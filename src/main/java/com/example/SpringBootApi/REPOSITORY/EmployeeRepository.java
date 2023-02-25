package com.example.SpringBootApi.REPOSITORY;

import com.example.SpringBootApi.Model.Employee;
import com.example.SpringBootApi.Model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    @Query("SELECT e from Employee e WHERE e.manager_id=?1")
    List<Employee> findAllEmployees(Integer manager_id);

    @Query("SELECT e from Employee e WHERE e.email=?1")
    Optional<Employee> findByEmail(String email);


    @Query("SELECT e from Employee e WHERE e.id=?1 AND e.manager_id=?2")
    Optional<Employee> findById(Integer id,Integer manager_id);
}
