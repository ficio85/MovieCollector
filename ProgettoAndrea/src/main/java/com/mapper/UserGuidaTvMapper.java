package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dto.ActorDTO;
import com.dto.UserGuidaTvDTO;
import com.dto.UserMovieRateDTO;

public class UserGuidaTvMapper implements RowMapper<UserGuidaTvDTO>{

	@Override
	public UserGuidaTvDTO mapRow(ResultSet rset, int arg1)
			throws SQLException {
		
		UserGuidaTvDTO rate = new UserGuidaTvDTO();
		rate.setUser(rset.getString("user"));
		rate.setLike(rset.getFloat("rate"));
		rate.setMovie(rset.getString("movie"));	
		return rate;
	}
	
}
