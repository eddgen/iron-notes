package com.eddgen.iron_notes.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseSetDto {

    private Long id;

    private Integer setNumber;

    private String notes;

    private Double weight;

    private Integer reps;

    private WorkoutExerciseDto workoutExerciseDto;
}
