package com.eddgen.iron_notes.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.eddgen.iron_notes.domain.dto.ExerciseDto;
import com.eddgen.iron_notes.domain.entities.Exercise;
import com.eddgen.iron_notes.mappers.Mapper;
import com.eddgen.iron_notes.service.ExerciseService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(path = "/exercises")
public class ExercisesController {

    private Mapper<Exercise, ExerciseDto> exerciseMapper;
    
    private ExerciseService exerciseService;

    
    public ExercisesController(Mapper<Exercise, ExerciseDto> exerciseMapper, ExerciseService exerciseService) {
        this.exerciseMapper = exerciseMapper;
        this.exerciseService = exerciseService;
    }

    @PostMapping
    public ResponseEntity<ExerciseDto> createExercise(@RequestBody ExerciseDto exerciseDto) {
        Exercise exercise = exerciseMapper.mapFromDto(exerciseDto);
        Exercise savedExercise = exerciseService.save(exercise);
        return new ResponseEntity<>(exerciseMapper.mapToDto(savedExercise),HttpStatus.CREATED);
    }

    @GetMapping
    public List<ExerciseDto> getAllExercises() {
        List<Exercise> exercises = exerciseService.findAll();
        return exercises.stream()
        .map(exerciseEntity -> exerciseMapper.mapToDto(exerciseEntity))
        .toList();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ExerciseDto> getExerciseById(@PathVariable Long id) {
        Optional<Exercise> optionalExerciseEntity = exerciseService.findById(id);
        return optionalExerciseEntity.map(exercise ->{
            ExerciseDto exerciseDto = exerciseMapper.mapToDto(exercise);
            return new ResponseEntity<>(exerciseDto,HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));       
    }   

    @PatchMapping("/{id}")
    public ResponseEntity<ExerciseDto> partialUpdateExercise(
        @PathVariable Long id,
        @RequestBody ExerciseDto exerciseDto
    ){
        if(!exerciseService.exists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Exercise exerciseEntity = exerciseMapper.mapFromDto(exerciseDto);
        Exercise updatedExercise = exerciseService.partialUpdate(id, exerciseEntity);
        ExerciseDto responseExercise = exerciseMapper.mapToDto(updatedExercise);
        return new ResponseEntity<>(responseExercise,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id){
        exerciseService.deleteExercise(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    

}
