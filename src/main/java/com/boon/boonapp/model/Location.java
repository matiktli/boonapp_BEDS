package com.boon.boonapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "location")
public class Location {

    @Id
    @Column(name = "location_id", updatable = false, nullable = false)
    private Long id;

    //-90 to 90
    @Column(name = "lat", nullable = false)
    private Long lat;

    //-180 to 180
    @Column(name = "lng", nullable = false)
    private Long lng;

    @Column(name = "name")
    private String name;
}
