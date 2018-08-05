package com.masterclass.gql.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Document(collection="Course")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Course
{
    @Id
    private String _id;

    @NotNull(message="Course Id cannot be null")
    private Integer courseId;

    @NotNull(message="Course Slug cannot be null")
    private String slug;

    @NotNull(message="Course Title cannot be null")
    private String title;

    @NotNull(message="Course Price cannot be null")
    private Integer price;

    @JsonFormat(pattern="yyyy-MM-dd")
    @NotNull(message="Course Date cannot be null")
    private Date release_date;

    @NotNull(message="Course Instructor cannot be null")
    private Instructor instructor;

    public Course()
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

    public Integer getCourseId()
    {
        return courseId;
    }

    public void setCourseId(Integer courseId)
    {
        this.courseId = courseId;
    }

    public String getSlug()
    {
        return slug;
    }

    public void setSlug(String slug)
    {
        this.slug = slug;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Integer getPrice()
    {
        return price;
    }

    public void setPrice(Integer price)
    {
        this.price = price;
    }

    public Date getRelease_date()
    {
        return release_date;
    }

    public void setRelease_date(Date release_date)
    {
        this.release_date = release_date;
    }

    public Instructor getInstructor()
    {
        return instructor;
    }

    public void setInstructor(Instructor instructor)
    {
        this.instructor = instructor;
    }
}
