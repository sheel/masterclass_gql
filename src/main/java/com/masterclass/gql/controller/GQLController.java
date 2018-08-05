package com.masterclass.gql.controller;

import com.masterclass.gql.model.Course;
import com.masterclass.gql.service.GQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/graphql")
public class GQLController
{

    @Autowired
    private com.masterclass.gql.service.GQLService GQLService = new GQLService();

    @RequestMapping("/")
    public List<Course> rootContext(@RequestBody String q)
    {
        List r = new ArrayList<>();
        try
        {
            r = GQLService.execute(q);
        }
        catch(Exception e)
        {
            System.out.println("Could not successfully execute your query.");
            e.printStackTrace();
            return new ArrayList<Course>();
        }
        return r;
    }

}
