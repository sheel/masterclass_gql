package com.masterclass.gql.repository;

import com.masterclass.gql.model.Course;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICourseRepository extends MongoRepository<Course, String>
{
    Course findBy_id(ObjectId id);
    Course findByCourseId(Integer id);
    Course findByTitle(String title);
    Course findBySlug(String slug);
}