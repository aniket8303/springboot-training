package com.example.springboot_training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot_training.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    List<Staff> findByDepartmentIgnoreCase(String department);

}