package com.masterclass.gql.core;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 query
 {
 <query_type>(<param_name>: <param_value>)
 {
 <field_name>
 <field_name>
 }
 }
 **/
public class GQLQuery
{
    private String queryString;

    private GQLSchema schema;

    private GQLCommand command;

    private List<GQLAttribute> filterAttributes;

    private List<GQLAttribute> resultAttributes;

    private List<?> results;

    public GQLQuery()
    {
        this.filterAttributes = new ArrayList<>();
        this.resultAttributes = new ArrayList<>();
    }

    public GQLQuery(String q, GQLSchema s)
    {
        this.filterAttributes = new ArrayList<>();
        this.resultAttributes = new ArrayList<>();
        this.setSchema(s);
        this.setQueryString(q);
    }

    public GQLQuery compile() throws Exception
    {
        this.parseQuery();
        //TODO: confirm the query matches the schema
        //this.isValid(q);
        return this;
    }

    public String isValid(String q)
    {
        String type = "^\\s*(query)?\\s*\\{.*\\}\\s*\\}.*$";

        //flatten the query onto a single line (remove newlines and tabs)
        q = q.replace("\n", " ").replace("\t", " ");

        Pattern p = Pattern.compile(type);
        Matcher m = p.matcher(q);

        if(m.matches())
            System.out.println("matched!");
        else
            System.out.println("failed");


        return q;
    }

    private void parseQuery() throws Exception
    {
        String q = this.getQueryString();
        //flatten the query onto a single line (remove newlines and tabs)
        q = q.replace("\n", " ").replace("\t", " ").replace("\"","");

        int commandIdx = -1;

        //identify the command
        for (String key : this.schema.getCommands().keySet())
        {
            commandIdx = q.indexOf(key);
            if(commandIdx != -1)
            {
                this.command = this.schema.getCommand(key);
                break;
            }
        }
        //TODO: if commandIdx == -1 at this point, throw an exception - invalid command/query

        //find filters, if any
        int startFiltersIdx = q.indexOf("(");
        if(startFiltersIdx != -1)
        {
            startFiltersIdx++;
            int endFiltersIdx = q.indexOf(")");
            String filterExpression = q.substring(startFiltersIdx, endFiltersIdx);
            String filters[] = filterExpression.split(",");
            for(int i = 0; i < filters.length; i++)
            {
                String[] f = filters[i].split(":");
                //TODO: validate that the attribute exists in the schema
                GQLAttribute a = schema.getAttribute(f[0].trim());
                a.setValue(f[1].trim());
                this.filterAttributes.add(a);
            }
        }

        //identify result attributes
        int attrStartIdx = q.indexOf("{", commandIdx)+1;
        int attrEndIdx = q.indexOf("}", attrStartIdx)-1;
        String sub = q.substring(attrStartIdx,attrEndIdx).trim();
        String[] attributes = sub.split("\\s+");
        for(int i = 0; i < attributes.length; i++)
        {
            GQLAttribute a = new GQLAttribute();
            a.setAttribute(attributes[i]);
            this.resultAttributes.add(a);
        }
    }

    public GQLSchema getSchema()
    {
        return schema;
    }

    public void setSchema(GQLSchema schema)
    {
        this.schema = schema;
    }

    public String getQueryString()
    {
        return queryString;
    }

    public void setQueryString(String queryString)
    {
        this.queryString = queryString;
    }

    public GQLCommand getCommand()
    {
        return command;
    }

    public void setCommand(GQLCommand command)
    {
        this.command = command;
    }

    public List<GQLAttribute> getFilterAttributes()
    {
        return filterAttributes;
    }

    public void setFilterAttributes(List<GQLAttribute> filterAttributes)
    {
        this.filterAttributes = filterAttributes;
    }

    public List<GQLAttribute> getResultAttributes()
    {
        return resultAttributes;
    }

    public void setResultAttributes(List<GQLAttribute> resultAttributes)
    {
        this.resultAttributes = resultAttributes;
    }

    public List<?> getResults()
    {
        return results;
    }

    public void setResults(List<?> results)
    {
        this.results = results;
    }
}
