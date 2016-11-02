package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dto.InternazionalizationDTO;
import com.dto.MovieDTO;
import com.mapper.InternationalizationMapper;

@Repository("internazionalizationDAO")
public class InternationalizationDAO {

	@Autowired
	@Qualifier("jdbcTemplate")
	NamedParameterJdbcTemplate jdbcTemplate;
	public int updateInternationalization (MovieDTO movie)
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
	
	public int insertTempInternazionalization (InternazionalizationDTO translation)
	{

		// TODO Auto-generated method stub

		int result = 0;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("itTitle", translation.getItTitle());
		parameters.addValue("engTitle", translation.getEngTitle());
		parameters.addValue("movie", translation.getMovie());


		try {
			result=jdbcTemplate.update("insert into internationalizationTemp (itTitle,engTitle,idMovie) values (:itTitle,:engTitle,:movie)", parameters);
		} 
		catch(Exception e){
			e.printStackTrace();
			//throw e;

		}
		return  result;
		
	
	}
	
	
	
	
	public List <InternazionalizationDTO> getInternationalizationbyTitoloItaliano (String titoloItaliano, String director)
	{

		// TODO Auto-generated method stub

		List<InternazionalizationDTO> result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("titoloItaliano", titoloItaliano);
		parameters.addValue("director", director);

	


		try {
			result=jdbcTemplate.query(" SELECT * FROM internationalization,moviedirector where  idMovie=movie and director =:director and itTitle=:titoloItaliano ",parameters, new InternationalizationMapper());
		}
		catch(EmptyResultDataAccessException ex)
		{
			return null;
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return result;
		
	
	}
	
	
}
