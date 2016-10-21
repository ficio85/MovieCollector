package com.dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dto.DirectorDTO;
import com.dto.GenereDTO;
import com.dto.LabelDTO;
import com.dto.MovieDTO;
import com.dto.UserActorRateDTO;
import com.dto.UserDirectorRateDTO;
import com.dto.UserGenreRateDTO;
import com.dto.UserMovieRateDTO;
import com.dto.UserWriterRateDTO;
import com.mapper.LabelMapper;
import com.mapper.UserActorRateMapper;
import com.mapper.UserDirectorRateMapper;
import com.mapper.UserGenreRateMapper;
import com.mapper.UserMovieRateMapper;
import com.mapper.UserWriterRateMapper;
import com.util.DirectorGeneratorUtil;

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


	public UserActorRateDTO getUserActorRate (String codPers, String actor)
	{

		UserActorRateDTO result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("user", codPers);
		parameters.addValue("actor", actor);
		try {
			result=jdbcTemplate.queryForObject("select user,actor,timestamp,rate,autorate from useractorrate  where actor = :actor and user=:user ",parameters, new UserActorRateMapper());
		}
		catch(EmptyResultDataAccessException e)
		{
			return null;
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;		
	}
	
	public UserDirectorRateDTO getUserDirectorRate (String codPers, String director)
	{

		UserDirectorRateDTO result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("user", codPers);
		parameters.addValue("director", director);
		try {
			result=jdbcTemplate.queryForObject("select user,director,timestamp,rate,autorate from userdirectorrate  where director = :director and user=:user ",parameters, new UserDirectorRateMapper());
		} 
		catch(EmptyResultDataAccessException e)
		{
			return null;
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;		
	}

	public UserWriterRateDTO getUserWriterRate (String codPers, String writer)
	{

		UserWriterRateDTO result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("user", codPers);
		parameters.addValue("writer", writer);
		try {
			result=jdbcTemplate.queryForObject("select user,writer,timestamp,rate from userwriterrate  where writer = :writer and user=:user ",parameters, new UserWriterRateMapper());
		}
		catch(EmptyResultDataAccessException e)
		{
			return null;
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;		
	}
	
	public UserGenreRateDTO getUserGenreRate (String codPers, String genre)
	{

		UserGenreRateDTO result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("user", codPers);
		parameters.addValue("genre", genre);
		try {
			result=jdbcTemplate.queryForObject("select user,genre,rate,count from usergenrerate  where genre = :genre and user=:user ",parameters, new UserGenreRateMapper());
		}
		catch(EmptyResultDataAccessException e)
		{
			return null;
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
	
	public int insertUserGuidaTv (String codPers,String movie, int count)
	{

		// TODO Auto-generated method stub

		int result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("user", codPers);
		parameters.addValue("movie", movie);
		parameters.addValue("like", count);


		try {
			result=jdbcTemplate.update("INSERT INTO `prog1_schema`.`usermovietv`(`user`,`movie`,`like`)"
					+ " VALUES (:user,:movie,:like) ", parameters);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;

	}
	
	public int getUserGuidaTv (String codPers,String movie)
	{

		// TODO Auto-generated method stub

		int result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("user", codPers);
		parameters.addValue("movie", movie);


		try {
			result=jdbcTemplate.queryForInt(" select like from usermovietv where user=:user and movie=:movie ", parameters);
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
	
	public List<UserMovieRateDTO> getUserMovieRateByDirector(String user,String director) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("user", user);
		parameters.addValue("director", director);
		String sql ="SELECT user,a.movie,rate from usermovierate a,moviedirector b "
				+ " where a.movie=b.movie and user=:user and director =:director ";

		return jdbcTemplate.query(sql,parameters,new UserMovieRateMapper());
	}
	
public List<UserMovieRateDTO> getUserMovieRateByActor(String user,String actor) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("user", user);
		parameters.addValue("actor", actor);
		String sql ="SELECT user,a.movie,rate from usermovierate a,movieactor b "
				+ " where a.movie=b.movie and user=:user and actor =:actor ";

		return jdbcTemplate.query(sql,parameters,new UserMovieRateMapper());
	}

public List<UserMovieRateDTO> getUserMovieRateByGenre(String user,String genre) {
	
	MapSqlParameterSource parameters = new MapSqlParameterSource();
	parameters.addValue("user", user);
	parameters.addValue("genere", genre);
	String sql ="SELECT user,a.movie,rate from usermovierate a,moviegenre b "
			+ " where a.movie=b.movie and user=:user and genre =:genere ";

	return jdbcTemplate.query(sql,parameters,new UserMovieRateMapper());
}

public List<UserMovieRateDTO> getUserMovieRateByWriter(String user, String writer) {
	
	MapSqlParameterSource parameters = new MapSqlParameterSource();
	parameters.addValue("user", user);
	parameters.addValue("writer", writer);
	String sql ="SELECT user,a.movie,rate from usermovierate a,moviewriter b "
			+ " where a.movie=b.movie and user=:user and writer =:writer ";

	return jdbcTemplate.query(sql,parameters,new UserMovieRateMapper());
}


public int insertUserDirectorRate2(String codPers,String director, float autorate ) {

	// TODO Auto-generated method stub

	int result;
	MapSqlParameterSource parameters = new MapSqlParameterSource();
	parameters.addValue("user", codPers);
	parameters.addValue("director", director);
	parameters.addValue("autorate", autorate);


	try {
		result=jdbcTemplate.update("INSERT INTO `prog1_schema`.`userdirectorrate`(`user`,`director`,`autorate`)"
				+ " VALUES (:user,:director,:autorate) ", parameters);
	} 
	catch(Exception e){
		e.printStackTrace();
		throw e;

	}
	return  result;

}

public int updateUserDirectorRate2(String codPers,String director, float autorate ) {

	// TODO Auto-generated method stub

	int result;
	MapSqlParameterSource parameters = new MapSqlParameterSource();
	parameters.addValue("user", codPers);
	parameters.addValue("director", director);
	parameters.addValue("autorate", autorate);


	try {
		result=jdbcTemplate.update("UPDATE userdirectorrate set autorate=:autorate where user=:user and director=:director ", parameters);
	} 
	catch(Exception e){
		e.printStackTrace();
		throw e;

	}
	return  result;

}
	
public int insertUserActorRate2(String codPers,String actor, float autorate ) {

	// TODO Auto-generated method stub

	int result;
	MapSqlParameterSource parameters = new MapSqlParameterSource();
	parameters.addValue("user", codPers);
	parameters.addValue("actor", actor);
	parameters.addValue("autorate", autorate);


	try {
		result=jdbcTemplate.update("INSERT INTO `prog1_schema`.`useractorrate`(`user`,`actor`,`autorate`)"
				+ " VALUES (:user,:actor,:autorate) ", parameters);
	} 
	catch(Exception e){
		e.printStackTrace();
		throw e;

	}
	return  result;

}

public int updateUserActorRate2(String codPers,String actor, float autorate ) {

	// TODO Auto-generated method stub

	int result;
	MapSqlParameterSource parameters = new MapSqlParameterSource();
	parameters.addValue("user", codPers);
	parameters.addValue("actor", actor);
	parameters.addValue("autorate", autorate);


	try {
		result=jdbcTemplate.update("UPDATE useractorrate set autorate=:autorate where user=:user and actor=:actor ", parameters);
	} 
	catch(Exception e){
		e.printStackTrace();
		throw e;

	}
	return  result;

}
	
public int insertUserWriteRate(String codPers,String writer, float rate ) {

	// TODO Auto-generated method stub

	int result;
	MapSqlParameterSource parameters = new MapSqlParameterSource();
	parameters.addValue("user", codPers);
	parameters.addValue("writer", writer);
	parameters.addValue("rate", rate);


	try {
		result=jdbcTemplate.update("INSERT INTO `prog1_schema`.`userwriterrate`(`user`,`writer`,`rate`)"
				+ " VALUES (:user,:writer,:rate) ", parameters);
	} 
	catch(Exception e){
		e.printStackTrace();
		throw e;

	}
	return  result;

}

public int updateUserWriterRate(String codPers,String writer, float rate ) {

	// TODO Auto-generated method stub

	int result;
	MapSqlParameterSource parameters = new MapSqlParameterSource();
	parameters.addValue("user", codPers);
	parameters.addValue("writer", writer);
	parameters.addValue("rate", rate);


	try {
		result=jdbcTemplate.update("UPDATE userwriterrate set rate=:rate where user=:user and writer=:writer", parameters);
	} 
	catch(Exception e){
		e.printStackTrace();
		throw e;

	}
	return  result;

}

public int insertUserGenreRate(String codPers,String genre, float rate, int sum ) {

	// TODO Auto-generated method stub

	int result;
	MapSqlParameterSource parameters = new MapSqlParameterSource();
	parameters.addValue("user", codPers);
	parameters.addValue("genre", genre);
	parameters.addValue("rate", rate);
	parameters.addValue("count", sum);


	try {
		result=jdbcTemplate.update("INSERT INTO `prog1_schema`.`usergenrerate`(`user`,`genre`,`rate`,`count`) VALUES (:user,:genre,:rate,:count)", parameters);
	} 
	catch(Exception e){
		e.printStackTrace();
		throw e;

	}
	return  result;

}

public int updateUserGenreRate(String codPers,String genre, float rate, int sum ) {

	// TODO Auto-generated method stub

	int result;
	MapSqlParameterSource parameters = new MapSqlParameterSource();
	parameters.addValue("user", codPers);
	parameters.addValue("genre", genre);
	parameters.addValue("rate", rate);
	parameters.addValue("count", sum);


	try {
		result=jdbcTemplate.update("UPDATE usergenrerate SET rate=:rate, count=:count where user=:user and genre=:genre", parameters);
	} 
	catch(Exception e){
		e.printStackTrace();
		throw e;

	}
	return  result;

}



}
