package com.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import com.dao.ActorDAO;
import com.dao.GenereDAO;
import com.dao.LabelDAO;
import com.dao.MovieRankDAO;
//import com.dao.GenereDAO;
import com.dao.SearchMovieDAO;
import com.dto.ActorDTO;
import com.dto.GenereDTO;
import com.dto.LabelDTO;
import com.dto.MovieDTO;
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

		ArrayList <String> indexes= new ArrayList(Arrays.asList(index));
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
	
	
//	public List<GenereDTO> getListaLabelbySearch(String name) {
//		// TODO Auto-generated method stub
//
//		return labelDAO.getListaLabelbySearch(name);
//	
//	}
//	
	

}
