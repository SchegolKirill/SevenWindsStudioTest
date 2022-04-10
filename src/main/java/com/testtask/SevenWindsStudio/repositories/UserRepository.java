package com.testtask.SevenWindsStudio.repositories;

import com.testtask.SevenWindsStudio.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
