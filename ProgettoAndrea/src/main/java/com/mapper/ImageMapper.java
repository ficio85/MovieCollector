package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dto.DirectorDTO;
import com.dto.ImageDTO;
import com.dto.MovieDTO;

public class ImageMapper implements RowMapper<ImageDTO>{

	@Override
	public ImageDTO mapRow(ResultSet rset, int arg1)
			throws SQLException {
		
		ImageDTO image = new ImageDTO();
		image.setHeight(rset.getString("heigth"));
		image.setSrc(rset.getString("image"));
		image.setWidth(rset.getString("width"));
	
//		actor.setIndexList(rset.getInt("indexList"));	
		return image;
	}
	
}
