package com.mtech.sjmsuser.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String accountUuid;
    private String name;
    private boolean seeking;
    private String jobTitle;
    private String image;
    private String about;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userProfile")
    private List<WorkingExperience> workingExperience;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userProfile")
    private List<EducationalExperience> educationalExperience;

}