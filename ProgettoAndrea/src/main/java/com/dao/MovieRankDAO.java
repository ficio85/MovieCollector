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
import com.dto.UserMovieRateDTO;
import com.mapper.LabelMapper;
import com.mapper.UserMovieRateMapper;

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


	public float getUserActorRank (String codPers, String actor)
	{

		float result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("user", codPers);
		parameters.addValue("actor", actor);
		try {
			result=jdbcTemplate.queryForObject(" SELECT  rate FROM useractorrate where actor = :actor and user=:user ",parameters, Float.class);
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

	public int insertUserActorRate (String codPers,String actor, float rate)
	{

		// TODO Auto-generated method stub

		int result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("user", codPers);
		parameters.addValue("actor", actor);
		parameters.addValue("rate", rate);


		try {
			result=jdbcTemplate.update("INSERT INTO `prog1_schema`.`useractorrate`(`user`,`actor`,`rate`)"
					+ " VALUES (:user,:actor,:rate) ", parameters);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;

	}
	
	public int insertUserDirectorRate (String codPers,String director, float rate)
	{

		// TODO Auto-generated method stub

		int result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("user", codPers);
		parameters.addValue("director", director);
		parameters.addValue("rate", rate);


		try {
			result=jdbcTemplate.update("INSERT INTO `prog1_schema`.`userdirectorrate`(`user`,`director`,`rate`)"
					+ " VALUES (:user,:director,:rate) ", parameters);
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
	
	public int deleteUserActorRate(String codPers, String actor) {

		// TODO Auto-generated method stub

		int result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("user", codPers);
		parameters.addValue("actor", actor);


		try {
			result=jdbcTemplate.update(" delete from useractorrate where user=:user and actor=:actor  ", parameters);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;



	}
	
	public int deleteUserDirectorRate(String codPers, String director) {

		// TODO Auto-generated method stub

		int result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("user", codPers);
		parameters.addValue("director", director);


		try {
			result=jdbcTemplate.update(" delete from userdirectorrate where user=:user and director=:director  ", parameters);
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
	
	public float updateActorRank(String actor, float rank)
	{

		float result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("rate", rank);
		parameters.addValue("actor", actor);
		try {
			result=jdbcTemplate.update(" UPDATE actor set rate=:rate where name=:actor ",parameters);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;		
	}
	
	public float updateDirectorRank(String director, float rank)
	{

		float result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("rate", rank);
		parameters.addValue("director", director);
		try {
			result=jdbcTemplate.update(" UPDATE director set rate=:rate where name=:director ",parameters);
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
	
	public float getSumUserActorRate(String actor) {

		float result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("actor", actor);
		try {
			result=jdbcTemplate.queryForObject(" SELECT SUM(rate) from useractorrate where actor=:actor ",parameters,Float.class);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;		
	}
	
	public float getSumUserDirectorRate(String director) {

		float result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("director", director);
		try {
			result=jdbcTemplate.queryForObject(" SELECT SUM(rate) from userdirectorrate where director=:director ",parameters,Float.class);
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
	

	public int getCountUserActorRate(String actor) {
		// TODO Auto-generated method stub
		int result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("actor", actor);
		try {
			result=jdbcTemplate.queryForInt(" SELECT COUNT(rate) from useractorrate where actor=:actor ", parameters);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;		

	}
	
	public int getCountUserDirectorRate(String director) {
		// TODO Auto-generated method stub
		int result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("director", director);
		try {
			result=jdbcTemplate.queryForInt(" SELECT COUNT(rate) from userdirectorrate where director=:director ", parameters);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;		

	}

	public List<UserMovieRateDTO> getUserMovieRate(String user) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("user", user);
		String sql ="SELECT `user`,`movie`,`rate` from usermovierate where user=:user ";

		return jdbcTemplate.query(sql,parameters,new UserMovieRateMapper());
	}
	
	
}
