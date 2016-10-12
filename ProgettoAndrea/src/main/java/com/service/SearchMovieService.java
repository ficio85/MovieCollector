package com.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import com.dao.ActorDAO;
import com.dao.DirectorDAO;
import com.dao.GenereDAO;
import com.dao.LabelDAO;
import com.dao.MovieRankDAO;
//import com.dao.GenereDAO;
import com.dao.SearchMovieDAO;
import com.dto.ActorDTO;
import com.dto.DirectorDTO;
import com.dto.GenereDTO;
import com.dto.LabelDTO;
import com.dto.MovieDTO;
import com.dto.ProgramTvMovieDTO;
import com.dto.SearchDTO;

@Service("searchMovieService")
public class SearchMovieService {




	@Autowired
	@Qualifier("searchMovieDAO")
	SearchMovieDAO searchMovieDAO;


	@Autowired
	@Qualifier("genereDAO")
	GenereDAO genereDAO;

	@Autowired
	@Qualifier("labelDAO")
	LabelDAO labelDAO;
	
	@Autowired
	@Qualifier("rateDAO")
	MovieRankDAO rateDAO;

	@Autowired
	@Qualifier("actorDAO")
	ActorDAO actorDAO;
	
	@Autowired
	@Qualifier("directorDAO")
	DirectorDAO directorDAO;
	
	public List <MovieDTO> getListaFilm(SearchDTO search){

		List <String> codMovies = new ArrayList <String>();
		List<MovieDTO> movies = null;
		if(search.isSearchTitle())
		{
			movies= searchMovieDAO.getMovieLikeTitle(search.getTitle());
		}
		else
		{
			int offset=search.getStart();
			int limit=search.getOffset();

			if(search.isSearchActor())
			{
				if(!codMovies.isEmpty())
				{
					codMovies=searchMovieDAO.getMoviesByActor( search.getActors(),codMovies,0,0,search.isAndActors(),false);
				}
				else
				{
					codMovies=searchMovieDAO.getMoviesByActor( search.getActors(),null,0,0,search.isAndActors(),false);
				}
			}
			if(search.isSearchDirector())
			{
				if(!codMovies.isEmpty())
				{
					codMovies=searchMovieDAO.getMoviesByDirector( search.getDirectors(),codMovies);
				}
				else
				{
					codMovies = searchMovieDAO.getMoviesByDirector( search.getDirectors());
				}
			}
			if(search.isSearchLabel())
			{
				if(!codMovies.isEmpty())
				{
					codMovies=searchMovieDAO.getMoviesByLabel( search.getLabels(),codMovies,0,0,search.isAndActors(),false);
				}
				else
				{
					codMovies=searchMovieDAO.getMoviesByLabel( search.getLabels(),null,0,0,search.isAndActors(),false);
				}
			}
			if(search.isSearchGenre())
			{
				if(!codMovies.isEmpty())
				{

					codMovies=searchMovieDAO.getMoviesByGenre( search.getGenres(),codMovies,0,0,search.isAndGenres(),false);
				}
				else
				{

					codMovies=searchMovieDAO.getMoviesByGenre( search.getGenres(),null, 0,0,search.isAndGenres(),false);
				}
			}
			if(search.isSearchYear())
			{
				if(!codMovies.isEmpty())
				{
					codMovies=searchMovieDAO.getMoviesByYear( search.getYears(), codMovies,0,0);
				}
				else
				{
					codMovies=searchMovieDAO.getMoviesByYear( search.getYears(),null, 0,0);
				}
			}

			if(!codMovies.isEmpty())
			{
				if(search.isCount())
				{
					search.setCountResult(codMovies.size());
				}
				movies =searchMovieDAO.getMoviesByIndex(codMovies,offset, limit,false);
			}
		}

		if(movies!=null && movies.size()!=0)
		{
			completeMovies(movies);
		}

		return movies;

	}


	public MovieDTO getAllMovieDetail(String index, String user){

		ArrayList <String> indexes= new ArrayList<>(Arrays.asList(index));
		MovieDTO movieDto = searchMovieDAO.getMoviesByIndex(indexes, 0, 0,true).get(0);
		movieDto.setTitoloItaliano(searchMovieDAO.getMovieInternationalization(movieDto.getMovieKey()));
		movieDto.setActors(searchMovieDAO.getMovieActors(movieDto.getMovieKey()));
		movieDto.setGenre(searchMovieDAO.getMovieGenre(movieDto.getMovieKey()));
		movieDto.setDirectors(searchMovieDAO.getMovieDirector(movieDto.getMovieKey()));
		movieDto.setLanguages(searchMovieDAO.getMovieLanguages(movieDto.getMovieKey()));
		movieDto.setCountries(searchMovieDAO.getMovieCountry(movieDto.getMovieKey()));
		movieDto.setWriters(searchMovieDAO.getMovieWriter(movieDto.getMovieKey()));
		//get user generated content
		movieDto.setLabels(searchMovieDAO.getUserMovieLabels(movieDto.getMovieKey(),user));
		//		movieDto.setUserRanking(searchMovieDAO.getUserMovieRanking(movieDto.getMovieKey(),user));
		return movieDto;



	}

	private void completeMovies(List<MovieDTO> movies) {


		for(MovieDTO movieDTO:movies)
		{
			movieDTO.setTitoloItaliano(searchMovieDAO.getMovieInternationalization(movieDTO.getMovieKey()));
			movieDTO.setActors(searchMovieDAO.getMovieActors(movieDTO.getMovieKey()));
			movieDTO.setGenre(searchMovieDAO.getMovieGenre(movieDTO.getMovieKey()));
			movieDTO.setDirectors(searchMovieDAO.getMovieDirector(movieDTO.getMovieKey()));
			movieDTO.setLabels(labelDAO.getListaLabelbyMovie(movieDTO.getMovieKey()));
		}
	}

	private void completeMovie(MovieDTO movieDTO) {
			
			movieDTO.setTitoloItaliano(searchMovieDAO.getMovieInternationalization(movieDTO.getMovieKey()));
			movieDTO.setActors(searchMovieDAO.getMovieActors(movieDTO.getMovieKey()));
			movieDTO.setGenre(searchMovieDAO.getMovieGenre(movieDTO.getMovieKey()));
			movieDTO.setDirectors(searchMovieDAO.getMovieDirector(movieDTO.getMovieKey()));
			movieDTO.setLabels(labelDAO.getListaLabelbyMovie(movieDTO.getMovieKey()));
		
	}

	public List<GenereDTO> getListaGeneri() {
		// TODO Auto-generated method stub

		List <GenereDTO> generi =genereDAO.getListaGeneri();
		return generi;
	}
	
	public List<LabelDTO> getListaLabel(String search) {
		// TODO Auto-generated method stub

		List <LabelDTO> labels =labelDAO.getListaLabelbySearch(search);
		return labels;
	}

	public float getMovieRate(List<String> indexes) {
		// TODO Auto-generated method stub

		float rate =searchMovieDAO.getMoviesByIndex(indexes, 0, 0,true).get(0).getRate();
		return rate;
	}


	public ActorDTO getAllActorDetail(String name) {
		// TODO Auto-generated method stub
		return actorDAO.getActorsDetail(name);
	}

	public DirectorDTO getAllDirectorDetail(String director) {
		// TODO Auto-generated method stub
		return directorDAO.getDirectorDetail(director);
	}

	


	


	public List<ProgramTvMovieDTO> getProgrammiTv(String user, Timestamp dateBegin, Timestamp dateEnd) {
		// TODO Auto-generated method stub
		
		List<ProgramTvMovieDTO> programmiTv = searchMovieDAO.getMovieTvList(dateBegin,dateEnd);
		
		for(ProgramTvMovieDTO programma : programmiTv)
		{
			if(!programma.getMovie().getMovieKey().equals("NONPRESENTE"))
			programma.setMovie(getAllMovieDetail(programma.getMovie().getMovieKey(), user));
		}
		return programmiTv;
	}


	public List<MovieDTO> extractMovieForTvGuide(MovieDTO movie) {
		// TODO Auto-generated method stub
		List <MovieDTO> listMovie= null;
		List <Integer> years= new ArrayList <Integer>();
		int year = movie.getYear();
		int yearPrec= -1;
		int yearSuc = year+1;
		years.add(yearPrec);
		years.add(yearSuc);
		years.add(year);
		//TODO
		//questo è da rifare completamente, si baserà sul processo di traduzione sostanzialmente,
		//in pratica vedo se c'è il titolo sulla tabella di traduzione (ricerca titolo e anno - ricerca titolo e regista-- ricerca titolo e anno rilassato--
		// se non c'è avvio il processo di traduzione! che ho già fatto per la ricerca/inserimento in pratica.
	
		if(movie.getDirectors()!=null && !movie.getDirectors().isEmpty())
		{
			 listMovie = searchMovieDAO.getMovieByDirectorYear(movie.getYear(), movie.getDirectors());

		}
		 if((listMovie==null || listMovie.size()==0) && (movie.getDirectors()!=null && !movie.getDirectors().isEmpty()))
		 {
			 listMovie= searchMovieDAO.getMovieByDirectorTitle(movie.getTitle(), movie.getDirectors());
		 }
		 
		 //ultima prova rilasso il vincolo sull'anno
		 if((listMovie ==null || listMovie.size()==0) && (movie.getDirectors()!=null && !movie.getDirectors().isEmpty()))
		 {
			 listMovie=searchMovieDAO.getMovieByDirectorAndYears(years, movie.getDirectors().get(0).getName());
		 }
		 return listMovie;

		
	}


	


	
	
	
//	public List<GenereDTO> getListaLabelbySearch(String name) {
//		// TODO Auto-generated method stub
//
//		return labelDAO.getListaLabelbySearch(name);
//	
//	}
//	
	

}
