package com.masterclass.gql.core;

import org.junit.Test;

import java.util.Date;

public class GQLAttributeTests
{

    @Test
    public void testAttributeRuntimeBindingString() throws Exception
    {
        GQLAttribute a = new GQLAttribute();
        a.setAttribute("name");
        a.setDataType("String");
        a.setValue("John");

        assert(a.getValue() instanceof String);
    }

    @Test
    public void testAttributeRuntimeBindingInteger() throws Exception
    {
        GQLAttribute a = new GQLAttribute();
        a.setAttribute("courseId");
        a.setDataType("Integer");
        a.setValue("10");

        assert(a.getValue() instanceof Integer);
    }

    @Test
    public void testAttributeRuntimeBindingDate() throws Exception
    {
        GQLAttribute a = new GQLAttribute();
        a.setAttribute("release_date");
        a.setDataType("Date");
        a.setValue("01/10/2018");

        assert(a.getValue() instanceof Date);
    }

}
