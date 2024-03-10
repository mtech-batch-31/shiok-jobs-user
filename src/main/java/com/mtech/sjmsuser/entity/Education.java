package com.mtech.sjmsuser.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "profile_id", nullable = false)
    private UserProfile userProfile;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String school;
    private String yearStart;
    private String yearEnd;

    private String logo;

    @Column(length = 512)
    private String description;

    // getters and setters

    @Override
    public String toString() {
        return "Education{" +
                "userProfile=" + userProfile.getId() +
                ", Id=" + Id +
                ", school='" + school + '\'' +
                ", yearStart='" + yearStart + '\'' +
                ", yearEnd='" + yearEnd + '\'' +
                ", logo='" + logo + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}