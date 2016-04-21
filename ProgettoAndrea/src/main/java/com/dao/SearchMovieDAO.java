package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.dto.MovieDTO;


public class SearchMovieDAO {
	
	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;	
	
		private String sqlbase=
		"   select movie.*,moviegenre.*, movieactor.*,internationalization.*,moviedirector.*          "+
		"	from movie                                                                                "+
		"	LEFT JOIN movieactor on movie.idmovie = movieactor.movie                                  "+
		"	LEFT JOIN internationalization on movie.idmovie= internationalization.idmovie             "+
		"	LEFT JOIN moviegenre on movie.idmovie=moviegenre.movie                                    "+
		"	LEFT JOIN moviedirector on movie.idmovie=moviedirector.movie						      "+
		"	where movie.idmovie in =	?				      ";

		


		private class MovieWrapper implements RowMapper<MovieDTO>{

			@Override
			public MovieDTO mapRow(ResultSet rset, int arg1)
					throws SQLException {
				
				MovieDTO film = new MovieDTO();
				
				film.setTitle(rset.getString("engTitle"));
				film.setTitoloItaliano(rset.getString("itTitle"));
				film.setMovieKey(rset.getString("idMovie"));
				
				
				return film;
			}
			
		}
}
