package com.eddgen.iron_notes.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.eddgen.iron_notes.domain.dto.ExerciseSetDto;
import com.eddgen.iron_notes.domain.entities.ExerciseSet;
import com.eddgen.iron_notes.mappers.Mapper;

@Component 
public class ExerciseSetMapperImpl implements Mapper<ExerciseSet, ExerciseSetDto>{

    private ModelMapper modelMapper;

    public ExerciseSetMapperImpl(ModelMapper modelMapper){
        this.modelMapper=modelMapper;
    }

    @Override
    public ExerciseSet mapFromDto(ExerciseSetDto exerciseSetDto) {
        return modelMapper.map(exerciseSetDto, ExerciseSet.class);
    }

    @Override
    public ExerciseSetDto mapToDto(ExerciseSet exerciseSet) {
        return modelMapper.map(exerciseSet, ExerciseSetDto.class);
    }

    
}
