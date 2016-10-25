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
	
	
	
	public List <InternazionalizationDTO> getInternationalizationbyTitoloItaliano (String titoloItaliano)
	{

		// TODO Auto-generated method stub

		List<InternazionalizationDTO> result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("titoloItaliano", titoloItaliano);
	


		try {
			result=jdbcTemplate.query(" SELECT * FROM internationalization where itTitle=:titoloItaliano ",parameters, new InternationalizationMapper());
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
