package com.mtech.sjmsuser.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String accountUuid;
    private String name;
    private boolean seeking;
    private String jobTitle;
    private String image;
    private String about;
    private List<WorkingExperienceDto> workingExperience;
    private List<EducationalExperienceDto> educationalExperience;

}