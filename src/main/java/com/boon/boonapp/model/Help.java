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
@Table(name = "help")
public class Help {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "help_id", updatable = false, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "needy_id")
    private Needy needy;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinTable(
            name = "help_to_user",
            joinColumns = { @JoinColumn(name = "help_id", referencedColumnName = "help_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private Set<User> helpers = new HashSet<>();

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "file_id")
    private FileMetadata file;

}
