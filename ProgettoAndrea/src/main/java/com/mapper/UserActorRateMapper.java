package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dto.UserActorRateDTO;
import com.dto.UserMovieRateDTO;


	
	public class UserActorRateMapper implements RowMapper<UserActorRateDTO>{

		@Override
		public UserActorRateDTO mapRow(ResultSet rset, int arg1)
				throws SQLException {
			
			UserActorRateDTO rate = new UserActorRateDTO();
			rate.setUser(rset.getString("user"));
			rate.setRate(rset.getFloat("rate"));
			rate.setActor(rset.getString("actor"));	
			rate.setAutorate(rset.getFloat("autorate"));
			return rate;
		}
	
	}
