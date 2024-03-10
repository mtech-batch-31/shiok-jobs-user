package com.mtech.sjmsuser.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CreateUserDto {

    private String accountUuid;
    private String name;
    private boolean seeking;
    private String jobTitle;
    private String image;
    private String about;
    private List<WorkExperienceDto> workingExperience;
    private List<EducationDto> educationalExperience;
}
