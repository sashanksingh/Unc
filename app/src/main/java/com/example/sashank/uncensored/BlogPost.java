package com.example.sashank.uncensored;

import com.google.firebase.firestore.ServerTimestamp;
import java.util.Date;

public class BlogPost {
    public String age,country,gender,message,name,title,opinion;
    public Date timestamp;

    public BlogPost()
    {

    }

    public BlogPost(String age, String country, String gender, String message, String name, String title, Date timestamp, String opinion) {
        this.age = age;
        this.country = country;
        this.gender = gender;
        this.message = message;
        this.name = name;
        this.title = title;
        this.timestamp = timestamp;
        this.opinion = opinion;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
