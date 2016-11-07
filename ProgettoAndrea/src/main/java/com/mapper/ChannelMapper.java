package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dto.ActorDTO;
import com.dto.ChannelDTO;
import com.dto.CountryDTO;
import com.dto.DirectorDTO;
import com.dto.MovieDTO;

public class ChannelMapper implements RowMapper<ChannelDTO>{

	@Override
	public ChannelDTO mapRow(ResultSet rset, int arg1)
			throws SQLException {
		
		ChannelDTO country = new ChannelDTO();
		country.setChannel(rset.getString("channel"));
		country.setPlatform(rset.getString("platform"));
		
		return country;
	}
	
}
