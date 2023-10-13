package com.mtech.sjmsuser.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
//
//    private List<WorkingExperience> workingExperiences;
//    private List<EducationalExperience> educationalExperiences;

}