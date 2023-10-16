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
public class WorkingExperienceDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    private BigInteger profile_id;

    private  String company;

    private String yearStart;
    private String yearEnd;


    private String jobTitle;

    private String logo;

    private String experience;

}
