package com.abhi.quiz.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;


@Document(collection = "users")
public class User {

    @Id
    private String id; // MongoDB id

    private String uid; // Firebase uid
    private String email;
    private String name;
    private  Date createdAt ;
    private Date updatedAt ;

    public User() {
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User(String id, String uid, String email, String name, Date updatedAt) {
        this.id = id;
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
