package com.eddgen.iron_notes.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eddgen.iron_notes.domain.dto.WorkoutExerciseDto;
import com.eddgen.iron_notes.domain.entities.WorkoutExercise;
import com.eddgen.iron_notes.mappers.Mapper;
import com.eddgen.iron_notes.service.WorkoutExerciseService;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/workout-sessions/{workout_id}")
public class WorkoutExerciseController {

    private WorkoutExerciseService wrExerciseService;

    private Mapper<WorkoutExercise,WorkoutExerciseDto> wrExerciseMapper;


    public WorkoutExerciseController(WorkoutExerciseService wrExerciseService,
                                    Mapper<WorkoutExercise,WorkoutExerciseDto> wrExerciseMapper) {
        this.wrExerciseService = wrExerciseService;
        this.wrExerciseMapper= wrExerciseMapper;
    }

    // @GetMapping("/wr-exercises")
    // public List<WorkoutExerciseDto> getAllWrExercises() {
    //     List<WorkoutExercise> wrExercises = wrExerciseService.findAllExercises();
    //     return wrExercises.stream().map(
    //         wrExercise -> wrExerciseMapper.mapToDto(wrExercise)).
    //         toList();
    // }

    @GetMapping("/wr-exercises")
    public List<WorkoutExerciseDto> getAllWrExercises(@PathVariable String workout_id) {

        List<WorkoutExercise> wrExercises = wrExerciseService.
        findAllExercisesByWorkoutSessionId(workout_id);

        return wrExercises.stream().map(
        wrExercise -> wrExerciseMapper.mapToDto(wrExercise)).
        toList();
    }
    
    @GetMapping("wr-exercises/{id}")
    public ResponseEntity<WorkoutExerciseDto> getWrExerciseById(
        @PathVariable String workout_id,
        @PathVariable String id) {

        Optional<WorkoutExercise> workoutExercise = wrExerciseService.findByIdAndWorkoutSessionId(id, workout_id);
        return workoutExercise.map(wrExercise -> {
            WorkoutExerciseDto responseWrExerciseDto = wrExerciseMapper.mapToDto(wrExercise);
            return new ResponseEntity<>(responseWrExerciseDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("wr-exercises")
    public ResponseEntity<WorkoutExerciseDto> createWrExercise(
        @PathVariable String workout_id,
        @RequestBody WorkoutExerciseDto wrExerciseDto) {
        WorkoutExercise workoutExercise = wrExerciseMapper.mapFromDto(wrExerciseDto);
        WorkoutExercise savedWorkoutExercise = wrExerciseService.saveWrExercise(workout_id, workoutExercise);
        WorkoutExerciseDto responseExerciseDto = wrExerciseMapper.mapToDto(savedWorkoutExercise);
        return new ResponseEntity<>(responseExerciseDto,HttpStatus.CREATED);
    }
    
    @PatchMapping("wr-exercises/{id}")
    public ResponseEntity<WorkoutExerciseDto> updateWrExercise(
        @PathVariable String workout_id,
        @PathVariable String id,
        @RequestBody WorkoutExerciseDto wrExerciseDto){
            WorkoutExercise workoutExerciseToUpdate = wrExerciseMapper.mapFromDto(wrExerciseDto);
            WorkoutExercise updatedWorkoutExercise = wrExerciseService.updateWrExercise(workout_id, id, workoutExerciseToUpdate);
            WorkoutExerciseDto responseUpdatedWorkoutExercise = wrExerciseMapper.mapToDto(updatedWorkoutExercise);
            return new ResponseEntity<>(responseUpdatedWorkoutExercise,HttpStatus.OK);
        }
    
    @DeleteMapping("wr-exercises/{id}")
    public ResponseEntity<Void> deleteWorkoutExercise(
        @PathVariable String workout_id,
        @PathVariable String id){

        wrExerciseService.deleteWorkoutExercise(workout_id, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
