package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dto.GenereDTO;
import com.dto.LabelDTO;

public class LabelMapper implements RowMapper<LabelDTO> {


	@Override
	public LabelDTO mapRow(ResultSet rset, int arg1)
			throws SQLException {
		
		LabelDTO label = new LabelDTO();
		label.setName(rset.getString("label"));
		
		return label;
	}
	


}
