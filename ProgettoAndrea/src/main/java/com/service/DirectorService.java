package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.dao.ActorDAO;
import com.dao.DirectorDAO;
import com.dao.SearchMovieDAO;
import com.dto.MovieDTO;

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
}
