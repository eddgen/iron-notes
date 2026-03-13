package com.eddgen.iron_notes.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.eddgen.iron_notes.domain.dto.ExerciseDto;
import com.eddgen.iron_notes.domain.entities.Exercise;
import com.eddgen.iron_notes.mappers.Mapper;
import com.eddgen.iron_notes.service.ExerciseService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class ExercisesController {

    private Mapper<Exercise, ExerciseDto> exerciseMapper;
    
    private ExerciseService exerciseService;

    
    public ExercisesController(Mapper<Exercise, ExerciseDto> exerciseMapper, ExerciseService exerciseService) {
        this.exerciseMapper = exerciseMapper;
        this.exerciseService = exerciseService;
    }

    @PostMapping("/exercise")
    public ResponseEntity<ExerciseDto> createExercise(@RequestBody ExerciseDto exerciseDto) {
        Exercise exercise = exerciseMapper.mapFromDto(exerciseDto);
        Exercise savedExercise = exerciseService.save(exercise);
        return new ResponseEntity<>(exerciseMapper.mapToDto(savedExercise),HttpStatus.CREATED);
    }

    @GetMapping("/exercise")
    public List<ExerciseDto> getAllExercises() {
        List<Exercise> exercises = exerciseService.findAll();
        return exercises.stream()
        .map(exerciseEntity -> exerciseMapper.mapToDto(exerciseEntity))
        .toList();
    }
    
    

}
