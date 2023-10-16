package com.mtech.sjmsuser.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkingExperience {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "profile_id", nullable = false)
    private UserProfile userProfile;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
//    private BigInteger profileId;
    private String company;
    private String yearStart;
    private String yearEnd;
    private String jobTitle;

    private String logo;

    @Column(length = 512)
    private String experience;

    // getters and setters
}