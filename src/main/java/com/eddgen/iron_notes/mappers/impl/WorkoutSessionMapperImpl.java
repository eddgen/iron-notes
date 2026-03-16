package com.eddgen.iron_notes.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.eddgen.iron_notes.domain.dto.WorkoutSessionDto;
import com.eddgen.iron_notes.domain.entities.WorkoutSession;
import com.eddgen.iron_notes.mappers.Mapper;

@Component
public class WorkoutSessionMapperImpl implements Mapper<WorkoutSession, WorkoutSessionDto>{

    private ModelMapper modelMapper;
    
    public WorkoutSessionMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public WorkoutSession mapFromDto(WorkoutSessionDto workoutSessionDto) {
        return modelMapper.map(workoutSessionDto, WorkoutSession.class);
    }

    @Override
    public WorkoutSessionDto mapToDto(WorkoutSession workoutSession) {
        return modelMapper.map(workoutSession, WorkoutSessionDto.class);
    }


}
