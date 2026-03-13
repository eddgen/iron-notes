package com.eddgen.iron_notes.service;

import java.util.List;

import com.eddgen.iron_notes.domain.entities.Exercise;

public interface ExerciseService {
    Exercise save(Exercise exercise);

    List<Exercise> findAll();

}
