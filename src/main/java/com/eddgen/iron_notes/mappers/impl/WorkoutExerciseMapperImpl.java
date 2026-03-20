package com.eddgen.iron_notes.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.eddgen.iron_notes.domain.dto.WorkoutExerciseDto;
import com.eddgen.iron_notes.domain.entities.WorkoutExercise;
import com.eddgen.iron_notes.mappers.Mapper;

@Component
public class WorkoutExerciseMapperImpl implements Mapper<WorkoutExercise,WorkoutExerciseDto> {
    
    private ModelMapper modelMapper;
    
    public WorkoutExerciseMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public WorkoutExercise mapFromDto(WorkoutExerciseDto b) {
        return modelMapper.map(b,WorkoutExercise.class);
    }

    @Override
    public WorkoutExerciseDto mapToDto(WorkoutExercise a) {
        return modelMapper.map(a,WorkoutExerciseDto.class);
    }

    

}
