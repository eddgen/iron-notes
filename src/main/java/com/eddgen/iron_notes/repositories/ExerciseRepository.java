package com.eddgen.iron_notes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eddgen.iron_notes.domain.entities.Exercise;

@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, Long>{

}
