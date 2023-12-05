package com.cst438.domain;


import javax.persistence.*;

@Entity
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;  // Match the column name in the table

    private String username;
    private String password;
    private String Role;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getRole() {
        return Role;
    }

    public void setRole(String getRole) {
        this.Role = Role;
    }

    // No need for additional attributes (firstName, lastName, role) in this class

    @Override
    public String toString() {
        return "User [user_id=" + user_id + ", username=" + username + "]";
    }
}
