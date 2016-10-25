package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.object.MappingSqlQueryWithParameters;
import org.springframework.stereotype.Repository;

import com.dto.ActorDTO;
import com.eccezione.WarningException;
import com.mapper.ActorMapper;
import com.mapper.ActorMapperComplete;

@Repository("logDAO")
public class LogDAO {
	
	@Autowired
	@Qualifier("jdbcTemplate")
	NamedParameterJdbcTemplate jdbcTemplate;
	
	
	public int insertLogWarning (WarningException warn)
{

	// TODO Auto-generated method stub

	int result;
	MapSqlParameterSource parameters = new MapSqlParameterSource();
	parameters.addValue("idmovie", warn.getMovieKey());
	parameters.addValue("tipoEccezione", warn.getTipo());
	parameters.addValue("messaggioEccezione", warn.getMessaggio());
	parameters.addValue("title", warn.getMovieTitle());
	parameters.addValue("stackTrace", warn.getStackTrace());


	try {
		result=jdbcTemplate.update("INSERT INTO `prog1_schema`.`logwarning`(`idmovie`,`tipoEccezione`,`messaggioEccezione`,`title`,`stacktrace`)"
				+ "VALUES (:idmovie,:tipoEccezione,:messaggioEccezione,:title,:stackTrace)", parameters);
	} 
	catch(Exception e){
		e.printStackTrace();
		throw e;

	}
	return  result;
	


}}

