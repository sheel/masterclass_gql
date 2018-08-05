package com.masterclass.gql.service;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.masterclass.gql.core.GQLCommand;
import com.masterclass.gql.core.GQLQuery;
import com.masterclass.gql.core.GraphQL;
import com.masterclass.gql.dao.CourseDAO;
import com.masterclass.gql.model.Course;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GQLService
{
    private GraphQL graphQL = new GraphQL();

    private CourseDAO courseExecutor = new CourseDAO();

    @Value("classpath:course.graphql")
    Resource resource;

    public GQLService()
    {

    }

    public List<Course> execute(String q) throws Exception
    {
        loadSchema();
        GQLQuery query = this.graphQL.compile(q);
        List<Course> results = new ArrayList<Course>();

        if(query.getCommand().getVerb().equals("allCourses"))
        {
            results = this.courseExecutor.findAll(query);
        }
        else if(query.getCommand().getVerb().equals("course"))
        {
            results = this.courseExecutor.find(query);
        }

        return results;
    }

    private String loadSchema()
    {
        String contents = "";
        try
        {
            Map commands = this.graphQL.getSchema().getCommands();
            commands.put("allCourses", new GQLCommand("allCourses","[Course]", null));
            commands.put("course", new GQLCommand("course","Course", null));
            this.graphQL.getSchema().setCommands(commands);

            contents = Files.toString(this.resource.getFile(), Charsets.UTF_8);
            this.graphQL.getSchema().compile(contents);
        }
        catch(IOException e)
        {
            System.out.println("Could not load schema");
            System.out.println(e.toString());
        }
        return contents;
    }

}
