package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.controller.ProgramTvMovieDTO;
import com.dto.MovieDTO;

public class ProgramTvMapper implements RowMapper<ProgramTvMovieDTO> {


	@Override
	public ProgramTvMovieDTO mapRow(ResultSet rset, int arg1)throws SQLException  {
		
		
		ProgramTvMovieDTO program = new ProgramTvMovieDTO();
		program.getMovie().setMovieKey(rset.getString("movie"));
		program.setOraInizio(rset.getTimestamp("time"));
		program.setPlatform(rset.getString("channel"));


		return program;
	}
	

}
