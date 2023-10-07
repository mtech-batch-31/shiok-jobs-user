package com.mtech.sjmsuser.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkingExperienceDto {

    private  String company;

    private String yearStart;
    private String yearEnd;


    private String jobTitle;

    private String experience;

}
