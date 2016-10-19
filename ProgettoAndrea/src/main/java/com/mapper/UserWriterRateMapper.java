package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.dto.UserDirectorRateDTO;
import com.dto.UserWriterRateDTO;

public class UserWriterRateMapper implements RowMapper<UserWriterRateDTO>{

	@Override
	public UserWriterRateDTO mapRow(ResultSet rset, int arg1)
			throws SQLException {
		
		UserWriterRateDTO rate = new UserWriterRateDTO();
		rate.setUser(rset.getString("user"));
		rate.setRate(rset.getFloat("rate"));
		rate.setWriter(rset.getString("director"));	
		return rate;
	}

}
