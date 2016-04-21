package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.swing.tree.TreePath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.dto.MovieDTO;
import com.eccezione.ExceptionInsert;
import com.menu.ProvaDTO;
import com.util.MovieGeneratorUtil;

@Repository("movieDAO")
public class InsertMovieDAO {

	

	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;	
	public int insertMovie (MovieDTO movieDto)
	{
		int result=0;

		try{		

			result = jdbcTemplate.update("INSERT INTO movie (`idmovie`,`name`,`length`,`imdbRating`,`year`,`plot`,`metacritic`,`numImdbRating`,`indexImdb`,`release`,`rated`,`awards`,`poster`,`type`) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
					new Object[]{movieDto.getMovieKey(), movieDto.getTitle(),movieDto.getLength(),movieDto.getImdbRating(),movieDto.getYear(),movieDto.getPlot(),movieDto.getMetaScore(),movieDto.getNumImdbRating(),movieDto.getImdbKey(),movieDto.getReleaseDate(),movieDto.getRated(),movieDto.getAwards(),movieDto.getPoster(),movieDto.getType()});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return result;


	}



	public int deleteAllMovies ()
	{
		int result=0;

		try{		

			result = jdbcTemplate.update("DELETE  from movie ",new Object[]{});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return result;


	}

	public String isMoviePresent(MovieDTO movieDto) 
	{
		String result="";
		try {
			result=jdbcTemplate.queryForObject(" SELECT 1 FROM movie where name= ? ", new Object[] { movieDto.getTitle() }, String.class);
		} 
		catch(EmptyResultDataAccessException e)
		{
			result= "EMPTY";
		}
		catch(Exception e)
		{
			result="ERROR";
		}
		return result;

	}

	public boolean isPresentCountry(String country) {
		// TODO Auto-generated method stub
		String cod="";
		if(country.trim().length()>3)
		{
			 cod=country.trim().substring(0, 3).toUpperCase();
		}
		else
		{
			cod=country.trim().toUpperCase();
		}

		String result="";
		try {
			result=jdbcTemplate.queryForObject(" SELECT 1 FROM nations where idnations= ? ", new Object[] { cod }, String.class);
		} 
		catch(EmptyResultDataAccessException e)
		{
			result= "EMPTY";
		}
		catch(Exception e)
		{
			result="ERROR";
		}
		if(result.equals("1"))
			return true;
		else return false;



	}
	
	
	public List <MovieDTO> getInternazionalization(MovieDTO movie) {
		// TODO Auto-generated method stub

		List<MovieDTO> result;
		try {
			result=jdbcTemplate.query(" SELECT * FROM internationalization where engTitle= ? ", new Object[] { movie.getTitle() }, new DataInternationalization());
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return  result;
		

	}
	
	private class DataInternationalization implements RowMapper<MovieDTO>{

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
	
	
	
	public int insertInternazionalization(MovieDTO movie) {
		// TODO Auto-generated method stub

		int result=0;
		try {
			result = jdbcTemplate.update("INSERT INTO internationalization(`idMovie`,`engTitle`,`itTitle`) VALUES(?,?,?)",new Object[]{movie.getMovieKey(),movie.getTitle(), movie.getTitoloItaliano()}); 
		}
		catch(Exception e)
		{
			result=0;
		}
		return result;
		
	}
	
	public int updateInternazionalization(MovieDTO movie) {
		// TODO Auto-generated method stub

		int result=0;
		try {
			result = jdbcTemplate.update("UPDATE internationalization SET engTitle = ?,itTitle = ? WHERE idMovie = ? ",new Object[]{movie.getTitle(), movie.getTitoloItaliano(), movie.getMovieKey()}); 
		}
		catch(Exception e)
		{
			result=0;
		}
		return result;
		
	}

	public int insertCountry(String country) {

		int result=0;

		String desCountry=country.toUpperCase();
		String codCountry="";
		if(desCountry.trim().length()>3)
		{
			 codCountry =desCountry.trim().substring(0, 3).toUpperCase();
		}
		else
		{
			codCountry =desCountry.trim().toUpperCase();
		}

		try{		

			result = jdbcTemplate.update("INSERT INTO nations (`idnations`,`desnations`) values ( ?, ?)", new Object[]{codCountry,desCountry});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return result;



	}

	public int deleteAllCountries()
	{
		int result=0;

		try{		

			result = jdbcTemplate.update("DELETE from nations",new Object[]{});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return result;


	}

	public boolean isPresentGenre(String genre) {
		// TODO Auto-generated method stub
		String cod=genre.trim().substring(0, 3).toUpperCase();

		String result="";
		try {
			result=jdbcTemplate.queryForObject(" SELECT 1 FROM genre where codGenre= ? ", new Object[] { cod }, String.class);
		} 
		catch(EmptyResultDataAccessException e)
		{
			result= "EMPTY";
		}
		catch(Exception e)
		{
			result="ERROR";
		}
		if(result.equals("1"))
			return true;
		else return false;



	}
	
	

	public int insertGenre(String genre) {

		int result=0;

		String desGenre=genre.toUpperCase().trim();
		String codGenre=desGenre.substring(0, 3);

		try{		

			result = jdbcTemplate.update("INSERT INTO genre (`codGenre`,`desGenre`) values ( ?, ?)", new Object[]{codGenre,desGenre});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return result;



	}

	public int insertActor(String actor) {

		int result=0;


		try{		

			result = jdbcTemplate.update("INSERT INTO actor (`name`) values ( ?)", new Object[]{actor});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return result;
	}
	
	public int insertWriter(String writer) {

		int result=0;


		try{		

			result = jdbcTemplate.update("INSERT INTO writer (`name`) values (  ?)", new Object[]{writer});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return result;


	}
	
	public int insertDirector(String director) {

		int result=0;


		try{		

			result = jdbcTemplate.update("INSERT INTO director (`name`) values ( ?)", new Object[]{director});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return result;


	}
	
	public int insertLanguage(String language) {

		int result=0;
		String cod=language.trim().toUpperCase().substring(0, 3);

		try{		

			result = jdbcTemplate.update("INSERT INTO language (`cod`,`des`) values (?, ?)", new Object[]{cod,language});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return result;


	}
	
	public int insertLogEccezioni(ExceptionInsert excIns) {

		

		int result;
		try{		

			result = jdbcTemplate.update("INSERT INTO logeccezioni (`movieKey`,`movieTitle`,`logEccezione`) values ( ?, ?,?)", new Object[]{excIns.getMovieKey(),excIns.getMovieTitle(),excIns.getStackTrace()});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return result;



	}


	
	public boolean isPresentDirector(String actor) {
		// TODO Auto-generated method stub

		String result="";
		try {
			result=jdbcTemplate.queryForObject(" SELECT 1 FROM director where name= ? ", new Object[] { actor }, String.class);
		} 
		catch(EmptyResultDataAccessException e)
		{
			result= "EMPTY";
		}
		catch(Exception e)
		{
			result="ERROR";
		}
		if(result.equals("1"))
			return true;
		else return false;

	}
	
	public boolean isPresentLanguage(String language) {
		// TODO Auto-generated method stub

		String result="";
		String languageSearch=language.trim().substring(0, 3).toUpperCase();
		try {
			result=jdbcTemplate.queryForObject(" SELECT 1 FROM language where cod= ? ", new Object[] { languageSearch }, String.class);
		} 
		catch(EmptyResultDataAccessException e)
		{
			result= "EMPTY";
		}
		catch(Exception e)
		{
			result="ERROR";
		}
		if(result.equals("1"))
			return true;
		else return false;

	}

	
	
	public boolean isPresentActor(String actor) {
		// TODO Auto-generated method stub

		String result="";
		try {
			result=jdbcTemplate.queryForObject(" SELECT 1 FROM actor where name= ? ", new Object[] { actor }, String.class);
		} 
		catch(EmptyResultDataAccessException e)
		{
			result= "EMPTY";
		}
		catch(Exception e)
		{
			result="ERROR";
		}
		if(result.equals("1"))
			return true;
		else return false;

	}
	
	public boolean isPresentWriter(String writer) {
		// TODO Auto-generated method stub

		String result="";
		try {
			result=jdbcTemplate.queryForObject(" SELECT 1 FROM writer where name= ? ", new Object[] { writer }, String.class);
		} 
		catch(EmptyResultDataAccessException e)
		{
			result= "EMPTY";
		}
		catch(Exception e)
		{
			result="ERROR";
		}
		if(result.equals("1"))
			return true;
		else return false;

	}

	public int deleteActors()
	{
		int result=0;

		try{		

			result = jdbcTemplate.update("DELETE from actor ",new Object[]{});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return result;


	}
	
	public int deleteLanguage()
	{
		int result=0;

		try{		

			result = jdbcTemplate.update("DELETE from language ",new Object[]{});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return result;


	}
	
	public int deleteDirectors()
	{
		int result=0;

		try{		

			result = jdbcTemplate.update("DELETE from director ",new Object[]{});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return result;


	}

	public int deleteWriters()
	{
		int result=0;

		try{		

			result = jdbcTemplate.update("DELETE from writer ",new Object[]{});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return result;


	}
	
	
	
	public int deleteMovieActors()
	{
		int result=0;

		try{		

			result = jdbcTemplate.update("DELETE from movieactor ",new Object[]{});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return result;


	}





	public int deleteGenres()
	{
		int result=0;

		try{		

			result = jdbcTemplate.update("DELETE from genre ",new Object[]{});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return result;


	}

	public void insertMovieGenRel(MovieDTO movieDto) {

		String movieKey = movieDto.getMovieKey();
		List <String> movieGenre= movieDto.getGenre();

		int index=1;
		for(String genre: movieGenre)
		{
			if(MovieGeneratorUtil.isNotNullEntry(genre))
			{
				try{		

					jdbcTemplate.update("INSERT INTO moviegenre (`movie`,`genre`,`index`) values ( ?, ?,?)", new Object[]{movieDto.getMovieKey(), genre.trim().toUpperCase().substring(0, 3), index});					
					//				
				}

				catch(Exception e){
					e.printStackTrace();
					throw e;

				}
			}
			index++;
			
		}



	}

	public void insertMovieActorsRel(MovieDTO movieDto) {

		String movieKey = movieDto.getMovieKey();
		List <String> movieActors= movieDto.getActors();


		int index=1;
		for(String actor: movieActors)
		{
			if(MovieGeneratorUtil.isNotNullEntry(actor))
			{
				try{		

					jdbcTemplate.update("INSERT INTO movieactor (`movie`,`actor`,`index`) values ( ?,?,?)", new Object[]{movieDto.getMovieKey(),actor.trim(),index});					
					//				
				}

				catch(Exception e){
					e.printStackTrace();
					throw e;
				}
			}

			index++;
		}



	}
	
	public void insertMovieDirectorsRel(MovieDTO movieDto) {

		String movieKey = movieDto.getMovieKey();
		List <String> movieDirectors= movieDto.getDirectors();
		int index=1;

		for(String director: movieDirectors)
		{
			if(MovieGeneratorUtil.isNotNullEntry(director))
			{
				try{		

					jdbcTemplate.update("INSERT INTO moviedirector (`movie`,`director`,`index`) values ( ?,?,?)", new Object[]{movieDto.getMovieKey(), director.trim(), index});					
					//				
				}

				catch(Exception e){
					e.printStackTrace();
					throw e;
				}
			}

			index++;
		}



	}
	public void insertMovieLanguagesRel(MovieDTO movieDto) {

		String movieKey = movieDto.getMovieKey();
		List <String> movieLanguages= movieDto.getLanguages();
		int index=1;

		for(String language: movieLanguages)
		{
			if(MovieGeneratorUtil.isNotNullEntry(language))
			{
				String codLanguage=language.trim().substring(0, 3).toUpperCase();
				try{		

					jdbcTemplate.update("INSERT INTO movielanguage (`movie`,`language`,`index`) values ( ?,?,?)", new Object[]{movieDto.getMovieKey(), codLanguage, index});					
					//				
				}

				catch(Exception e){
					e.printStackTrace();
					throw e;
				}
			}

			index++;
		}
	}
	
	
	
	public void insertMovieWritersRel(MovieDTO movieDto) {

		String movieKey = movieDto.getMovieKey();
		List <String> movieWriters= movieDto.getWriters();
		int index=1;
int w=0;
		for(String writer: movieWriters)
		{
			if(MovieGeneratorUtil.isNotNullEntry(writer))
			{
				try{		
					if(!isPresentMovieWriterRel(movieDto.getMovieKey(),writer))
					{
						jdbcTemplate.update("INSERT INTO moviewriter (`movie`,`writer`,`work`,`index`) values ( ?,?,?,?)", new Object[]{movieDto.getMovieKey(), writer.trim(),movieDto.getWorkwriters().get(w),index});					

					}
					//				
				}

				catch(Exception e){
					e.printStackTrace();
					throw e;
				}
			}

			w++;
			index++;
		}



	}
	
	private boolean isPresentMovieWriterRel(String movieKey, String writer) {String result="";
	try {
		result=jdbcTemplate.queryForObject(" SELECT 1 FROM moviewriter where movie= ? and writer=? ", new Object[] { movieKey,writer }, String.class);
	} 
	catch(EmptyResultDataAccessException e)
	{
		result= "EMPTY";
	}
	catch(Exception e)
	{
		result="ERROR";
	}
	if(result.equals("1"))
		return true;
	else return false;}



	public void insertMovieNationsRel(MovieDTO movieDto) {

		String movieKey = movieDto.getMovieKey();
		List <String> countries= movieDto.getCountries();


		for(String country: countries)
		{
			if(MovieGeneratorUtil.isNotNullEntry(country))
			{
				String cod="";
				if(country.length()>3)
				{
					cod=country.trim().substring(0, 3).toUpperCase();
				}
				else
				{
					cod=country.trim().toUpperCase();
	
				} 

				try{		

					jdbcTemplate.update("INSERT INTO movienation (`movie`,`nation`) values ( ?,?)", new Object[]{movieDto.getMovieKey(), cod});					
					//				
				}

				catch(Exception e){
					e.printStackTrace();
					throw e;
				}
			}

			
		}



	}
	
	
	public boolean isPresentMovieActorsRel(MovieDTO movie, String nameActor) {
		// TODO Auto-generated method stub

		String result="";
		try {
			result=jdbcTemplate.queryForObject(" SELECT 1 FROM movieActor where movie= ?  and actor = ? ", new Object[] { movie.getMovieKey(), nameActor }, String.class);
		} 
		catch(EmptyResultDataAccessException e)
		{
			result= "EMPTY";
		}
		catch(Exception e)
		{
			result="ERROR";
		}
		if(result.equals("1"))
			return true;
		else return false;

	}
	
	public int deleteMovGenRel()
	{
		int result=0;

		try{		

			result = jdbcTemplate.update("DELETE from moviegenre ",new Object[]{});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return result;


	}
	
	public int deleteMovieNationRel()
	{
		int result=0;

		try{		

			result = jdbcTemplate.update("DELETE from movienation ",new Object[]{});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return result;


	}
	
	public int deleteMovieActorsRel()
	{
		int result=0;

		try{		

			result = jdbcTemplate.update("DELETE from movieactor ",new Object[]{});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return result;


	}



	public int deleteInternationalization() {

		int result=0;

		try{		

			result = jdbcTemplate.update("DELETE from internationalization ",new Object[]{});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return result;


			
	}



	public int deleteMovieWritersRel() {

		int result=0;

		try{		

			result = jdbcTemplate.update("DELETE from moviewriter ",new Object[]{});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return result;


			
	}



	public int deleteMovieDirectorsRel() {

		int result=0;

		try{		

			result = jdbcTemplate.update("DELETE from moviedirector ",new Object[]{});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return result;


			
	}


	public int deleteMovieLanguagesRel() {

		int result=0;

		try{		

			result = jdbcTemplate.update("DELETE from movielanguage ",new Object[]{});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return result;


			
	}



	public int insertSerie(MovieDTO movieDto) {

		int result=0;

		try{		

			result = jdbcTemplate.update("INSERT INTO serietv (`moviekey`,`imdbSerieKey`,`season`,`episode`) values (?, ?, ?, ?)", new Object[]{movieDto.getMovieKey(),movieDto.getImdbSerieKey(),movieDto.getSeason(),movieDto.getEpisode()});					
			//				
		}

		catch(Exception e){
			e.printStackTrace();
			throw e;

		}
		return result;


	}

}
