package com.menu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.swing.tree.TreePath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository("provaDAO")
public class ProvaDAO {


	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;	
	public ProvaDTO provaQuery ()
	{
		ProvaDTO user = new ProvaDTO();
		
		String sql ="SELECT * "
				+ " "
				+ " FROM USERS ";
		try{
			
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
//			if(rows.isEmpty()){
//				dto = modificaCodInsGraTipoInsGra(dto);
//			}
			for(Map<String, Object> row : rows){
			 user.setUser((String) row.get("USER"));
			 user.setPassword((String) row.get("CODPERS"));
			}
		}
			
		catch(Exception e){
			e.printStackTrace();
		}
		return user;
		
	
	}
	
	private class UserMapper implements RowMapper <ProvaDTO>
	{

		@Override
		public ProvaDTO mapRow(ResultSet rset, int arg1)
				throws SQLException {
			
			ProvaDTO dati =new ProvaDTO();
			dati.setPassword(rset.getString("USER"));
			dati.setUser(rset.getString("CODPERS"));					
			return dati;
		}
		
	}

}
