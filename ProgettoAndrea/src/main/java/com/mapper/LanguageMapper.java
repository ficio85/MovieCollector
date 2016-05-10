package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dto.ActorDTO;
import com.dto.LanguageDTO;
import com.dto.MovieDTO;

public class LanguageMapper implements RowMapper<LanguageDTO>{

	@Override
	public LanguageDTO mapRow(ResultSet rset, int arg1)
			throws SQLException {
		
		LanguageDTO language = new LanguageDTO();
		language.setCod(rset.getString("cod"));
		language.setDes(rset.getString("des"));		
		return language;
	}
	
}
