package com.mtech.sjmsuser.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

public class WorkingExperience extends SecurityProperties.User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int userId;
    String[] skills;
    String experience;
    String currentJob;
    String isEmployed;
    JobCategories[] lookingFor;
    String lookingForEmploymentType;
    String lookingForExperience;
    String preferredLocation;
    JobHistory[] jobHistory;
    int expectedSalary;

}
