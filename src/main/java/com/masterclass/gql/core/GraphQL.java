package com.masterclass.gql.core;

import org.springframework.stereotype.Component;

@Component
public class GraphQL
{
    private GQLSchema schema;

    public GraphQL()
    {
        this.schema = new GQLSchema();
    }

    public GQLSchema getSchema()
    {
        return schema;
    }

    public void setSchema(GQLSchema schema)
    {
        this.schema = schema;
    }

    private GQLSchema compile(GQLSchema s)
    {
        return schema;
    }

    public GQLQuery compile(String q) throws Exception
    {
        return new GQLQuery(q, this.getSchema()).compile();
    }

}
