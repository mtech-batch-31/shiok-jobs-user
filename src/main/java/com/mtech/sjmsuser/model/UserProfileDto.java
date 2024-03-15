package com.mtech.sjmsuser.model;

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

    private long id;
    private String accountUuid;
    private String name;
    private boolean seeking;
    private String jobTitle;
    private String image;
    private String about;
    private List<WorkExperienceDto> workExperience;
    private List<EducationDto> education;

}