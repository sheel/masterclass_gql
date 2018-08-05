package com.masterclass.gql.core;

import java.util.HashMap;
import java.util.Map;

public class GQLSchema
{
    private Map<String, GQLCommand> commands;

    private Map<String, GQLType> type;

    public GQLSchema()
    {
        this.commands = new HashMap<String, GQLCommand>();
        type = new HashMap<>();
    }

    public Map<String, GQLCommand> getCommands()
    {
        return commands;
    }

    public void setCommands(Map<String, GQLCommand> commands)
    {
        this.commands = commands;
    }

    public GQLCommand getCommand(String key)
    {
        return commands.get(key);
    }

    public GQLAttribute getAttribute(String a)
    {
        return type.get("Course").getAttributes().get(a);
    }

    public Map<String, GQLType> getType()
    {
        return type;
    }

    public void setType(Map<String, GQLType> type)
    {
        this.type = type;
    }

    public void compile(String s)
    {
        String types[] = s.split("type");
        for(int i = 0; i < types.length; i++)
        {
            types[i] = types[i].trim();
            if(!"".equals(types[i]))
            {
                String typeKey = types[i].substring(0, types[i].indexOf("{") - 1);
                if(typeKey.equals("Course"))
                {
                    GQLType t = new GQLType();
                    t.setType("Course");
                    HashMap<String, GQLAttribute> al = new HashMap<>();

                    String a = types[i].substring(types[i].indexOf("{") + 1, types[i].indexOf("}") - 1).trim();
                    String attributes[] = a.split("\n");
                    for(i = 0; i < attributes.length; i++)
                    {
                        String details[] = attributes[i].trim().split(":");
                        GQLAttribute attribute = new GQLAttribute();
                        attribute.setAttribute(details[0].trim());
                        attribute.setDataType(details[1].trim());
                        al.put(details[0],attribute);
                    }
                    t.setAttributes(al);
                    type.put("Course", t);
                    return;
                }
            }
        }
    }
}
