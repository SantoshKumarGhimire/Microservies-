package com.microservice.example.moviecatalogservice.resources;

import com.microservice.example.moviecatalogservice.models.CatalogItem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCaltalogItems(@PathVariable("userId") String userId){

       return Collections.singletonList(
               new CatalogItem("Titanic","sbcd","5")
       );
    }

}
