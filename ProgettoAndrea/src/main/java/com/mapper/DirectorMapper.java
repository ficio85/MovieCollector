package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dto.ActorDTO;
import com.dto.DirectorDTO;
import com.dto.MovieDTO;

public class DirectorMapper implements RowMapper<DirectorDTO>{

	@Override
	public DirectorDTO mapRow(ResultSet rset, int arg1)
			throws SQLException {
		
		DirectorDTO director = new DirectorDTO();
		director.setName("director");
		director.setIndexList(rset.getInt("indexList"));
		
		return director;
	}
	
}
