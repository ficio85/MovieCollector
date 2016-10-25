package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dto.GenereDTO;
import com.dto.InternazionalizationDTO;

public class InternationalizationMapper implements RowMapper<InternazionalizationDTO>{

	@Override
	public InternazionalizationDTO mapRow(ResultSet rset, int arg1)
			throws SQLException {
		
		InternazionalizationDTO international = new InternazionalizationDTO();
		international.setMovie(rset.getString("idMovie"));
		international.setItTitle(rset.getString("itTitle"));
		international.setEngTitle(rset.getString("engTitle"));
		
		return international;
	}
	
}