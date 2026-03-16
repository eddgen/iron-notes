package com.eddgen.iron_notes.service;

import java.time.LocalDate;
import java.util.Optional;

import com.eddgen.iron_notes.domain.entities.WorkoutSession;

public interface WorkoutSessionService {

    Optional<WorkoutSession> findByWorkoutDate(LocalDate workouDate);

    WorkoutSession save(WorkoutSession workoutSession);

    WorkoutSession update(String id, WorkoutSession workoutSession);

    boolean exists(String id);

    

}
