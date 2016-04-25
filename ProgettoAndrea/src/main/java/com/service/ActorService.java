package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.dao.ActorDAO;
import com.dao.SearchMovieDAO;
import com.dto.MovieDTO;

@Service("actorService")
public class ActorService {
	
	@Autowired
	@Qualifier("actorDAO")
	ActorDAO actorDAO;	
	public  List<String> getActorsList(String search) 
	{
		List<String> result = null;
		
		result=actorDAO.getActorsList(search);
		
		
		return result;

	}
}
