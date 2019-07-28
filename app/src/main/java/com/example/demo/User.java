package com.example.demo;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private String likes;
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }
    public User(String username, String password, String likes) {
        super();
        this.username = username;
        this.password = password;
        this.likes = likes;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getLikes() {
        return likes;
    }
    public void setLikes(String sex) {
        this.likes = likes;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password="
                + password + ", likes=" + likes + "]";
    }
}
