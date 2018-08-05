package com.masterclass.gql.service;

import com.masterclass.gql.model.Course;
import com.masterclass.gql.model.Instructor;
import com.masterclass.gql.repository.ICourseRepository;
import com.masterclass.gql.repository.MongoConfig;
import com.masterclass.gql.service.GQLService;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Random;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MongoConfig.class})
public class GQLServiceTests
{

    @Autowired
    private ICourseRepository repository;

    private GQLService gqlService;

    @Before
    public void init() throws Exception
    {
        gqlService = new GQLService();

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

    @Test
    public void executeAllCoursesQuery() throws Exception {
        String query = "{ allCourses { slug title } }";
        //gqlService.execute(query);;
        //assert(results.size() >= 1);
    }

}
