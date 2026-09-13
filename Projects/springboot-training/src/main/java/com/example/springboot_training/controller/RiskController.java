package com.example.springboot_training.controller;

import org.springframework.web.bind.annotation.*;

import com.example.springboot_training.dto.DepartmentRiskResponse;
import com.example.springboot_training.dto.RiskDashboardResponse;
import com.example.springboot_training.dto.RiskRequest;
import com.example.springboot_training.dto.RiskResponse;
import com.example.springboot_training.model.RiskAssessment;
import com.example.springboot_training.service.RiskService;

import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/risk")
public class RiskController {

    private final RiskService riskService;

    public RiskController(RiskService riskService) {
        this.riskService = riskService;
    }

    @PostMapping("/calculate/{staffId}")
    public RiskResponse calculateRisk(
            @PathVariable Integer staffId,
            @Valid @RequestBody RiskRequest request) {

        return riskService.calculateRisk(
                staffId,
                request);
    }

    @GetMapping("/dashboard")
    public RiskDashboardResponse getDashboard() {

        return riskService.getDashboard();
    }

    @GetMapping("/department-analysis")
    public List<DepartmentRiskResponse> getDepartmentRiskAnalysis() {

        return riskService.getDepartmentRiskAnalysis();
    }

    @GetMapping("/history/{staffId}")
    public List<RiskAssessment> getRiskHistory(
            @PathVariable Integer staffId) {

        return riskService.getRiskHistory(staffId);
    }
}