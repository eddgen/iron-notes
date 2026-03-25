package com.eddgen.iron_notes.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eddgen.iron_notes.domain.entities.WorkoutExercise;

@Repository
public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise,String>{
    List<WorkoutExercise> findByWorkoutSessionId(String workoutSessionId);

    Optional<WorkoutExercise> findByIdAndWorkoutSessionId(String id, String workoutSessionId);
}
