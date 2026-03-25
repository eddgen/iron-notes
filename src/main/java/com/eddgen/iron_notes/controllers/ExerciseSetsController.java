package com.eddgen.iron_notes.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.eddgen.iron_notes.domain.dto.ExerciseSetDto;
import com.eddgen.iron_notes.domain.entities.ExerciseSet;
import com.eddgen.iron_notes.mappers.Mapper;
import com.eddgen.iron_notes.service.ExerciseSetService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(path = 
"/workout-sessions/{workout_id}/wr-exercises/{wr_exercise_id}/exerciseSets")
public class ExerciseSetsController {

    Mapper<ExerciseSet, ExerciseSetDto> setMapper;

    ExerciseSetService exerciseSetService;
    

    public ExerciseSetsController(
        Mapper<ExerciseSet, ExerciseSetDto> setMapper,
        ExerciseSetService exerciseSetService) {
        this.setMapper = setMapper;
        this.exerciseSetService = exerciseSetService;
    }

    @GetMapping
    public List<ExerciseSetDto> getSetsForExerciseFromWorkout(
        @PathVariable String workout_id,
        @PathVariable String wr_exercise_id
    ) {

        List<ExerciseSet> exerciseSets = exerciseSetService.
        findAllSetsForExerciseFromWorkout(workout_id, wr_exercise_id);

        return exerciseSets.stream().map(entitySet ->
            setMapper.mapToDto(entitySet)
        ).toList();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ExerciseSetDto> getSetById(
        @PathVariable String workout_id,
        @PathVariable String wr_exercise_id,
        @PathVariable Long id) {
            
        Optional<ExerciseSet> exerciseSet = exerciseSetService.findById(workout_id,wr_exercise_id,id);
        return exerciseSet.map(exerciseSetEntity -> {
            ExerciseSetDto mappedExerciseSetDto = setMapper.mapToDto(exerciseSetEntity);
            return new ResponseEntity<>(mappedExerciseSetDto,HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    
    }

    @PostMapping
    public ResponseEntity<ExerciseSetDto> createExerciseSet(
        @PathVariable String workout_id,
        @PathVariable String wr_exercise_id,
        @RequestBody ExerciseSetDto setDto) {
        ExerciseSet setEntity = setMapper.mapFromDto(setDto);
        ExerciseSet savedExerciseSet = exerciseSetService.saveExerciseSet(setEntity,wr_exercise_id,workout_id);
        ExerciseSetDto returnedSetDto = setMapper.mapToDto(savedExerciseSet);
        return new ResponseEntity<>(returnedSetDto,HttpStatus.CREATED);
    }
    
    @PatchMapping(path = "/{id}")
    public ResponseEntity<ExerciseSetDto> updateExerciseSet(
        @PathVariable String workout_id,
        @PathVariable String wr_exercise_id,
        @PathVariable Long id,
        @RequestBody ExerciseSetDto setDto
    ){
        ExerciseSet setEntity = setMapper.mapFromDto(setDto);
        ExerciseSet updatedExerciseSet = exerciseSetService.updateExerciseSet(setEntity,id,wr_exercise_id,workout_id);
        ExerciseSetDto responseExerciseSetDto = setMapper.mapToDto(updatedExerciseSet);
        return new ResponseEntity<>(responseExerciseSetDto,HttpStatus.OK);
    }

}
