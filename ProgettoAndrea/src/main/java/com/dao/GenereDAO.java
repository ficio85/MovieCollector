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
import com.dto.MovieDTO;

@Repository("genereDAO")
public class GenereDAO {

	@Autowired
	@Qualifier("jdbcTemplate")
	NamedParameterJdbcTemplate jdbcTemplate;
	public List <GenereDTO> getListaGeneri ()
	{

		// TODO Auto-generated method stub

		List<GenereDTO> result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		try {
			result=jdbcTemplate.query(" SELECT * FROM genre ",parameters, new GenreWrapper());
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;
		

	
	}
	
	private class GenreWrapper implements RowMapper<GenereDTO>{

		@Override
		public GenereDTO mapRow(ResultSet rset, int arg1)
				throws SQLException {
			
			GenereDTO genere = new GenereDTO();
			
			genere.setCodGenre(rset.getString("codGenre"));
			genere.setDesGenre(rset.getString("desGenre"));		
			return genere;
		}
		
	}

	
	
	
	
}
