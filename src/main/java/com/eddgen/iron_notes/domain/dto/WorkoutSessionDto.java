package com.eddgen.iron_notes.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkoutSessionDto {
   
    private String id;

    private String title; 

    private LocalDate workoutDate;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private Boolean completed;
    
    private List<WorkoutExerciseDto> exercises;
}
