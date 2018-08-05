package com.masterclass.gql.core;

import java.util.Map;

public class GQLType
{
    private String type;
    private Map<String, GQLAttribute> attributes;

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Map<String, GQLAttribute> getAttributes()
    {
        return attributes;
    }

    public void setAttributes(Map<String, GQLAttribute> attributes)
    {
        this.attributes = attributes;
    }
}
