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

@Repository("rateDAO")
public class MovieRankDAO {

	@Autowired
	@Qualifier("jdbcTemplate")
	NamedParameterJdbcTemplate jdbcTemplate;
	public float getUserMovieRank (String codPers, String movie)
	{

		float result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("user", codPers);
		parameters.addValue("movie", movie);
		try {
			result=jdbcTemplate.queryForObject(" SELECT  rate FROM usermovierate where movie = :movie and user=:user ",parameters, Float.class);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;		
	}



	public int insertUserRate (String codPers,String movie, float rate)
	{

		// TODO Auto-generated method stub

		int result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("user", codPers);
		parameters.addValue("movie", movie);
		parameters.addValue("rate", rate);


		try {
			result=jdbcTemplate.update("INSERT INTO `prog1_schema`.`usermovierate`(`user`,`movie`,`rate`)"
					+ " VALUES (:user,:movie,:rate) ", parameters);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;

	}

	public int deleteUserRate(String codPers, String movie) {

		// TODO Auto-generated method stub

		int result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("user", codPers);
		parameters.addValue("movie", movie);


		try {
			result=jdbcTemplate.update(" delete from usermovierate where user=:user and movie=:movie  ", parameters);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;



	}	


	public float updateMovieRank(String movie, float rank)
	{

		float result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("rate", rank);
		parameters.addValue("movie", movie);
		try {
			result=jdbcTemplate.update(" UPDATE movie set rate=:rate where idmovie=:movie ",parameters);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;		
	}



	public float getSumUserRate(String movie) {

		float result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("movie", movie);
		try {
			result=jdbcTemplate.queryForObject(" SELECT SUM(rate) from usermovierate where movie=:movie ",parameters,Float.class);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;		
	}



	public int getCountUserRate(String movie) {
		// TODO Auto-generated method stub
		int result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("movie", movie);
		try {
			result=jdbcTemplate.queryForInt(" SELECT COUNT(rate) from usermovierate where movie=:movie ", parameters);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;		

	}

}
