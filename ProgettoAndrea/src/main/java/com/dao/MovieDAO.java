package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.swing.tree.TreePath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.dto.MovieDTO;
import com.menu.ProvaDTO;

@Repository("movieDAO")
public class MovieDAO {


	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;	
	public int insertMovie (MovieDTO movieDto)
	{
		int result=0;

		try{		

			result = jdbcTemplate.update("INSERT INTO movie (`idmovie`,`name`,`length`,`country`,`imdbRating`,`year`,`actors`,`plot`,`genre`) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?)",
					new Object[]{movieDto.getMovieKey(), movieDto.getName(),movieDto.getLength(),"prova",movieDto.getImdbRating(),movieDto.getYear(),3,movieDto.getPlot(),"COD"});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return result;


	}



	public int deleteAllMovies ()
	{
		int result=0;

		try{		

			result = jdbcTemplate.update("DELETE  from movie",new Object[]{});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
		}
		return result;


	}

	public String isMoviePresent(MovieDTO movieDto) 
	{
		String result="";
		try {
			result=jdbcTemplate.queryForObject(" SELECT 1 FROM movie where name= ? ", new Object[] { movieDto.getName() }, String.class);
		} 
		catch(EmptyResultDataAccessException e)
		{
			result= "EMPTY";
		}
		catch(Exception e)
		{
			result="ERROR";
		}
		return result;

	}

	public boolean isPresentCountry(String country) {
		// TODO Auto-generated method stub
		String cod=country.substring(0, 3).toUpperCase();

		String result="";
		try {
			result=jdbcTemplate.queryForObject(" SELECT 1 FROM nations where idnations= ? ", new Object[] { cod }, String.class);
		} 
		catch(EmptyResultDataAccessException e)
		{
			result= "EMPTY";
		}
		catch(Exception e)
		{
			result="ERROR";
		}
		if(result.equals("1"))
			return true;
		else return false;



	}

	public int insertCountry(String country) {

		int result=0;

		String desCountry=country.toUpperCase();
		String codCountry =desCountry.substring(0, 3);
		String cod=country.substring(0, 3).toUpperCase();

		try{		

			result = jdbcTemplate.update("INSERT INTO nations (`idnations`,`desnations`) values ( ?, ?)", new Object[]{codCountry,desCountry});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return result;



	}

	public int deleteAllCountries()
	{
		int result=0;

		try{		

			result = jdbcTemplate.update("DELETE from nations",new Object[]{});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
		}
		return result;


	}

	public boolean isPresentGenre(String genre) {
		// TODO Auto-generated method stub
		String cod=genre.trim().substring(0, 3).toUpperCase();

		String result="";
		try {
			result=jdbcTemplate.queryForObject(" SELECT 1 FROM genre where codGenre= ? ", new Object[] { cod }, String.class);
		} 
		catch(EmptyResultDataAccessException e)
		{
			result= "EMPTY";
		}
		catch(Exception e)
		{
			result="ERROR";
		}
		if(result.equals("1"))
			return true;
		else return false;



	}

	public int insertGenre(String genre) {

		int result=0;

		String desGenre=genre.toUpperCase().trim();
		String codGenre=desGenre.substring(0, 3);

		try{		

			result = jdbcTemplate.update("INSERT INTO genre (`codGenre`,`desGenre`) values ( ?, ?)", new Object[]{codGenre,desGenre});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return result;



	}

	public int insertActor(String actor) {

		int result=0;


		try{		

			result = jdbcTemplate.update("INSERT INTO actor (`name`) values ( ?)", new Object[]{actor});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return result;


	}

	public boolean isPresentActor(String actor) {
		// TODO Auto-generated method stub

		String result="";
		try {
			result=jdbcTemplate.queryForObject(" SELECT 1 FROM actor where name= ? ", new Object[] { actor }, String.class);
		} 
		catch(EmptyResultDataAccessException e)
		{
			result= "EMPTY";
		}
		catch(Exception e)
		{
			result="ERROR";
		}
		if(result.equals("1"))
			return true;
		else return false;



	}

	public int deleteActors()
	{
		int result=0;

		try{		

			result = jdbcTemplate.update("DELETE from actor ",new Object[]{});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
		}
		return result;


	}

	public int deleteMovieActors()
	{
		int result=0;

		try{		

			result = jdbcTemplate.update("DELETE from movieactor ",new Object[]{});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
		}
		return result;


	}





	public int deleteGenres()
	{
		int result=0;

		try{		

			result = jdbcTemplate.update("DELETE from genre ",new Object[]{});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
		}
		return result;


	}

	public void insertMovieGenRel(MovieDTO movieDto) {

		String movieKey = movieDto.getMovieKey();
		List <String> movieGenre= movieDto.getGenre();


		for(String genre: movieGenre)
		{
			try{		

				jdbcTemplate.update("INSERT INTO moviegenre (`movie`,`genre`) values ( ?, ?)", new Object[]{movieDto.getMovieKey(), genre.trim().toUpperCase().substring(0, 3)});					
				//				
			}

			catch(Exception e){
				e.printStackTrace();
				throw e;

			}
		}



	}

	public void insertMovieActorsRel(MovieDTO movieDto) {

		String movieKey = movieDto.getMovieKey();
		List <String> movieActors= movieDto.getActors();


		for(String actor: movieActors)
		{
			try{		

				jdbcTemplate.update("INSERT INTO movieactor (`movie`,`actor`) values ( ?,?)", new Object[]{movieDto.getMovieKey(),actor.trim()});					
				//				
			}

			catch(Exception e){
				e.printStackTrace();
				throw e;
			}
		}



	}
	
	
	public int deleteMovGenRel()
	{
		int result=0;

		try{		

			result = jdbcTemplate.update("DELETE from moviegenre ",new Object[]{});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
		}
		return result;


	}
	
	public int deleteMovieActorsRel()
	{
		int result=0;

		try{		

			result = jdbcTemplate.update("DELETE from movieactor ",new Object[]{});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
		}
		return result;


	}



}
