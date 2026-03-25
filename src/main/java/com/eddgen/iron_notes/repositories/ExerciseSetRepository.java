package com.eddgen.iron_notes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eddgen.iron_notes.domain.entities.ExerciseSet;
import java.util.List;


@Repository
public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, Long>{
    List<ExerciseSet> findByWorkoutExerciseId(String workoutExerciseId);
}
