package com.dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.controller.ProgramTvMovieDTO;
import com.dto.ActorDTO;
import com.dto.GenereDTO;
import com.dto.LabelDTO;
import com.dto.MovieDTO;
import com.dto.ProgramMovieDTO;
import com.mapper.LabelMapper;
import com.mapper.ProgramTvMapper;

@Repository("guidaTvDAO")
public class GuidaTvDAO {
	
	@Autowired
	@Qualifier("jdbcTemplate")
	NamedParameterJdbcTemplate jdbcTemplate;
	public int insertProgrammaTv(ProgramTvMovieDTO program) {

		// TODO Auto-generated method stub

		int result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("movieKey", program.getMovie().getMovieKey());
		parameters.addValue("time", program.getOraInizio());
		parameters.addValue("channel", program.getPlatform());
		parameters.addValue("title", program.getTitolo());
		parameters.addValue("type", program.getTipo());

		try {
			result=jdbcTemplate.update("INSERT INTO `prog1_schema`.`movietv`(`movie`,`time`,`channel`,`title`,`type`) VALUES(:movieKey,:time,:channel,:title,:type)", parameters);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;



}
	

	public ProgramTvMovieDTO getFirstProgrammaTv() {

		// TODO Auto-generated method stub

		MapSqlParameterSource parameters = new MapSqlParameterSource();


		ProgramTvMovieDTO result;
		try {
			result=jdbcTemplate.queryForObject("select * from movietv  limit 1 ", parameters, new ProgramTvMapper());
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;



}
	
	public int deleteGuidaTv()
	{

		int result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();


		try {
			result=jdbcTemplate.update("delete from movietv ",parameters);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;		
	}


}
