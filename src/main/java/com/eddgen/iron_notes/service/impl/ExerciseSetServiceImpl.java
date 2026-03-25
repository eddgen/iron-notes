package com.eddgen.iron_notes.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eddgen.iron_notes.domain.entities.ExerciseSet;
import com.eddgen.iron_notes.domain.entities.WorkoutExercise;
import com.eddgen.iron_notes.repositories.ExerciseSetRepository;
import com.eddgen.iron_notes.service.ExerciseSetService;
import com.eddgen.iron_notes.service.WorkoutExerciseService;

@Service
public class ExerciseSetServiceImpl implements ExerciseSetService {

    ExerciseSetRepository exerciseSetRepository;
    
    WorkoutExerciseService workoutExerciseService;

    public ExerciseSetServiceImpl(ExerciseSetRepository exerciseSetRepository,
        WorkoutExerciseService workoutExerciseService
    ) {
        this.exerciseSetRepository = exerciseSetRepository;
        this.workoutExerciseService = workoutExerciseService;
    }


    @Override
    public List<ExerciseSet> findAllSetsForExerciseFromWorkout(String workout_id, String wr_exercise_id) {
        workoutExerciseService.
        findByIdAndWorkoutSessionId(wr_exercise_id,workout_id).
        orElseThrow(() -> new RuntimeException("WorkoutExercise not found"));
        return exerciseSetRepository.findByWorkoutExerciseId(wr_exercise_id);
    }


    @Override
    public Optional<ExerciseSet> findById(String workout_id, String wr_exercise_id, Long id) {
        workoutExerciseService.findByIdAndWorkoutSessionId(wr_exercise_id, workout_id)
        .orElseThrow(() -> new RuntimeException("WorkoutExercise not found"));
        return exerciseSetRepository.findById(id);
    }


    @Override
    public ExerciseSet saveExerciseSet(ExerciseSet Set , String wr_exercise_id, String workout_id) {
        WorkoutExercise workoutExercise = workoutExerciseService.findByIdAndWorkoutSessionId(wr_exercise_id, workout_id)
                .orElseThrow(() -> new RuntimeException("WorkoutExercise not found"));
        
        Set.setWorkoutExercise(workoutExercise);
        return exerciseSetRepository.save(Set);
        
    }


    @Override
    public ExerciseSet updateExerciseSet(ExerciseSet setEntityForUpdating, Long id, String wr_exercise_id, String workout_id) {
        workoutExerciseService.findByIdAndWorkoutSessionId(wr_exercise_id, workout_id)
                .orElseThrow(() -> new RuntimeException("WorkoutExercise not found"));

        Optional<ExerciseSet> existingExerciseSet = exerciseSetRepository.findById(id);
        
        return existingExerciseSet.map(existingSet -> {
            Optional.ofNullable(setEntityForUpdating.getNotes()).ifPresent(existingSet::setNotes);
            Optional.ofNullable(setEntityForUpdating.getReps()).ifPresent(existingSet::setReps);
            Optional.ofNullable(setEntityForUpdating.getSetNumber()).ifPresent(existingSet::setSetNumber);
            Optional.ofNullable(setEntityForUpdating.getWeight()).ifPresent(existingSet::setWeight);

            return exerciseSetRepository.save(existingSet); 
        }).orElseThrow(()-> new RuntimeException("Set does not exist"));
    }

    

}
