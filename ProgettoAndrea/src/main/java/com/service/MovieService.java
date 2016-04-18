package com.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.MovieDAO;
import com.dto.MovieDTO;
import com.eccezione.ExceptionInsert;
import com.util.MovieGeneratorUtil;

@Service("movieService")
public class MovieService {
	
	@Autowired
	@Qualifier("movieDAO")
	MovieDAO movieDAO;
	
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
	public int insertMovie(MovieDTO movieDto)
	{
		String result = movieDAO.isMoviePresent(movieDto);
		if(result.trim().equals("ERROR"))
		{
			return 3;
		}
		else if (result.trim().equals("1"))
		{
			return 2;
		}
		else if (result.trim().equals("EMPTY"))
		{
			//popolamento tipologica nazioni
			
			for(String country : movieDto.getCountries())
			{
				if(MovieGeneratorUtil.isNotNullEntry(country))
				{
					if(!movieDAO.isPresentCountry(country))
					{
						movieDAO.insertCountry(country);
					}
				}
				
			}
			//popolamento tipologica generi
			for(String genre : movieDto.getGenre())
			{
				if(MovieGeneratorUtil.isNotNullEntry(genre))
				{
					if(!movieDAO.isPresentGenre(genre))
					{
						movieDAO.insertGenre(genre);
					}
				}

				
			}
			//popolamento tipologica generi
			for(String language : movieDto.getLanguages())
			{
				if(MovieGeneratorUtil.isNotNullEntry(language))
				{
					if(!movieDAO.isPresentLanguage(language))
					{
						movieDAO.insertLanguage(language);
					}
				}

				
			}
			//popolamento tipologica attori
			for(String actor : movieDto.getActors())
			{
				if(MovieGeneratorUtil.isNotNullEntry(actor))
				{
					if(!movieDAO.isPresentActor(actor.trim()))
					{
						movieDAO.insertActor(actor.trim());
					}
				}

				
			}
			//popolamento tipologica registi
			for(String director : movieDto.getDirectors())
			{
				if(MovieGeneratorUtil.isNotNullEntry(director))
				{
					if(!movieDAO.isPresentDirector(director.trim()))
					{
						movieDAO.insertDirector(director.trim());
					}
				}

				
			}
			int wr=0;
			//popolamento tipologica scrittori
			for(String writer : movieDto.getWriters())
			{
				
				if(MovieGeneratorUtil.isNotNullEntry(writer))
				{
					if(!movieDAO.isPresentWriter(writer.trim()))
					{
						movieDAO.insertWriter(writer.trim(),movieDto.getWorkwriters().get(wr));
					}
				}

				wr ++;
			}
			movieDAO.insertMovie(movieDto);
			movieDAO.insertMovieGenRel(movieDto);
			movieDAO.insertMovieActorsRel(movieDto);
			movieDAO.insertMovieDirectorsRel(movieDto);
			movieDAO.insertMovieNationsRel(movieDto);
			movieDAO.insertMovieLanguagesRel(movieDto);
			if(!isPresentMovieWritersRel(movieDto))
			{
				movieDAO.insertMovieWritersRel(movieDto);

			}
			movieDAO.insertInternazionalization(movieDto);
			
			//in caso di serie tv
			if(isSerie(movieDto))
			{
				movieDAO.insertSerie(movieDto);
			}
			
			return 1;
		}
		return 0;
		
	}
	
	private boolean isSerie(MovieDTO movieDto) {
		// TODO Auto-generated method stub
		if(movieDto.getImdbSerieKey()!=null && !movieDto.getImdbSerieKey().trim().equals(""))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private boolean isPresentMovieWritersRel(MovieDTO movieDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
	public void cleanDB()
	{
		cleanDbRel();
		movieDAO.deleteAllMovies();
		movieDAO.deleteGenres();
		movieDAO.deleteAllCountries();
		movieDAO.deleteActors();
		movieDAO.deleteDirectors();
		movieDAO.deleteWriters();
		movieDAO.deleteLanguage();
	}
	
	
	
	@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
	public void cleanDbRel()
	{
		  movieDAO.deleteMovGenRel();
		  movieDAO.deleteMovieActorsRel();
		  movieDAO.deleteMovieNationRel();
		  movieDAO.deleteMovieWritersRel();
		  movieDAO.deleteMovieDirectorsRel();
		  movieDAO.deleteMovieLanguagesRel();
			movieDAO.deleteInternationalization();

		  
	}

	public void generateDbByActor(List<MovieDTO> moviesActor, String actor) {
	
		
	}

	public MovieDTO getInternationalization(MovieDTO film) {
		MovieDTO movieDTOtoUpdate= null;
		List<MovieDTO> movies = movieDAO.getInternazionalization(film);
		if(movies!= null && movies.size()!=0)
		{
			movieDTOtoUpdate = movies.get(0);
		}
		return movieDTOtoUpdate;
	}
	
	

	public void updateMovieInternationalization(String actor, MovieDTO film) {
		movieDAO.updateInternazionalization(film);
		if(!movieDAO.isPresentMovieActorsRel(film, actor))
		{
			List <String> actors = new ArrayList <String>();
			actors.add(actor);
			film.setActors(actors);
			
				movieDAO.insertMovieActorsRel(film);
			
		}
	}
	
	public void insertException(ExceptionInsert exc) {
		
		movieDAO.insertLogEccezioni(exc);
		
	}
	
}
