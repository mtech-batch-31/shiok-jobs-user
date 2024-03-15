package com.mtech.sjmsuser.mappers;

import com.mtech.sjmsuser.entity.WorkExperience;
import com.mtech.sjmsuser.model.WorkExperienceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {EducationMapper.class, WorkExperienceMapper.class})
public interface WorkExperienceMapper {

    WorkExperienceMapper INSTANCE = Mappers.getMapper(WorkExperienceMapper.class);

    public WorkExperienceDto toDto(WorkExperience workExperience);

    @Mapping(target = "userProfile", ignore = true)
    @Mapping(target = "id", ignore = true)
    public WorkExperience toEntity(WorkExperienceDto workExperienceDto);

}
