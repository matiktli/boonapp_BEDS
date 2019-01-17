package com.boon.boonapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "needy")
public class Needy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "needy_id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private NeedyType type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    private Location location;

    @Column(name = "description")
    private String description;

    @Column(name = "notes")
    private String notes;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(
            name = "needy_to_user",
            joinColumns = {@JoinColumn(name = "needy_id", referencedColumnName = "needy_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> attachedUsers = new HashSet<>();

}
