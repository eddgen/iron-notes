package com.eddgen.iron_notes.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.eddgen.iron_notes.domain.dto.WorkoutSessionDto;
import com.eddgen.iron_notes.domain.entities.WorkoutSession;
import com.eddgen.iron_notes.mappers.Mapper;
import com.eddgen.iron_notes.service.WorkoutSessionService;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/workout-sessions")
public class WorkoutSessionController {
    WorkoutSessionService workoutService;

    Mapper<WorkoutSession,WorkoutSessionDto> workoutMapper;

    public WorkoutSessionController(
        Mapper<WorkoutSession, WorkoutSessionDto> workoutMapper,
        WorkoutSessionService workoutService){
        this.workoutMapper=workoutMapper;
        this.workoutService=workoutService;
    }

    @GetMapping(path = "/day/{date}")
    public ResponseEntity<WorkoutSessionDto> getWorkoutByDate(@PathVariable LocalDate date) {
         Optional<WorkoutSession> workoutSession = workoutService.findByWorkoutDate(date);
         if(workoutSession.isEmpty()){
             WorkoutSessionDto emptySession = WorkoutSessionDto.builder()
                     .workoutDate(date)
                     .exercises(List.of())
                     .build();
             return new ResponseEntity<>(emptySession,HttpStatus.OK);
         }
         WorkoutSessionDto workoutSessionDto= workoutMapper.mapToDto(workoutSession.get());
         return new ResponseEntity<>(workoutSessionDto,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WorkoutSessionDto> createSession(@RequestBody WorkoutSessionDto WorkoutDto) {
        WorkoutSession newSession = workoutMapper.mapFromDto(WorkoutDto);
        WorkoutSession savedSession = workoutService.save(newSession);
        return new ResponseEntity<>(workoutMapper.mapToDto(savedSession), HttpStatus.CREATED);
    }
    
    @PatchMapping(path = "/{id}")
    public ResponseEntity<WorkoutSessionDto> updateWorkout(
        @PathVariable String id,
        @RequestBody WorkoutSessionDto WorkoutDto){
        WorkoutSession workoutEntity = workoutMapper.mapFromDto(WorkoutDto);
        WorkoutSession updatedWorkout = workoutService.update(id, workoutEntity);
        WorkoutSessionDto responsWorkoutDto = workoutMapper.mapToDto(updatedWorkout);
        return new ResponseEntity<>(responsWorkoutDto,HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable String id){
        workoutService.deleteWorkout(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
