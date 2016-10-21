package com.algorithm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.dao.DirectorDAO;
import com.dao.MovieRankDAO;
import com.dto.ActorDTO;
import com.dto.DirectorDTO;
import com.dto.GenereDTO;
import com.dto.MovieDTO;
import com.dto.PesiDTO;
import com.dto.UserActorRateDTO;
import com.dto.UserDirectorRateDTO;
import com.dto.UserGenreRateDTO;
import com.dto.UserWriterRateDTO;
import com.dto.WriterDTO;
import com.service.SearchMovieService;
@EnableAsync
@Service("movieLikeAlgorithm")
public class MovieLikeAlgorithm {

	@Autowired
	@Qualifier("rateDAO")
	 MovieRankDAO rateDAO;	
	
	
	public  float getMovieLikeRate(MovieDTO movie,String user)
	{
		boolean isRegista=false;
		boolean isAttori=false;
		boolean isGenre=false;
		List <DirectorDTO> directors =movie.getDirectors();
		List <GenereDTO> genres= movie.getGenre();
		List <ActorDTO> actors=movie.getActors();
		List <WriterDTO> writers=movie.getWriters();

		List <UserWriterRateDTO> rateWriters = new ArrayList <UserWriterRateDTO>();
		List <UserActorRateDTO> rateActors = new ArrayList <UserActorRateDTO>();
		List <UserGenreRateDTO> rateGenres = new ArrayList <UserGenreRateDTO>();
		System.out.println("--INIZIO ALGORITMO--");
		System.out.println("--TITOLO FILM "+movie.getTitle());
		System.out.println("--TITOLO FILM "+movie.getTitoloItaliano());

		//prendo il regista principale
		DirectorDTO mainDirector=directors.get(0);
		 UserDirectorRateDTO rateDirector = rateDAO.getUserDirectorRate(user, mainDirector.getName());
		 
		 for(int i=0;i<actors.size();i++)
		 {
			 UserActorRateDTO rateActor= rateDAO.getUserActorRate(user, actors.get(i).getName());
			if(rateActor!=null)
			 rateActors.add(rateActor);
		 }
		 for(int i=0;i<writers.size();i++)
		 {
			 UserWriterRateDTO rateWriter= rateDAO.getUserWriterRate(user, writers.get(i).getName());
			 if(rateWriter!=null)
			 rateWriters.add(rateWriter);
		 }
		 for(int i=0;i<genres.size();i++)
		 {
			 UserGenreRateDTO rateGenre= rateDAO.getUserGenreRate(user, genres.get(i).getCodGenre());
			 if(rateGenre!=null)
				 rateGenres.add(rateGenre);
		 }
		
		 PesiDTO pesi=calcolaPesi(rateDirector,rateActors,rateWriters,rateGenres);
		 
			System.out.println("--FINE ALGORITMO--"+movie.getTitoloItaliano());

		 return calcolaMedia(pesi,rateDirector,rateActors,rateWriters,rateGenres);
		 
		
		
	}


	private float calcolaMedia(PesiDTO pesi, UserDirectorRateDTO rateDirector, List<UserActorRateDTO> rateActors,
			List<UserWriterRateDTO> rateWriters, List<UserGenreRateDTO> rateGenres) {
		// TODO Auto-generated method stub
		float mediaDir = 0;
		float mediaAct = 0;
		float mediaWri =0;
		float mediaGen= 0;
		
		if(rateDirector!=null)
		{
			if(rateDirector.getRate()!=0.0)
			{
				mediaDir = rateDirector.getRate();
			}
			else if (rateDirector.getAutorate()!=0.0)
			{
				mediaDir = rateDirector.getAutorate();
			}
		}
		if(rateActors!=null && rateActors.size()!=0)
		{
			for(UserActorRateDTO rateActor:rateActors)
			{
				if(rateActor.getRate()!=0.0)
				{
					mediaAct+=rateActor.getRate();
				}
				else if(rateActor.getAutorate()!=0.0)
				{
					mediaAct+=rateActor.getAutorate();
				}
			}
			mediaAct= mediaAct/rateActors.size();
		}
		if(rateWriters!=null && rateWriters.size()!=0)
		{
			for(UserWriterRateDTO rateWriter:rateWriters)
			{
				if(rateWriter.getRate()!=0.0)
				{
					mediaWri+=rateWriter.getRate();
				}
				
			}
			mediaWri=mediaWri/rateWriters.size();
		}
		if(rateGenres!=null && rateGenres.size()!=0)
		{
			for(UserGenreRateDTO rateGenre:rateGenres)
			{
				if(rateGenre.getRate()!=0.0)
				{
					mediaGen+=rateGenre.getRate();
				}
				
			}
			mediaGen= mediaGen/rateGenres.size();
		}
		System.out.println("mediaDir " +mediaDir);
		System.out.println("mediaAct " +mediaAct);
		System.out.println("mediaWri " +mediaWri);
		System.out.println("mediaGen " +mediaGen);

		float sommaFactors=mediaDir*pesi.getPesoRegista()+mediaAct*pesi.getPesoAttori()+mediaWri*pesi.getPesoScrittori()+mediaGen*pesi.getPesoGeneri();
		System.out.println("sommaFactors " +sommaFactors);

		return sommaFactors/100;
	}


	private PesiDTO calcolaPesi(UserDirectorRateDTO rateDirector, List<UserActorRateDTO> rateActors,
			List<UserWriterRateDTO> rateWriters, List<UserGenreRateDTO> rateGenres) {
		// TODO Auto-generated method stub
		
		//non ci sono registi
		if(rateDirector==null)
		{
			if(rateActors!=null && rateActors.size()!=0)
			{
				if(rateWriters!=null && rateWriters.size()!=0)
				{
					if(rateGenres!=null && rateGenres.size()!=0)//non ci sono registi (max 95)
					{
						System.out.println("--INIZIO ALGORITMO--");

						return settaPesi(0,40,30,25);

					}
					else //non ci sono registi, generi (max 50)
					{
						System.out.println("--INIZIO ALGORITMO--");

						return settaPesi(0,30,20,0);

					}
				}
				else //non ci sono registi e scrittori
				{
					
						if(rateGenres!=null && rateGenres.size()!=0)//non ci sono registi e scrittori (max 85)
						{
							System.out.println("--INIZIO ALGORITMO--");

							return settaPesi(0,0,40,45);
						}
						else //non ci sono registi,scrittori,generi (max 50)
						{
							System.out.println("--INIZIO ALGORITMO--");

							return settaPesi(0,0,50,0);
						}
					
				}
			}
			else //non ci sono registi e attori
			{
				if(rateWriters!=null && rateWriters.size()!=0)
				{
					if(rateGenres!=null && rateGenres.size()!=0)//non ci sono registi,attori (max 90)
					{
						System.out.println("--INIZIO ALGORITMO--");

						return settaPesi(0,50,0,40);
					}
					else //non ci sono registi, attori generi (max 60)
					{
						System.out.println("--INIZIO ALGORITMO--");

						return settaPesi(0,60,0,0);
					}
				}
				else //non ci sono registi attori scrittori
				{
					
						if(rateGenres!=null && rateGenres.size()!=0)//non ci sono registi attori scrittori (max 75)
						{
							System.out.println("--INIZIO ALGORITMO--");

							return settaPesi(0,0,0,75);
						}
						else //non ci sono registi,attori,scrittori,generi
						{
							System.out.println("--INIZIO ALGORITMO--");

							return settaPesi(0,0,0,0);
						}
					
				}
			}
			
		}
		else
		{

			if(rateActors!=null && rateActors.size()!=0)
			{
				if(rateWriters!=null && rateWriters.size()!=0)
				{
					if(rateGenres!=null && rateGenres.size()!=0)//c'è tutto
					{
						System.out.println("--INIZIO ALGORITMO--");

						return settaPesi(30,30,20,20);
					}
					else //non ci sono  generi
					{
						System.out.println("--INIZIO ALGORITMO--");

						return settaPesi(30,30,20,0);
					}
				}
				else //non ci sono scrittori
				{
					
						if(rateGenres!=null && rateGenres.size()!=0)//non ci  scrittori (max 95)
						{
							System.out.println("--INIZIO ALGORITMO--");

							return settaPesi(0,30,0,20);
						}
						else //non ci sono scrittori,generi (max 50)
						{
							System.out.println("--INIZIO ALGORITMO--");

							return settaPesi(30,30,20,0);
						}
					
				}
			}
			else //non ci  attori
			{
				if(rateWriters!=null && rateWriters.size()!=0)
				{
					if(rateGenres!=null && rateGenres.size()!=0)//non ci sono generi,attori (max 60)
					{
						System.out.println("--INIZIO ALGORITMO--");

						return settaPesi(0,0,30,30);
					}
					else //non ci sono attori (max 95)
					{
						System.out.println("--INIZIO ALGORITMO--");

						return settaPesi(40,40,15,0);
					}
				}
				else //non ci sono attori scrittori
				{
					
						if(rateGenres!=null && rateGenres.size()!=0)//non ci sono generi attori scrittori
						{
							System.out.println("--INIZIO ALGORITMO--");

							return settaPesi(60,0,0,0);
						}
						else //non ci sono attori,scrittori (max 90)
						{
							System.out.println("--INIZIO ALGORITMO--");

							return settaPesi(45,0,0,45);
						}
					
				}
			}
			
		
		}
		
	}


	private PesiDTO settaPesi(int pesoRegista, int pesoScrittore, int pesoAttore, int pesoGenere) {
		// TODO Auto-generated method stub
		
		PesiDTO pesi = new PesiDTO();
		pesi.setPesoRegista(pesoRegista);
		pesi.setPesoScrittori(pesoScrittore);
		pesi.setPesoAttori(pesoAttore);
		pesi.setPesoGeneri(pesoGenere);
		return pesi;
		}
	
	
}
