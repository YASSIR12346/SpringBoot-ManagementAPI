package com.example.SpringBootApi.REPOSITORY;

import com.example.SpringBootApi.Model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager,Integer> {

    @Query("SELECT m from Manager m WHERE m.email=?1")
    Optional<Manager> findByEmail(String email);
}
