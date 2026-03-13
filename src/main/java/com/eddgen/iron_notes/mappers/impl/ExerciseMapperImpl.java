package com.eddgen.iron_notes.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.eddgen.iron_notes.domain.dto.ExerciseDto;
import com.eddgen.iron_notes.domain.entities.Exercise;
import com.eddgen.iron_notes.mappers.Mapper;

@Component 
public class ExerciseMapperImpl implements Mapper<Exercise, ExerciseDto>{

    private ModelMapper modelMapper;

    public ExerciseMapperImpl(ModelMapper modelMapper){
        this.modelMapper=modelMapper;
    }

    @Override
    public Exercise mapFromDto(ExerciseDto exerciseDto) {
        return modelMapper.map(exerciseDto, Exercise.class);
    }

    @Override
    public ExerciseDto mapToDto(Exercise exercise) {
        return modelMapper.map(exercise, ExerciseDto.class);
    }

    
}
