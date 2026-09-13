package com.example.springboot_training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot_training.model.RiskAssessment;

public interface RiskAssessmentRepository
        extends JpaRepository<RiskAssessment, Integer> {

    long countByRiskLevel(String riskLevel);

    List<RiskAssessment> findByRiskLevel(String riskLevel);

    List<RiskAssessment> findByStaffId(Integer staffId);
}