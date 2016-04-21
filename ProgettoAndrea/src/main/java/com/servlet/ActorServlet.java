package com.servlet;


import java.util.List;

import org.codehaus.jackson.map.JsonSerializer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.service.ActorService;

@RestController
public class ActorServlet  {

       @Autowired
       @Qualifier("actorService")
       private ActorService actorService;
       
            
       @RequestMapping(value = "/loadActors",method = {RequestMethod.GET,RequestMethod.POST})
       @ResponseBody
       public   String[] getActorsList(@RequestParam(value="stringActor") String stringActorSearch) {
    	   
              List<String> listaAttori = actorService.getActorsList(stringActorSearch); 

              String [] arrayActors= new String[listaAttori.size()];;
              if(listaAttori!=null && listaAttori.size()!=0)
              {
                 
                  for(int j=0;  j< listaAttori.size() ;j++)
                  {
                	 arrayActors[j]=listaAttori.get(j); 
                  }

              }
              return arrayActors;
       }

       
       

       
       
}

