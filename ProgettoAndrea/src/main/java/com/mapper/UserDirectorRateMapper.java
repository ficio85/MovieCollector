package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dto.UserActorRateDTO;
import com.dto.UserDirectorRateDTO;
import com.dto.UserMovieRateDTO;


	
	public class UserDirectorRateMapper implements RowMapper<UserDirectorRateDTO>{

		@Override
		public UserDirectorRateDTO mapRow(ResultSet rset, int arg1)
				throws SQLException {
			
			UserDirectorRateDTO rate = new UserDirectorRateDTO();
			rate.setUser(rset.getString("user"));
			rate.setRate(rset.getFloat("rate"));
			rate.setDirector(rset.getString("director"));	
			rate.setAutorate(rset.getFloat("autorate"));
			return rate;
		}
	
	}
