package com.eddgen.iron_notes.service.impl;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.eddgen.iron_notes.domain.entities.Exercise;
import com.eddgen.iron_notes.repositories.ExerciseRepository;
import com.eddgen.iron_notes.service.ExerciseService;

@Service
public class ExerciseServiceImpl implements ExerciseService{

    private ExerciseRepository exerciseRepository;
    
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public Exercise save(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    @Override
    public List<Exercise> findAll() {
            return StreamSupport.stream(exerciseRepository.
                                findAll()
                                .spliterator(), 
                                false)
                    .toList();
    }

}
