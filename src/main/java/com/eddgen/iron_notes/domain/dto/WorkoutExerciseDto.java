package com.eddgen.iron_notes.domain.dto;

import java.util.List;

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

    
    private ExerciseDto exercise;

    
    private List<ExerciseSetDto> sets;

    
}
