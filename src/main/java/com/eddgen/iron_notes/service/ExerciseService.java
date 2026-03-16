package com.eddgen.iron_notes.service;

import java.util.List;
import java.util.Optional;

import com.eddgen.iron_notes.domain.entities.Exercise;

public interface ExerciseService {
    Exercise save(Exercise exercise);

    List<Exercise> findAll();

    Optional<Exercise> findById(Long id);

    boolean exists(Long id);

    Exercise partialUpdate(Long id, Exercise exerciseEntity);

    void deleteExercise(Long id);

}
