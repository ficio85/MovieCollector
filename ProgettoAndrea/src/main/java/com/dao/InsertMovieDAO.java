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

import com.dto.ActorDTO;
import com.dto.GenereDTO;
import com.dto.LabelDTO;
import com.dto.MovieDTO;
import com.dto.ProgramTvMovieDTO;
import com.eccezione.WarningException;
import com.mapper.MovieMapper;

@Repository("insertMovieDAO")
public class InsertMovieDAO {

	@Autowired
	@Qualifier("jdbcTemplate")
	NamedParameterJdbcTemplate jdbcTemplate;
	public MovieDTO getMovieToUpdate (MovieDTO movie)
	{

		// TODO Auto-generated method stub

		MovieDTO result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("engTitle", movie.getTitle());
		parameters.addValue("engTitle", movie.getTitle());


		try {
			result=jdbcTemplate.queryForObject(" SELECT * FROM internazionalization, movie "
					+ "where internazionalization.idMovie=movie.idmovie and internationalization.engTitle=:engTitle and movie.year=:year "
					,parameters, new MovieMapper());
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;
		

	
	}
	
	public int updateInternationlization (MovieDTO movie)
	{

		// TODO Auto-generated method stub

		int result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("itTitle", movie.getTitoloItaliano());
		parameters.addValue("id", movie.getMovieKey());


		try {
			result=jdbcTemplate.update("update internationalization set itTitle=:itTitle where idMovie=:id", parameters);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;
		

	
	}
	
	
	public boolean isMovieActorRel (MovieDTO movie,String actor)
	{

		// TODO Auto-generated method stub

		List<String> result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("movie", movie.getMovieKey());
		parameters.addValue("actor",actor);


		try {
			result=jdbcTemplate.queryForList(" SELECT 1 FROM movieactor where movie=:movie and actor=:actor "
					,parameters, String.class);
		}
		catch(EmptyResultDataAccessException ex)
		{
			return false;
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		if(result==null||result.size()==0)
		{
			return false;
		}
		else
		{
			return true;
		}
		
		

	
	}
	
	public int getMovieActorIndex (MovieDTO movie,String actor)
	{

		// TODO Auto-generated method stub

		String result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("movie", movie.getMovieKey());


		try {
			result=jdbcTemplate.queryForObject(" SELECT max(indexList)+1 FROM movieactor where movie=:movie "
					,parameters, String.class);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		if(result == null || result.equals("") || result.equals("null"))
			result="1";
		return  Integer.parseInt(result);
		

	
	}

	public int updateMovieActorsRel (MovieDTO movie, String actor, int index)
	{

		// TODO Auto-generated method stub

		int result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("movie", movie.getMovieKey());
		parameters.addValue("actor", actor);
		parameters.addValue("index", index);


		try {
			result=jdbcTemplate.update("insert into movieactor (movie,actor,indexList)values (:movie,:actor,:index)", parameters);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;
		

	
	}
	
	public int insertLogWarning (WarningException warn)
	{

		// TODO Auto-generated method stub

		int result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("idmovie", warn.getMovieKey());
		parameters.addValue("tipoEccezione", warn.getTipo());
		parameters.addValue("messaggioEccezione", warn.getMessaggio());
		parameters.addValue("title", warn.getMovieTitle());
		parameters.addValue("stackTrace", warn.getStackTrace());


		try {
			result=jdbcTemplate.update("INSERT INTO `prog1_schema`.`logwarning`(`idmovie`,`tipoEccezione`,`messaggioEccezione`,`title`,`stacktrace`)"
					+ "VALUES (:idmovie,:tipoEccezione,:messaggioEccezione,:title,:stackTrace)", parameters);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;
		

	
	}

	public int deleteMovieActorsRel( String movieKey) {

		// TODO Auto-generated method stub

		int result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("movieKey", movieKey);



		try {
			result=jdbcTemplate.update("delete from movieactor where movie=:moviekey", parameters);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;
		

	
	}
	
	public int insertMovieActorsRel( ActorDTO actor, String movieKey) {

		// TODO Auto-generated method stub

		int result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("movieKey", movieKey);
		parameters.addValue("actor", actor.getName());
		parameters.addValue("indexList", actor.getIndexList());
		parameters.addValue("role", actor.getRole());
		try {
			result=jdbcTemplate.update("INSERT INTO `prog1_schema`.`movieactor`(`movie`,`actor`,`indexList`,`role`) VALUES(:moviekey,:actor,:index,:role)", parameters);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;
		

	
	}

	
	
	
	
}
