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
import com.dto.ImageDTO;
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


	public int updateDirectorInfo(DirectorDTO  director) {
		// TODO Auto-generated method stub


		int result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		//per adesso solo la chiave imdb
		parameters.addValue("iCompleto", director.getiCompleto());
		parameters.addValue("timeimdb", director.getTimeiCompleto());
		parameters.addValue("fullname", director.getFullname());
		parameters.addValue("birthDate", director.getBirthDate());
		parameters.addValue("director", director.getName());
		result=jdbcTemplate.update(" UPDATE director set iCompleto=:iCompleto, timeiCompleto=:timeimdb, fullname=:fullname, birthDate=:birthDate where name = :director ", parameters);			
		return result;
			
		}


	public void insertDirectorImages(String image, String director) {
		// TODO Auto-generated method stub
		

		int result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		//per adesso solo la chiave imdb
		parameters.addValue("image", image);
		parameters.addValue("director", director);
		result=jdbcTemplate.update(" INSERT into imagedirector(image,director) values(:image,:director) ", parameters);			

	}


	public void insertDirectorImages(DirectorDTO director) {
		// TODO Auto-generated method stub
		

		int result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		//per adesso solo la chiave imdb
		parameters.addValue("director", director.getName());
		for(ImageDTO image :director.getImages())
		{
			parameters.addValue("image", image.getSrc());
			parameters.addValue("heigth", image.getHeight());
			parameters.addValue("width", image.getWidth());
			result=jdbcTemplate.update(" INSERT into imagedirector(director,image,heigth,width) values(:director,:image,:heigth,:width) ", parameters);			

		}

	}
}
