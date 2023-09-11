package com.hotel.entity;


import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Hotels ")
@Data
public class Hotel {
    @Id
    @Column(name = "hotelId")
    private String id;
    @Column(name = "NAME")
    private String name;
    @Column( name = "LOCATION")
    private String location;
    @Column(name = "ABOUT")
    private String about;

}

