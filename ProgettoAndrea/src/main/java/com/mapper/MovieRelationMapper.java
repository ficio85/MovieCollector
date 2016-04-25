package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dto.MovieDTO;
import com.dto.RelationDTO;

public class MovieRelationMapper  implements RowMapper<RelationDTO> {

	@Override
	public RelationDTO mapRow(ResultSet rset, int arg1)
			throws SQLException {
		
		MovieDTO movie = new MovieDTO();
		movie.setMovieKey(rset.getString("idmovie"));
		movie.setTitle(rset.getString("name"));
		movie.setLength(rset.getInt("length"));
		movie.setPoster(rset.getString("poster"));

		return null;
	}
	
	
}
