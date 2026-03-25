package com.eddgen.iron_notes.service;

import java.util.List;
import java.util.Optional;

import com.eddgen.iron_notes.domain.dto.ExerciseSetDto;
import com.eddgen.iron_notes.domain.entities.ExerciseSet;

public interface ExerciseSetService {
    

    List<ExerciseSet> findAllSetsForExerciseFromWorkout(String workout_id, String wr_exercise_id);

    Optional<ExerciseSet> findById(String workout_id, String wr_exercise_id, Long id);

    ExerciseSet saveExerciseSet(ExerciseSet setEntity, String wr_exercise_id, String workout_id);

    ExerciseSet updateExerciseSet(ExerciseSet setEntity, Long id, String wr_exercise_id, String workout_id);

}
