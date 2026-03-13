package com.eddgen.iron_notes.domain.dto;

import java.util.List;

import com.eddgen.iron_notes.domain.entities.Exercise;
import com.eddgen.iron_notes.domain.entities.ExerciseSet;
import com.eddgen.iron_notes.domain.entities.WorkoutSession;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutExerciseDto {
     
    private String id;

    
    private Exercise exercise;

    
    private List<ExerciseSet> sets;

    
    private WorkoutSession workoutSession;
    
}
