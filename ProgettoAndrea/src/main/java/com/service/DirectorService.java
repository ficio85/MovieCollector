package com.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ActorDAO;
import com.dao.DirectorDAO;
import com.dao.SearchMovieDAO;
import com.dto.DirectorDTO;
import com.dto.ImageDTO;
import com.dto.MovieDTO;
import com.util.JsoupUtil;

@EnableAsync
@Service("directorService")
public class DirectorService {
	
	@Autowired
	@Qualifier("directorDAO")
	DirectorDAO directorDAO;	
	public  List<String> getDirectorsList(String search) 
	{
		List<String> result = null;
		
		result=directorDAO.getDirectorsList(search);
		
		
		return result;

	}
	
	@Async
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
	public  void getCompleteInfoDirector(String movieToParse, DirectorDTO director) throws Exception
	{
		if(director.getiCompleto()!=1)
		{
			System.out.println("Inizio thread");
			DirectorDTO directorInspected = JsoupUtil.imdbInspectDirectorFromMovie(movieToParse);
			director.setImdbIndex(directorInspected.getImdbIndex());
			JsoupUtil.generateImdbDirectorInfo(director);
			director.setiCompleto(1);
			director.setTimeiCompleto(new Timestamp(new Date().getTime()));
			directorDAO.updateDirectorInfo(director);
		}
		if(director.getImages()==null || director.getImages().size()==0)
		{
			JsoupUtil.generateWikiDirectorInfo(director);
	
			directorDAO.insertDirectorImages(director);
		
		}
	}
}
