package com.eddgen.iron_notes.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eddgen.iron_notes.domain.entities.WorkoutSession;
import com.eddgen.iron_notes.exceptions.ResourceNotFoundException;
import com.eddgen.iron_notes.repositories.WorkoutSessionRepository;
import com.eddgen.iron_notes.service.WorkoutSessionService;

@Service
public class WorkoutSessionServiceImpl implements WorkoutSessionService{

    WorkoutSessionRepository workoutRepository;
    
    public WorkoutSessionServiceImpl(WorkoutSessionRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    @Override
    public Optional<WorkoutSession> findByWorkoutDate(LocalDate date) {
        return workoutRepository.findByWorkoutDate(date);
    }

    @Override
    public WorkoutSession save(WorkoutSession workoutSession) {
        return workoutRepository.save(workoutSession);
    }

    @Override
    public WorkoutSession update(String id, WorkoutSession workoutSession) {
        Optional<WorkoutSession> existingWorkoutEntity = workoutRepository.findById(id);

        return existingWorkoutEntity.map(existingWorkout -> {
            Optional.ofNullable(workoutSession.getTitle()).ifPresent(existingWorkout::setTitle);
            Optional.ofNullable(workoutSession.getWorkoutDate()).ifPresent(existingWorkout::setWorkoutDate);
            Optional.ofNullable(workoutSession.getStartedAt()).ifPresent(existingWorkout::setStartedAt);
            Optional.ofNullable(workoutSession.getEndedAt()).ifPresent(existingWorkout::setEndedAt);
            Optional.ofNullable(workoutSession.getCompleted()).ifPresent(existingWorkout::setCompleted);

            return workoutRepository.save(existingWorkout);
        }).orElseThrow(() -> new ResourceNotFoundException("Workout session not found with id: " + id));
    }

    @Override
    public boolean exists(String id) {
        return workoutRepository.existsById(id);
    }

    @Override
    public void deleteWorkout(String id) {
        workoutRepository.deleteById(id);
    }
    

}
