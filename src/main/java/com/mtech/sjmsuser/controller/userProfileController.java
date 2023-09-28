package com.mtech.sjmsuser.controller;

import com.mtech.sjmsuser.model.educationalExperienceDto;
import com.mtech.sjmsuser.model.userProfileDto;
import com.mtech.sjmsuser.model.workingExperienceDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@CrossOrigin
public class userProfileController {

    @GetMapping("/api/v1/user")
    public userProfileDto userProfile() {
        return new userProfileDto("Andrew Tan", "Assistant Engineer", "url", "Job seeker with 10 years experience in programming", new ArrayList<>() {{
            add(new workingExperienceDto(
                    "SPTel",
                    "2012",
                    "2015",
                    "Engineer",
                    "Performed engineering duties in multiple projects"
            ));
            add(new workingExperienceDto(
                    "SPTel",
                    "2012",
                    "2015",
                    "Engineer",
                    "Performed engineering duties in multiple projects"
            ));
            add(new workingExperienceDto(
                    "SPTel",
                    "2012",
                    "2015",
                    "Engineer",
                    "Performed engineering duties in multiple projects"
            ))
            ;
        }}, new ArrayList<>() {{
            add(new educationalExperienceDto(
                    "Hwa Chong Institution",
                    "2000",
                    "2008",
                    "integrated programme"
            ));
            add(new educationalExperienceDto(
                    "Hwa Chong Institution",
                    "2000",
                    "2008",
                    "integrated programme"
            ));
            add(new educationalExperienceDto(
                    "Hwa Chong Institution",
                    "2000",
                    "2008",
                    "integrated programme"
            ))
            ;
        }});
    }
}
