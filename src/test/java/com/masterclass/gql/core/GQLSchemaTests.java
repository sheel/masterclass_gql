package com.masterclass.gql.core;

import org.junit.Test;

public class GQLSchemaTests
{
    @Test
    public void compileSchema()
    {
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
        assert(schema.getType().get("Course") != null && schema.getType().get("Course").getAttributes().size() == 6);
    }

    @Test
    public void getAttributeSchemaByKeySlug()
    {
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
        GQLAttribute a = schema.getAttribute("slug");

        assert(a.getAttribute().equals("slug"));
        assert(a.getDataType().equals("String"));
    }
}
