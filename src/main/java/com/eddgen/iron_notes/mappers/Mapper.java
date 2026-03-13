package com.eddgen.iron_notes.mappers;

public interface Mapper<A,B> {

    A mapFromDto(B b);

    B mapToDto(A a );

}
