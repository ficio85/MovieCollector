package com.dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dto.GenereDTO;
import com.dto.LabelDTO;
import com.dto.MovieDTO;
import com.mapper.LabelMapper;

@Repository("labelDAO")
public class LabelDAO {

	@Autowired
	@Qualifier("jdbcTemplate")
	NamedParameterJdbcTemplate jdbcTemplate;
	public List <LabelDTO> getListaLabelbySearch (String search)
	{

		List<LabelDTO> result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("name", search+"%");
		try {
			result=jdbcTemplate.query(" SELECT distinct  label FROM usermovielabel where label like :name ",parameters, new LabelMapper());
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;		
	}
	
	public List <LabelDTO> getListaLabelbyMovie (String movie)
	{

		List<LabelDTO> result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("name", movie+"%");
		try {
			result=jdbcTemplate.query(" SELECT count(label)  FROM usermovielabel where movie = :name group by label order by count(label) LIMIT 5  ",parameters, new LabelMapper());
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;		
	}
	
	
	
	public int updateLabelsbyMovie (String movie, String label)
	{

		int result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("movie", movie);
		parameters.addValue("label", label);
	
		
		try {
			result=jdbcTemplate.update("insert into movielabel (movie, label) values (:movie, :label)",parameters);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;		
	}

	public int deleteLabelsFromMovie (String movie)
	{

		int result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("movie", movie);
	
		
		try {
			result=jdbcTemplate.update("delete from movielabel where movie=:movie",parameters);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;		
	}
	
	public int insertUserLaber (String codPers,String movie, LabelDTO label)
	{

		// TODO Auto-generated method stub

		int result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("user", codPers);
		parameters.addValue("movie", movie);
		parameters.addValue("label", label.getName());


		try {
			result=jdbcTemplate.update("INSERT INTO `prog1_schema`.`usermovielabel`(`user`,`movie`,`label`)"
					+ "VALUES (:user,:movie,:label)", parameters);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;
			
	}

	
	
}
