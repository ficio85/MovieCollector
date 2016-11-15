package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dto.UserMovieDTO;
import com.dto.UserMovieRateDTO;
import com.mapper.UserMovieRateMapper;

@Repository("userMovieDAO")
public class UserMovieDAO {

	@Autowired
	@Qualifier("jdbcTemplate")
	NamedParameterJdbcTemplate jdbcTemplate;
	
	public List<UserMovieRateDTO> getUserMovies(String codPers) {
		// TODO Auto-generated method stub
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("user", codPers);
		List<UserMovieRateDTO> result;
		try {
			result=jdbcTemplate.query(" SELECT * FROM usermovierate where user = (:user) ",parameters, new UserMovieRateMapper());
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return  result;

	}
	
		public List<String> getUserMoviesCodes(String codPers) {
		// TODO Auto-generated method stub
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("user", codPers);
		List<String> result;
		try {
			result=jdbcTemplate.queryForList(" SELECT movie FROM usermovierate where user = :user ",parameters, String.class);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}

		return  result;


	}
	
}
