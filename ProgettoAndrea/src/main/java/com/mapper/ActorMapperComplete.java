package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dto.ActorDTO;
import com.dto.MovieDTO;

public class ActorMapperComplete implements RowMapper<ActorDTO>{

	@Override
	public ActorDTO mapRow(ResultSet rset, int arg1)
			throws SQLException {
		
		ActorDTO actor = new ActorDTO();
		actor.setName(rset.getString("name"));
		actor.setRate(rset.getFloat("rate"));
		actor.setwCompleto(rset.getInt("wCompleto"));
		actor.setTimewCompleto(rset.getTimestamp("timewCompleto"));
//		actor.setIndexList(rset.getInt("indexList"));	
		return actor;
	}
	
}
