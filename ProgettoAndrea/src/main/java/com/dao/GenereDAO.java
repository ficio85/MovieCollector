package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dao.InsertMovieDAO.DataInternationalization;
import com.dto.GenereDTO;
import com.dto.MovieDTO;

public class GenereDAO {

	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;
	public List <GenereDTO> getListaGeneri ()
	{

		// TODO Auto-generated method stub

		List<GenereDTO> result;
		try {
			result=jdbcTemplate.query(" SELECT * FROM genre ", new Object[] { result. }, new GenreWrapper());
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
			
			genere.setCodGenre(codGenre);
			genere.setDesGenre(desGenre);		
			return genere;
		}
		
	}
	
	
	
}
