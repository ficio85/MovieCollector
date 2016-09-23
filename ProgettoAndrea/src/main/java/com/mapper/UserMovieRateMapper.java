package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dto.ActorDTO;
import com.dto.UserMovieRateDTO;

public class UserMovieRateMapper implements RowMapper<UserMovieRateDTO>{

	@Override
	public UserMovieRateDTO mapRow(ResultSet rset, int arg1)
			throws SQLException {
		
		UserMovieRateDTO rate = new UserMovieRateDTO();
		rate.setUser(rset.getString("user"));
		rate.setRate(rset.getFloat("rate"));
		rate.setMovie(rset.getString("movie"));	
		return rate;
	}
	
}
