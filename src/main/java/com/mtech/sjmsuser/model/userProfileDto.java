package com.mtech.sjmsuser.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class userProfileDto {
    private String name;
    private String jobTitle;
    private String image;
    private String about;

    private Iterable<workingExperienceDto> workingExperience;
    private Iterable<educationalExperienceDto> educationalExperience;

}