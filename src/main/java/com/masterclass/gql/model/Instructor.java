package com.masterclass.gql.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection="Instructor")
public class Instructor
{
    @Id
    private String _id;

    @NotNull(message="Instructor Id cannot be null")
    private Integer instructorId;

    @NotNull(message="Instructor Name cannot be null")
    private String name;

    @NotNull(message="Instructor Gender cannot be null")
    private String gender;

    @NotNull(message="Instructor City cannot be null")
    private String city;

    public Instructor()
    {

    }

    public String get_id()
    {
        return _id;
    }

    public void set_id(String _id)
    {
        this._id = _id;
    }

    public Integer getInstructorId()
    {
        return instructorId;
    }

    public void setInstructorId(Integer instructorId)
    {
        this.instructorId = instructorId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }
}
