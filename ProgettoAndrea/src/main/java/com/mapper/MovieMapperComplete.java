package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dto.MovieDTO;

public class MovieMapperComplete extends MovieMapper{

	@Override
	public MovieDTO mapRow(ResultSet rset, int arg1)
			throws SQLException {
		
		MovieDTO movie = new MovieDTO();
		movie.setMovieKey(rset.getString("idmovie"));
		movie.setImdbKey(rset.getString("indexImdb"));
		movie.setTitle(rset.getString("name"));
		movie.setYear(rset.getInt("year"));
		movie.setLength(rset.getInt("length"));
		movie.setPoster(rset.getString("poster"));
		movie.setPlot(rset.getString("plot"));
		movie.setReleaseDate(rset.getDate("releaseDate"));
		movie.setRated(rset.getString("rated"));
		movie.setRate(rset.getFloat("rate"));
		return movie;
	}
	
}
