package kea.dat3.repositories;

import kea.dat3.entities.Person;

import java.util.Optional;

public interface UtilityUserMethods<T> {
    Optional<T> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
