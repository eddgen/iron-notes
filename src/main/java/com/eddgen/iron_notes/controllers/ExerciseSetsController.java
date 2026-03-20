package com.eddgen.iron_notes.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.eddgen.iron_notes.domain.dto.ExerciseSetDto;
import com.eddgen.iron_notes.domain.entities.ExerciseSet;
import com.eddgen.iron_notes.mappers.Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping(path = 
    "/workoutSessions/{workout_session_id}/workoutExercises/{workout_exercises_id}/exerciseSets")
public class ExerciseSetsController {
    Mapper<ExerciseSet, ExerciseSetDto> exerciseSetMapper;

    

    public ExerciseSetsController(
        Mapper<ExerciseSet, ExerciseSetDto> exerciseSetMapper) {
        this.exerciseSetMapper = exerciseSetMapper;
        
    }

    @GetMapping
    public String getMethodName() {
        return new String();
    }
    
}
