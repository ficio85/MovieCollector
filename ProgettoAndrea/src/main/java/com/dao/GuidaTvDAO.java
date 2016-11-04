package com.dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dto.ActorDTO;
import com.dto.GenereDTO;
import com.dto.LabelDTO;
import com.dto.MovieDTO;
import com.dto.ProgramMovieDTO;
import com.dto.ProgramTvMovieDTO;
import com.dto.UserGuidaTvDTO;
import com.dto.UserMovieRateDTO;
import com.mapper.LabelMapper;
import com.mapper.ProgramTvMapper;
import com.mapper.UserGuidaTvMapper;
import com.util.DateUtil;

@Repository("guidaTvDAO")
public class GuidaTvDAO {
	
	@Autowired
	@Qualifier("jdbcTemplate")
	NamedParameterJdbcTemplate jdbcTemplate;
	public int insertProgrammaTv(ProgramTvMovieDTO program) {

		// TODO Auto-generated method stub

		int result = 0;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("movieKey", program.getMovie().getMovieKey());
		parameters.addValue("time", program.getOraInizio());
		parameters.addValue("channel", program.getChannel());
		parameters.addValue("title", program.getTitolo());
		parameters.addValue("type", program.getTipo());
		parameters.addValue("platform", program.getPlatform());


		try {
			result=jdbcTemplate.update("INSERT INTO `prog1_schema`.`movietv`(`movie`,`time`,`channel`,`title`,`type`,`platform`) VALUES(:movieKey,:time,:channel,:title,:type,:platform)", parameters);
		}
		catch(DuplicateKeyException e)
		{
			e.printStackTrace();

		}
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;



}
	
	public int insertUserGuidaTv(UserMovieRateDTO movieRate) {

		// TODO Auto-generated method stub

		int result = 0;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("movie", movieRate.getMovie());
		parameters.addValue("user", movieRate.getUser());
		parameters.addValue("like", movieRate.getRate());
		parameters.addValue("time", (DateUtil.getDateofNow()));
	

		try {
			result=jdbcTemplate.update("INSERT INTO `prog1_schema`.`usermovietv`(`movie`,`user`,`rate`,`time`) VALUES(:movie,:user,:like,:time)", parameters);
		}
		catch(DuplicateKeyException e)
		{
			e.printStackTrace();

		}
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;



}
	

	public List<ProgramTvMovieDTO> getFirstProgrammaTv() {

		// TODO Auto-generated method stub

		MapSqlParameterSource parameters = new MapSqlParameterSource();


		 List<ProgramTvMovieDTO> result;
		try {
			result=jdbcTemplate.query(" select * from movietv  limit 1 ", parameters, new ProgramTvMapper());
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;



}
	
	public List<UserGuidaTvDTO> getPreferredUserGuidaTv(String user) {

		// TODO Auto-generated method stub

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("user", user);
		parameters.addValue("time", DateUtil.getSqlDateofNow());

		 List<UserGuidaTvDTO> result;
		try {
			result=jdbcTemplate.query(" select * from usermovietv where user=:user and time=:time and rate= (select max(rate) from usermovietv where user=:user)", parameters, new UserGuidaTvMapper());
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;



}
	
	public int deleteGuidaTv()
	{

		int result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();


		try {
			result=jdbcTemplate.update("delete from movietv ",parameters);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;		
	}
	
	public int deleteUserGuidaTv(String user)
	{

		int result;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("user", user);

		try {
			result=jdbcTemplate.update("delete from usermovietv where user=:user ",parameters);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;		
	}
	
	public List<ProgramTvMovieDTO> getMovieTvList(Timestamp dateBegin, Timestamp dateEnd, String channel,
			String platform, String type) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("timeBegin", dateBegin);
		parameters.addValue("timeEnd", dateEnd);
		if(channel!=null&&!channel.trim().equals(""))
		{
			parameters.addValue("channel",channel);
		}
		if(platform!=null && !platform.trim().equals(""))
		{
			parameters.addValue("platform", platform);
		}
		if(type!=null && !type.trim().equals(""))
		{
			parameters.addValue("type", type);
		}

		

		String sql="SELECT * from movietv where time >= :timeBegin and time <= :timeEnd  ";
		if(channel!=null && !channel.trim().equals(""))
		{
		  sql+= " and channel=:channel ";
		}
		if(platform!=null && !platform.trim().equals(""))
		{
			sql+=" and platform=:platform ";
		}
		if(type!=null && !type.trim().equals(""))
		{
			sql+= "and type=:type " ;
		}		
		sql+="order by time asc";
		return jdbcTemplate.query(sql,parameters,new ProgramTvMapper());


	}


	



}
