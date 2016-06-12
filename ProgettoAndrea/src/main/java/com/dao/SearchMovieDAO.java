package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.controller.ProgramTvMovieDTO;
import com.dto.ActorDTO;
import com.dto.CountryDTO;
import com.dto.DirectorDTO;
import com.dto.GenereDTO;
import com.dto.LabelDTO;
import com.dto.LanguageDTO;
import com.dto.MovieDTO;
import com.dto.WriterDTO;
import com.mapper.ActorMapper;
import com.mapper.CountryMapper;
import com.mapper.DirectorMapper;
import com.mapper.GenereMapper;
import com.mapper.LabelMapper;
import com.mapper.LanguageMapper;
import com.mapper.MovieMapper;
import com.mapper.MovieMapperComplete;
import com.mapper.ProgramTvMapper;
import com.mapper.WriterMapper;

@Repository("searchMovieDAO")
public class SearchMovieDAO {

	@Autowired
	@Qualifier("jdbcTemplate")
	NamedParameterJdbcTemplate jdbcTemplate;	


	public List<String> getMoviesByActor(List <String> actors) {
		// TODO Auto-generated method stub
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("namesActor", actors);
		List<String> result;
		try {
			result=jdbcTemplate.queryForList(" SELECT distinct movie FROM movieactor where actor in (:namesActor) ",parameters, String.class);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}

		return  result;


	}

	public List<String> getMoviesByActor(List <String> actors, List <String> indexes) {
		// TODO Auto-generated method stub
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("namesActor", actors);
		parameters.addValue("indexes", indexes);
		List<String> result;
		try {
			result=jdbcTemplate.queryForList(" SELECT distinct movie FROM movieactor where actor in (:namesActor) and indexes in (:indexes) ",parameters, String.class);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}

		return  result;


	}


	public List<String> getMoviesByYear(List <Integer> years,List <String> indexes,int offset, int limit) {
		// TODO Auto-generated method stub
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("years", years);
		if(indexes!=null)
		{
			parameters.addValue("indexes", indexes);
		}
		if(limit != 0) 
		{
			parameters.addValue("limit", limit);
			parameters.addValue("offset", offset);
		}

		String sql="SELECT idmovie FROM movie where year in (:years) ";
		if(indexes!=null)
		{
			sql+=" and idmovie in (:indexes) ";
		}
		if(limit!=0)
		{
			sql+=" LIMIT :limit OFFSET :offset ";
		}

		List<String> result;
		try {
			result=jdbcTemplate.queryForList(sql,parameters, String.class);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}

		return  result;

	}



	public List<String> getMoviesByDirector(List <String> directors) {
		// TODO Auto-generated method stub
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("namesDirector", directors);
		List<String> result;
		try {
			result=jdbcTemplate.queryForList(" SELECT distinct movie FROM moviedirector where director in (:namesDirector) ",parameters, String.class);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}

		return  result;


	}

	public List<String> getMoviesByDirector(List <String> directors, List <String> indexes) {
		// TODO Auto-generated method stub
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("namesDirector", directors);
		parameters.addValue("indexes", indexes);

		List<String> result;
		try {
			result=jdbcTemplate.queryForList(" SELECT distinct movie FROM moviedirector where movie in (:indexes) and director in (:namesDirector) ",parameters, String.class);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}

		return  result;


	}









	public List<String> getMoviesByGenre(List<String> genres, List<String> indexes,int offset, int limit, boolean andGenre,boolean count) {
		// TODO Auto-generated method stub
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("genres", genres);
		parameters.addValue("indexes", indexes);
		parameters.addValue("limit", limit);
		parameters.addValue("offset", offset);


		if(indexes!=null)
		{
			parameters.addValue("indexes", indexes);
		}
		if(limit != 0) 
		{
			parameters.addValue("limit", limit);
			parameters.addValue("offset", offset);
		}
		String sql;



		sql="SELECT distinct movie FROM moviegenre where genre" ;


		if(andGenre)
		{
			parameters.addValue("count", genres.size());

			sql+=" in (:genres) group by movie having count(movie) = :count ";
		}
		else
		{
			sql+=" in (:genres) ";
		}
		if(indexes!=null)
		{
			sql+=" and movie in (:indexes) ";
		}
		if(limit!=0)
		{
			sql+=" LIMIT :limit OFFSET :offset ";
		}

		if(count)
		{

			String sql2="SELECT count(*) FROM moviegenre where movie in ("+sql+")";
			sql=sql2;

		}

		List<String> result;
		try {
			result=jdbcTemplate.queryForList(sql,parameters, String.class);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}

		return  result;


	}

	public List<String> getMoviesByActor(List<String> actors, List<String> indexes,int offset, int limit, boolean andActors,boolean count) {
		// TODO Auto-generated method stub
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("actors", actors);
		parameters.addValue("indexes", indexes);
		parameters.addValue("limit", limit);
		parameters.addValue("offset", offset);


		if(indexes!=null)
		{
			parameters.addValue("indexes", indexes);
		}
		if(limit != 0) 
		{
			parameters.addValue("limit", limit);
			parameters.addValue("offset", offset);
		}
		String sql;



		sql="SELECT distinct movie FROM movieactor where actor" ;


		if(andActors)
		{
			parameters.addValue("count", actors.size());

			sql+=" in (:actors) group by movie having count(movie) = :count ";
		}
		else
		{
			sql+=" in (:actors) ";
		}
		if(indexes!=null)
		{
			sql+=" and movie in (:indexes) ";
		}
		if(limit!=0)
		{
			sql+=" LIMIT :limit OFFSET :offset ";
		}

		if(count)
		{

			String sql2="SELECT count(*) FROM movieactor where movie in ("+sql+")";
			sql=sql2;

		}

		List<String> result;
		try {
			result=jdbcTemplate.queryForList(sql,parameters, String.class);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}

		return  result;


	}

	public List<String> getMoviesByLabel(List<String> labels, List<String> indexes,int offset, int limit, boolean andLabels,boolean count) {
		// TODO Auto-generated method stub
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("labels", labels);
		parameters.addValue("indexes", indexes);
		parameters.addValue("limit", limit);
		parameters.addValue("offset", offset);


		if(indexes!=null)
		{
			parameters.addValue("indexes", indexes);
		}
		if(limit != 0) 
		{
			parameters.addValue("limit", limit);
			parameters.addValue("offset", offset);
		}
		String sql;



		sql="SELECT distinct movie FROM movielabel where label" ;


		if(andLabels)
		{
			parameters.addValue("count", labels.size());

			sql+=" in (:labels) group by movie having count(movie) = :count ";
		}
		else
		{
			sql+=" in (:labels) ";
		}
		if(indexes!=null)
		{
			sql+=" and movie in (:indexes) ";
		}
		if(limit!=0)
		{
			sql+=" LIMIT :limit OFFSET :offset ";
		}

		if(count)
		{

			String sql2="SELECT count(*) FROM movielabel where movie in ("+sql+")";
			sql=sql2;

		}

		List<String> result;
		try {
			result=jdbcTemplate.queryForList(sql,parameters, String.class);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}

		return  result;

	}



	public  List<MovieDTO> getMoviesByIndex(List <String> indexes,int offset, int limit,boolean complete)
	{
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("indexes", indexes);
		parameters.addValue("limit", limit);
		parameters.addValue("offset", offset);

		String sql="";

		sql +="SELECT `idmovie`,`name`,`length`,`imdbRating`,`year`,`plot`,`metacritic`,`numImdbRating`,`indexImdb`,`release`,`rated`,`awards`,`poster`,`type`,`rate` ";



		sql += " FROM movie where idmovie in (:indexes) order by year asc ";
		if(limit!=0)
		{
			sql+="LIMIT :limit OFFSET :offset";
		}
		if(complete)
		{
			return jdbcTemplate.query(sql,parameters,new MovieMapperComplete());

		}
		else
		{
			return jdbcTemplate.query(sql,parameters,new MovieMapper());

		}

	}

	public  int getCountMoviesByIndex(List <String> indexes)
	{
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("indexes", indexes);
		String sql="";

		sql+="select count(*) FROM movie where idmovie in (:indexes) order by year asc";


		return jdbcTemplate.queryForInt(sql,parameters);

	}

	public  List<MovieDTO> getMoviesByTitle(List <String> indexes)
	{
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("indexes", indexes);
		String sql ="SELECT `idmovie`,`name`,`length`,`imdbRating`,`year`,`plot`,`metacritic`,`numImdbRating`,`indexImdb`,`release`,`rated`,`awards`,`poster`,`type`"
				+ " FROM movie where name in (:indexes)";
		return jdbcTemplate.query(sql,parameters,new MovieMapper());

	}

	public  List<MovieDTO> getMovieByTitleAndYear(String name, int year)
	{
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("name", name);
		parameters.addValue("year", year);

		String sql ="SELECT `idmovie`,`name`,`length`,`imdbRating`,`year`,`plot`,`metacritic`,`numImdbRating`,`indexImdb`,`release`,`rated`,`awards`,`poster`,`type`"
				+ " FROM movie where year=:year and name =:name";
		return jdbcTemplate.query(sql,parameters,new MovieMapper());

	}



	public String getMovieInternationalization(String key) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("key", key);
		String result;
		try {
			result=jdbcTemplate.queryForObject(" SELECT itTitle FROM internationalization where idMovie= :key ",parameters, String.class);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}

		return  result;


	}



	public List<ActorDTO> getMovieActors(String key) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("key", key);
		List<ActorDTO> result;
		try {
			result=jdbcTemplate.query(" SELECT * FROM movieactor where movie= :key ",parameters, new ActorMapper());
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}

		return  result;


	}




	public List<GenereDTO> getMovieGenre(String key) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("key", key);
		List<GenereDTO> result;
		try {
			result=jdbcTemplate.query(" SELECT * FROM moviegenre,genre where codGenre=genre and movie = :key ",parameters, new GenereMapper());
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}

		return  result;


	}


	public List<DirectorDTO> getMovieDirector(String key) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("key", key);
		List<DirectorDTO> result;
		try {
			result=jdbcTemplate.query(" SELECT * FROM moviedirector where movie = (:key) ",parameters, new DirectorMapper());
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}

		return  result;


	}


	public List<LanguageDTO> getMovieLanguages(String key) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("key", key);
		List<LanguageDTO> result;
		try {
			result=jdbcTemplate.query(" SELECT * FROM movielanguage movlang,language lan where lan.cod=movlang.language and movie = (:key) ",parameters, new LanguageMapper());
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}

		return  result;


	}

	public List<CountryDTO> getMovieCountry(String key) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("key", key);
		List<CountryDTO> result;
		try {
			result=jdbcTemplate.query(" SELECT * FROM movienation,nations where movienation.nation=nations.idnations and movie = (:key) ",parameters, new CountryMapper());
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}

		return  result;


	}


	public List<WriterDTO> getMovieWriter(String key) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("key", key);
		List<WriterDTO> result;
		try {
			result=jdbcTemplate.query(" SELECT * FROM moviewriter where movie = (:key) ",parameters, new WriterMapper());
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}

		return  result;


	}






	public List<MovieDTO> getMovieByDirectorTitle(String title, List<DirectorDTO> directors) {
		// TODO Auto-generated method stub
		String nameDirector =directors.get(0).getName();
		// TODO Auto-generated method stub
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("director", nameDirector);
		parameters.addValue("title", title);

		List<MovieDTO> result;
		try {
			result=jdbcTemplate.query(" SELECT * FROM moviedirector dir,movie mov where dir.movie = mov.idmovie"
					+ " and director = :director and name=:title ",parameters, new MovieMapper());
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}

		return  result;


	}

	public List<MovieDTO> getMovieByDirectorYear(int year, List<DirectorDTO> directors) {
		// TODO Auto-generated method stub
		String nameDirector =directors.get(0).getName();
		// TODO Auto-generated method stub
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("director", nameDirector);
		parameters.addValue("year", year);

		List<MovieDTO> result;
		try {
			result=jdbcTemplate.query(" SELECT * FROM moviedirector dir,movie mov where dir.movie = mov.idmovie"
					+ " and director = :director and year=:year ",parameters, new MovieMapper());
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}

		return  result;


	}










	public List<MovieDTO> getMovieByDirectorActor(String actor, List<DirectorDTO> directors, int year) {
		// TODO Auto-generated method stub
		String nameDirector =directors.get(0).getName();
		// TODO Auto-generated method stub
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("director", nameDirector);
		parameters.addValue("year", year);
		parameters.addValue("actor",actor );

		List<MovieDTO> result;
		try {
			result=jdbcTemplate.query(" SELECT * FROM moviedirector dir,movieactor act,movie mov where dir.movie = mov.idmovie and act.movie=mov.idmovie "
					+ "and director = :director and year=:year and actor=:actor ",parameters, new MovieMapper());
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}

		return  result;


	}












	public List<MovieDTO> getMovieByTitleAndYears(String title, List<Integer> movieYears) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("name", title);
		parameters.addValue("years", movieYears);

		String sql ="SELECT `idmovie`,`name`,`length`,`imdbRating`,`year`,`plot`,`metacritic`,`numImdbRating`,`indexImdb`,`release`,`rated`,`awards`,`poster`,`type`"
				+ " FROM movie where year in (:years) and name =:name";
		return jdbcTemplate.query(sql,parameters,new MovieMapper());

	}












	public List<MovieDTO> getMovieByActorTitle(String title, String actor) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("actor", actor);
		parameters.addValue("title", title);

		List<MovieDTO> result;
		try {
			result=jdbcTemplate.query(" SELECT * FROM movieactor act,movie mov where act.movie = mov.idmovie"
					+ " and actor = :actor and name=:title ",parameters, new MovieMapper());
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}

		return  result;


	}

	public Object getUserMovieRanking(String movieKey, String user) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("key", movieKey);
		parameters.addValue("user", user);

		List<LabelDTO> result;
		try {
			result=jdbcTemplate.query(" SELECT * FROM usermovielabel where movie= :key and user=:user",parameters, new LabelMapper());
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}

		return  result;


	}

	public List<LabelDTO> getUserMovieLabels(String movieKey, String user) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("key", movieKey);
		parameters.addValue("user", user);

		List<LabelDTO> result;
		try {
			result=jdbcTemplate.query(" SELECT * FROM usermovielabel where movie= :key and user=:user",parameters, new LabelMapper());
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}

		return  result;


	}

	public List<MovieDTO> getMovieLikeTitle(String title) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("title", title);


		String sql="";

		sql +="SELECT `idmovie`,`name`,`length`,`imdbRating`,`year`,`plot`,`metacritic`,`numImdbRating`,`indexImdb`,`release`,`rated`,`awards`,`poster`,`type`,`rate`";



		sql += " FROM movie where name = :title order by year asc ";

		return jdbcTemplate.query(sql,parameters,new MovieMapperComplete());





	}

	public List<ProgramTvMovieDTO> getMovieTvList() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();


		String sql="";

		sql +="SELECT `movie`,`channel`,`time` from movietv order by time asc ";

		return jdbcTemplate.query(sql,parameters,new ProgramTvMapper());


	}




}
