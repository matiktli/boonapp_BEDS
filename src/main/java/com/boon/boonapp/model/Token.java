package com.boon.boonapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "token")
public class Token {

    @Id
    @Column(name = "token_id", updatable = false, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "value", nullable = false)
    private String value;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    private Timestamp createDate;
}
