package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dto.UserDirectorRateDTO;
import com.dto.UserGenreRateDTO;
import com.dto.UserMovieRateDTO;

public class UserGenreRateMapper implements RowMapper<UserGenreRateDTO>{

	@Override
	public UserGenreRateDTO mapRow(ResultSet rset, int arg1)
			throws SQLException {
		
		UserGenreRateDTO rate = new UserGenreRateDTO();
		rate.setUser(rset.getString("user"));
		rate.setRate(rset.getFloat("rate"));
		rate.setGenre(rset.getString("genre"));	
		rate.setCount(rset.getInt("count"));
		return rate;
	}

}
