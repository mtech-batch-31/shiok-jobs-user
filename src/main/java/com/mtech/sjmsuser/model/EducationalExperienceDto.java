package com.mtech.sjmsuser.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationalExperienceDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    private BigInteger profile_id;

    private String school;

    private String yearStart;
    private String yearEnd;


    private String description;
}
