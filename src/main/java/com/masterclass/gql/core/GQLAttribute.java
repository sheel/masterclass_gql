package com.masterclass.gql.core;

import com.masterclass.gql.model.Instructor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GQLAttribute
{
    private String attribute;
    private String dataType;
    private Object value;

    public GQLAttribute()
    {

    }

    public String getAttribute()
    {
        return attribute;
    }

    public void setAttribute(String attribute)
    {
        this.attribute = attribute;
    }

    public String getDataType()
    {
        return dataType;
    }

    public void setDataType(String dataType)
    {
        this.dataType = dataType;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(String value) throws Exception
    {
        switch(dataType)
        {
            case "String":
                this.value = new String(value);
                break;

            case "Integer":
                this.value = Integer.parseInt(value);
                break;

            case "Date":
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                this.value = sdf.parse(value);
                break;

            case "Instructor":
                this.value = new Instructor();
                break;
        }
    }
}
