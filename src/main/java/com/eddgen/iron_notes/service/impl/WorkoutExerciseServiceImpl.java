package com.eddgen.iron_notes.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.eddgen.iron_notes.domain.entities.WorkoutExercise;
import com.eddgen.iron_notes.domain.entities.WorkoutSession;
import com.eddgen.iron_notes.repositories.WorkoutExerciseRepository;
import com.eddgen.iron_notes.repositories.WorkoutSessionRepository;
import com.eddgen.iron_notes.service.WorkoutExerciseService;

@Service
public class WorkoutExerciseServiceImpl implements WorkoutExerciseService{
    
    WorkoutExerciseRepository wrExerciseRepository;

    WorkoutSessionRepository workoutSessionRepository;
    
    public WorkoutExerciseServiceImpl(WorkoutExerciseRepository wrExerciseRepository,
            WorkoutSessionRepository workoutSessionRepository
    ) {
        this.wrExerciseRepository = wrExerciseRepository;
        this.workoutSessionRepository = workoutSessionRepository;
    }

    // @Override
    // public List<WorkoutExercise> findAllExercises() {
    //     return StreamSupport.stream(wrExerciseRepository.
    //         findAll().
    //         spliterator() ,
    //         false)
    //     .toList();
    // }

    @Override
    public List<WorkoutExercise> findAllExercisesByWorkoutSessionId(String workout_id) {
      return wrExerciseRepository.findByWorkoutSessionId(workout_id);
    }

    @Override
    public Optional<WorkoutExercise> findByIdAndWorkoutSessionId(String id, String workoutSessionId) {
        return wrExerciseRepository.findByIdAndWorkoutSessionId(id, workoutSessionId);
    }

    @Override
    public WorkoutExercise saveWrExercise(String workout_id, WorkoutExercise workoutExercise) {
        WorkoutSession workoutSession = workoutSessionRepository.findById(workout_id)
            .orElseThrow(() -> new RuntimeException("Workout session not found: " + workout_id));

        workoutExercise.setWorkoutSession(workoutSession);
        return wrExerciseRepository.save(workoutExercise);
    }

    @Override
    public WorkoutExercise updateWrExercise(String workout_id, String id, WorkoutExercise workoutExerciseToUpdate) {
        WorkoutSession workoutSession = workoutSessionRepository.findById(workout_id)
                .orElseThrow(() -> new RuntimeException("Workout session not found: " + workout_id));
                
        workoutExerciseToUpdate.setWorkoutSession(workoutSession);

        Optional<WorkoutExercise> existingWorkoutExercise = wrExerciseRepository.findByIdAndWorkoutSessionId(id, workout_id);

        return existingWorkoutExercise.map(existingWrExercise -> {
            Optional.ofNullable(workoutExerciseToUpdate.getExercise()).ifPresent(existingWrExercise::setExercise);
            Optional.ofNullable(workoutExerciseToUpdate.getSets()).ifPresent(existingWrExercise::setSets);
            Optional.ofNullable(workoutExerciseToUpdate.getWorkoutSession()).ifPresent(existingWrExercise::setWorkoutSession);

            return wrExerciseRepository.save(existingWrExercise);
        }).orElseThrow(()->new RuntimeException("Exercise from workout session not found"));
        
        
    }

    @Override
    public void deleteWorkoutExercise(String workout_id, String id) {
        wrExerciseRepository.findByIdAndWorkoutSessionId(id, workout_id)
                .orElseThrow(() -> new RuntimeException("Exercise Not found"));
        wrExerciseRepository.deleteById(id);
    }

    

    

    

    

}
