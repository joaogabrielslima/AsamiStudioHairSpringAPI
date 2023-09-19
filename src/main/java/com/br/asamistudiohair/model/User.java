package com.br.asamistudiohair.model;

import jakarta.persistence.*;

import java.util.Collections;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userRole;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;

    public User(){
        this.userRole = Collections.singleton("USER").toString();
    }

    public User (String name, String password, String email){
          this.userRole = Collections.singleton("USER").toString();
          this.name = name;
          this.password = password;
          this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}