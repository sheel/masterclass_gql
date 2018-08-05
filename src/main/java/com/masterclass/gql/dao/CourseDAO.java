package com.masterclass.gql.dao;

import com.masterclass.gql.model.Course;
import com.masterclass.gql.core.GQLQuery;
import com.masterclass.gql.repository.MongoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("courseDAO")
public class CourseDAO
{
    @Autowired
    MongoTemplate mongo;

    final String collection = "course";

    public CourseDAO()
    {
        MongoConfig c = new MongoConfig();
        try
        {
            mongo = c.mongoTemplate();
        }
        catch(Exception e)
        {
            System.out.println("failed to open db connection");
            e.printStackTrace();
        }
    }

    public List<Course> findAll(GQLQuery query) throws Exception
    {
        Query mongoQuery = new Query();
        query.getResultAttributes().forEach(a->mongoQuery.fields().include(a.getAttribute()).exclude("_id"));
        return mongo.find(mongoQuery, Course.class);
    }

    public List<Course> find(GQLQuery query)
    {
        Query mongoQuery = new Query();
        query.getFilterAttributes().forEach(f->mongoQuery.addCriteria(Criteria.where(f.getAttribute()).is(f.getValue())));
        query.getResultAttributes().forEach(a->mongoQuery.fields().include(a.getAttribute()).exclude("_id"));
        return mongo.find(mongoQuery, Course.class);
    }

    public String save(Course c)
    {
        mongo.save(c);
        return c.get_id();
    }
}
