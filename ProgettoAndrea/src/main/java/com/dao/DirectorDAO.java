package com.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.object.MappingSqlQueryWithParameters;
import org.springframework.stereotype.Repository;

import com.dto.DirectorDTO;
import com.mapper.DirectorMapperComplete;

@Repository("directorDAO")
public class DirectorDAO {
	@Autowired
	@Qualifier("jdbcTemplate")
	NamedParameterJdbcTemplate jdbcTemplate;	
	public List<String> getDirectorsList(String search) 
	{
		List<String> result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("name", search+"%");
		result=jdbcTemplate.queryForList(" SELECT name FROM director where name like :name ", parameters, String.class);			
		return result;
	}
	
	
	public DirectorDTO getDirectorDetail(String search) 
	{
		DirectorDTO result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("name", search);
		result= jdbcTemplate.queryForObject(" SELECT * FROM director where name = :name ", parameters, new DirectorMapperComplete());			
		return result;
	}
	
	public int updateDirectorbyWiki(int wiki, Timestamp timewiki, String director) {

		int result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		//per adesso solo la chiave imdb
		parameters.addValue("wiki", wiki);
		parameters.addValue("timewiki", timewiki);
		parameters.addValue("director", director);
		result=jdbcTemplate.update(" UPDATE director set wCompleto=:wiki, timewCompleto=:timewiki where name = :director ", parameters);			
		return result;
			
	}
}
