package com.eddgen.iron_notes.service;

import java.util.List;
import java.util.Optional;

import com.eddgen.iron_notes.domain.entities.WorkoutExercise;

public interface WorkoutExerciseService {
    // List<WorkoutExercise> findAllExercises();

    List<WorkoutExercise> findAllExercisesByWorkoutSessionId(String workout_id);

    Optional<WorkoutExercise> findByIdAndWorkoutSessionId(String id, String workoutSessionId);

    WorkoutExercise saveWrExercise(String workout_id, WorkoutExercise workoutExercise);

    WorkoutExercise updateWrExercise(String workout_id, String id, WorkoutExercise workoutExerciseToUpdate);
}
