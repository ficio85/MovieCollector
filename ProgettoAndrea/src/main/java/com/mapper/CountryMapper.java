package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dto.ActorDTO;
import com.dto.CountryDTO;
import com.dto.DirectorDTO;
import com.dto.MovieDTO;

public class CountryMapper implements RowMapper<CountryDTO>{

	@Override
	public CountryDTO mapRow(ResultSet rset, int arg1)
			throws SQLException {
		
		CountryDTO country = new CountryDTO();
		country.setCod(rset.getString("idnations"));
		country.setDes(rset.getString("desnations"));
		
		return country;
	}
	
}
