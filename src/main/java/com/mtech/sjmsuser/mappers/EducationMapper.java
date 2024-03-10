package com.mtech.sjmsuser.mappers;

import com.mtech.sjmsuser.entity.Education;
import com.mtech.sjmsuser.model.EducationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EducationMapper {

    EducationMapper INSTANCE = Mappers.getMapper(EducationMapper.class);

    public EducationDto toDto(Education education);

    @Mapping(target = "userProfile", ignore = true)
    @Mapping(target = "id", ignore = true)
    public Education toEntity(EducationDto educationDto);

}
