package com.eddgen.iron_notes.service;

import java.util.List;
import java.util.Optional;

import com.eddgen.iron_notes.domain.entities.ExerciseSet;

public interface ExerciseSetService {
    ExerciseSet saveSet(ExerciseSet exerciseSet);

    List<ExerciseSet> findAllSets();

    Optional<ExerciseSet> findSetById(Long id);

    boolean existsSet(Long id);

    ExerciseSet partialUpdateSet(Long id, ExerciseSet exerciseSetEntity);

    void deleteExerciseSet(Long id);

}
