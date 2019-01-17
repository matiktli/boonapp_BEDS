package com.boon.boonapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "location_id", updatable = false, nullable = false)
    private Long id;

    //-90 to 90
    @Column(name = "lat", nullable = false)
    private Double lat;

    //-180 to 180
    @Column(name = "lng", nullable = false)
    private Double lng;

    @Column(name = "name")
    private String name;
}
