package com.example.restaurant.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hector on 21/07/16.
 */
@Entity
public class User implements Serializable{

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 255)
    private String name;

    @NotEmpty
    @Size(min = 1, max = 255)
    @Column(nullable = true, length = 255)
    private String surnames;

    @Size(min=1, max=255)
    @Column(nullable = true, length = 255)
    private String address;

    @Size(min=1, max=255)
    @Column(nullable = true, length = 255)
    private String city;

    @Size(min=1, max=15)
    @Column(nullable = true, length = 15)
    private String phoneNo;

    public User() {}

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
