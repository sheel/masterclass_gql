package com.masterclass.gql.dao;

import com.masterclass.gql.model.Course;
import com.masterclass.gql.model.Instructor;
import com.masterclass.gql.repository.ICourseRepository;
import com.masterclass.gql.repository.MongoConfig;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MongoConfig.class})
public class CourseDAOTests
{

    @Autowired
    private ICourseRepository repository;

    private static final int SIZE = (int) (Math.random() * 100);

    @Before
    public void init()
    {
        repository.deleteAll();

        for (int i = 0; i < SIZE; i++) {

            Course course = new Course();
            Integer id = (int) (Math.random() * 1000);
            String slug = RandomStringUtils.randomAlphabetic((int) (Math.random() * 50));
            String title = RandomStringUtils.randomAlphabetic((int) (Math.random() * 30));
            Date release_date = new Date(-946771200000L + (Math.abs(new Random().nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000)));
            Integer price = (int) (Math.random() * 100);

            course.setCourseId(id);
            course.setSlug(slug);
            course.setTitle(title);
            course.setRelease_date(release_date);
            course.setPrice(price);

            Instructor instructor = new Instructor();
            id = (int) (Math.random() * 1000);
            String name = RandomStringUtils.randomAlphabetic((int) (Math.random() * 10)) + " " + RandomStringUtils.randomAlphabetic((int) (Math.random() * 10));
            String gender = RandomStringUtils.randomAlphabetic((int) (Math.random() * 5));
            String city = RandomStringUtils.randomAlphabetic((int) (Math.random() * 10));

            instructor.setInstructorId(id);
            instructor.setName(name);
            instructor.setGender(gender);
            instructor.setCity(city);
            course.setInstructor(instructor);

            repository.save(course);
        }
    }

    @Test
    public void testRepositorySavedAllInserts() {
        List<Course> results = repository.findAll();
        assertEquals(SIZE, results.size());
    }

    @Test
    public void readBySlug() {
        List<Course> results = repository.findAll();
        Course c = repository.findBySlug(results.get(0).getSlug());
        assertEquals(c.get_id(), results.get(0).get_id());
    }

    @Test
    public void readByTitle() {
        List<Course> results = repository.findAll();
        Course c = repository.findByTitle(results.get(0).getTitle());
        assertEquals(c.get_id(), results.get(0).get_id());
    }

    @Test
    public void readByCourseId() {
        List<Course> results = repository.findAll();
        Course c = repository.findByCourseId(results.get(0).getCourseId());
        assertEquals(c.get_id(), results.get(0).get_id());
    }


}
