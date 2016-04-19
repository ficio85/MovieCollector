package com.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;


public class SearchMovieDAO {
	
	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;	
	
//	public List <String> getMovieActors(List <String> actors) {
//		// TODO Auto-generated method stub
//
//		MapSqlParameterSource parameters = new MapSqlParameterSource();
//		parameters.addValue("names", actors);
//		List<Map<String, Object>> Foos = jdbcTemplate.queryForList("select * from foo where name in (:names)",parameters,String.class);
//		
//		
//		
//		List<String> result;
//		try {
//			result=jdbcTemplate.queryForList(sql, elementType, args)(" SELECT * FROM movieactor where actor in = ? ", new Object[] { movie.getTitle() }, new DataInternationalization());
//		} 
//		catch(Exception e){
//			e.printStackTrace();
//			throw e;
//
//		}
//		return  result;
//		
//
//	}
	
//	public List <String> getMovieActors(MovieDTO movie) {
//		// TODO Auto-generated method stub
//
//		List<String> result;
//		try {
//			result=jdbcTemplate.query(" SELECT * FROM internationalization where engTitle= ? ", new Object[] { movie.getTitle() }, new DataInternationalization());
//		} 
//		catch(Exception e){
//			e.printStackTrace();
//			throw e;
//
//		}
//		return  result;
//		
//
//	}
//	

}
