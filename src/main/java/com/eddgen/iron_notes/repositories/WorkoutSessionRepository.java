package com.eddgen.iron_notes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.eddgen.iron_notes.domain.entities.WorkoutSession;
import java.util.Optional;
import java.time.LocalDate;


@Repository
public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, String>{
    Optional<WorkoutSession> findByWorkoutDate(LocalDate workoutDate);
}
