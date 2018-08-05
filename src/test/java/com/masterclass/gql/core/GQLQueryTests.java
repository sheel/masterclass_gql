package com.masterclass.gql.core;

import org.junit.Test;

import java.util.Map;

public class GQLQueryTests
{
    @Test
    public void compileQueryOneCourse() throws Exception
    {
        String q = "{ course(slug:\"VScMwWlKoAJPwbbCrWcDDtucgtnjtsxVusdxIlafgQs\") { slug title } }";
        String s =
                "type Course {\n" +
                        "courseId: Integer\n" +
                        "    slug: String\n" +
                        "    title: String\n" +
                        "    price: Integer\n" +
                        "    release_date: Date\n" +
                        "    instructor: Instructor" +
                        "}";

        GQLSchema schema = new GQLSchema();
        schema.compile(s);

        Map commands = schema.getCommands();
        commands.put("allCourses", new GQLCommand("allCourses","[Course]", null));
        commands.put("course", new GQLCommand("course","Course", null));
        schema.setCommands(commands);

        GQLQuery query = new GQLQuery(q, schema).compile();
        assert("course".equals(query.getCommand().getVerb()));
    }

    @Test
    public void compileQueryAllCourses() throws Exception
    {
        String q =
                "{\n" +
                        "allCourses" +
                        "   {" +
                        "       slug" +
                        "       title" +
                        "       instructor" +
                        "   }" +
                        "}";
        String s =
                "type Course {\n" +
                        "courseId: Integer\n" +
                        "    slug: String\n" +
                        "    title: String\n" +
                        "    price: Integer\n" +
                        "    release_date: Date\n" +
                        "    instructor: Instructor" +
                        "}";

        GQLSchema schema = new GQLSchema();
        schema.compile(s);

        Map commands = schema.getCommands();
        commands.put("allCourses", new GQLCommand("allCourses","[Course]", null));
        commands.put("course", new GQLCommand("course","Course", null));
        schema.setCommands(commands);

        GQLQuery query = new GQLQuery(q, schema).compile();
        assert("allCourses".equals(query.getCommand().getVerb()));
    }
}
