package com.eddgen.iron_notes.service.impl;

import java.util.List;
import java.util.Optional;
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

    @Override
    public Optional<Exercise> findById(Long id) {
        return exerciseRepository.findById(id);
    }

    @Override
    public boolean exists(Long id) {
        return exerciseRepository.existsById(id);
    }

    @Override
    public Exercise partialUpdate(Long id, Exercise exerciseEntity) {
        Optional<Exercise> existingExerciseEntity = exerciseRepository.findById(id);
        return existingExerciseEntity.map(existingExercise -> {
            Optional.ofNullable(exerciseEntity.getCategory()).ifPresent(existingExercise::setCategory);
            Optional.ofNullable(exerciseEntity.getName()).ifPresent(existingExercise::setName);
            Optional.ofNullable(exerciseEntity.getEquipment()).ifPresent(existingExercise::setEquipment);
            return exerciseRepository.save(existingExercise);
        }).orElseThrow(() -> new RuntimeException("Exercise does not exist")); 
    }

    @Override
    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }

    

    

}
