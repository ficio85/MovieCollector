package com.servlet;


import java.util.List;

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
       public  List<String> getActorsList(@RequestParam(value="stringActor", defaultValue="") String stringActorSearch) {
    	   
              List<String> listaAttori = actorService.getActorsList(stringActorSearch);                          
              return listaAttori;
       }

       
       

       
       
}

