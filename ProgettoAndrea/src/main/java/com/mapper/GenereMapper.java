package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dto.ActorDTO;
import com.dto.DirectorDTO;
import com.dto.GenereDTO;
import com.dto.MovieDTO;

public class GenereMapper implements RowMapper<GenereDTO>{

	@Override
	public GenereDTO mapRow(ResultSet rset, int arg1)
			throws SQLException {
		
		GenereDTO genere = new GenereDTO();
		genere.setCodGenre("codGenre");
		genere.setDesGenre("desGenre");
		genere.setIndexList(rset.getInt("indexList"));
		
		return genere;
	}
	
}
