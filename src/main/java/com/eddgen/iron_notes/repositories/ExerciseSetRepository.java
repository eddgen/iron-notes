package com.eddgen.iron_notes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eddgen.iron_notes.domain.entities.ExerciseSet;

@Repository
public interface ExerciseSetRepository extends CrudRepository<ExerciseSet, Long>{

}
