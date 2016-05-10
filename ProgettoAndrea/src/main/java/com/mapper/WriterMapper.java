package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dto.ActorDTO;
import com.dto.MovieDTO;
import com.dto.WriterDTO;

public class WriterMapper implements RowMapper<WriterDTO>{

	@Override
	public WriterDTO mapRow(ResultSet rset, int arg1)
			throws SQLException {
		
		WriterDTO writer = new WriterDTO();
		writer.setName(rset.getString("writer"));
		writer.setWork(rset.getString("work"));
		writer.setIndexList(rset.getString("indexList"));
		return writer;
	}
	
}
