package com.masterclass.gql.core;

public class GQLCommand
{
    private String verb;
    private String resultType;
    private GQLAttribute[] filters;

    public GQLCommand()
    {}

    public GQLCommand(String c, String r, GQLAttribute[] f)
    {
        this.verb = c;
        this.resultType = r;
        this.filters = f;
    }

    public String getVerb()
    {
        return verb;
    }

    public void setVerb(String verb)
    {
        this.verb = verb;
    }

    public String getResultType()
    {
        return resultType;
    }

    public void setResultType(String resultType)
    {
        this.resultType = resultType;
    }

    public GQLAttribute[] getFilters()
    {
        return filters;
    }

    public void setFilters(GQLAttribute[] filters)
    {
        this.filters = filters;
    }
}
