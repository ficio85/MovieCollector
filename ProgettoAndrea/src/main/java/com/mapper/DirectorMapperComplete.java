package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dto.DirectorDTO;
import com.dto.MovieDTO;

public class DirectorMapperComplete implements RowMapper<DirectorDTO>{

	@Override
	public DirectorDTO mapRow(ResultSet rset, int arg1)
			throws SQLException {
		
		DirectorDTO director = new DirectorDTO();
		director.setName(rset.getString("name"));
		director.setFullname(rset.getString("fullname"));
		director.setBirthDate(rset.getDate("birthDate"));
		director.setwCompleto(rset.getInt("wCompleto"));
		director.setiCompleto(rset.getInt("iCompleto"));
		director.setTimewCompleto(rset.getTimestamp("timewCompleto"));
		director.setTimeiCompleto(rset.getTimestamp("timeiCompleto"));
		director.setBirthplace(rset.getString("birthplace"));
//		actor.setIndexList(rset.getInt("indexList"));	
		return director;
	}
	
}
