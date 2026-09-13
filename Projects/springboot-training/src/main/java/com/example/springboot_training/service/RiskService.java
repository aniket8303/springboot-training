package com.example.springboot_training.service;

import org.springframework.stereotype.Service;

import com.example.springboot_training.dto.RiskDashboardResponse;
import com.example.springboot_training.dto.RiskRequest;
import com.example.springboot_training.dto.RiskResponse;
import com.example.springboot_training.model.RiskAssessment;
import com.example.springboot_training.model.Staff;
import com.example.springboot_training.repository.RiskAssessmentRepository;
import com.example.springboot_training.repository.StaffRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.example.springboot_training.dto.DepartmentRiskResponse;

@Service
public class RiskService {

    private final StaffRepository staffRepository;
    private final RiskAssessmentRepository riskAssessmentRepository;

    public RiskService(
            StaffRepository staffRepository,
            RiskAssessmentRepository riskAssessmentRepository) {

        this.staffRepository = staffRepository;
        this.riskAssessmentRepository = riskAssessmentRepository;
    }

    public RiskResponse calculateRisk(
            Integer staffId,
            RiskRequest request) {

        // Find staff
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        int score = 0;

        // 1. Hours Worked
        if (request.getHoursWorked() <= 8) {
            score += 0;
        } else if (request.getHoursWorked() <= 10) {
            score += 10;
        } else {
            score += 20;
        }

        // 2. Consecutive Shifts
        if (request.getConsecutiveShifts() <= 2) {
            score += 0;
        } else if (request.getConsecutiveShifts() <= 4) {
            score += 10;
        } else {
            score += 20;
        }

        // 3. Rest Hours
        if (request.getRestHours() >= 12) {
            score += 0;
        } else if (request.getRestHours() >= 8) {
            score += 10;
        } else {
            score += 20;
        }

        // 4. Department Workload
        if (request.getDepartmentWorkload() <= 60) {
            score += 0;
        } else if (request.getDepartmentWorkload() <= 80) {
            score += 10;
        } else {
            score += 20;
        }

        // 5. Experience
        if (request.getExperienceYears() >= 5) {
            score += 0;
        } else if (request.getExperienceYears() >= 2) {
            score += 5;
        } else {
            score += 10;
        }

        // Determine Risk Level
        String riskLevel;

        if (score <= 29) {
            riskLevel = "LOW";
        } else if (score <= 59) {
            riskLevel = "MEDIUM";
        } else {
            riskLevel = "HIGH";
        }

        // Create Risk Assessment
        RiskAssessment assessment = new RiskAssessment(
                staff.getId(),
                staff.getName(),
                request.getHoursWorked(),
                request.getConsecutiveShifts(),
                request.getRestHours(),
                request.getDepartmentWorkload(),
                request.getExperienceYears(),
                score,
                riskLevel);

        // Save assessment
        riskAssessmentRepository.save(assessment);

        // Return response
        return new RiskResponse(
                staff.getId(),
                staff.getName(),
                score,
                riskLevel);
    }

    // Dashboard
    public RiskDashboardResponse getDashboard() {

        long totalStaff = staffRepository.count();

        long totalAssessments = riskAssessmentRepository.count();

        long highRisk = riskAssessmentRepository.countByRiskLevel("HIGH");

        long mediumRisk = riskAssessmentRepository.countByRiskLevel("MEDIUM");

        long lowRisk = riskAssessmentRepository.countByRiskLevel("LOW");

        return new RiskDashboardResponse(
                totalStaff,
                totalAssessments,
                highRisk,
                mediumRisk,
                lowRisk);
    }

    public List<DepartmentRiskResponse> getDepartmentRiskAnalysis() {

        List<RiskAssessment> assessments = riskAssessmentRepository.findAll();

        Map<String, long[]> departmentData = new HashMap<>();

        for (RiskAssessment assessment : assessments) {

            Staff staff = staffRepository
                    .findById(assessment.getStaffId())
                    .orElse(null);

            if (staff == null) {
                continue;
            }

            String department = staff.getDepartment();

            departmentData.putIfAbsent(
                    department,
                    new long[4]);

            long[] data = departmentData.get(department);

            // Total assessments
            data[0]++;

            // Risk level
            if ("HIGH".equals(assessment.getRiskLevel())) {
                data[1]++;
            } else if ("MEDIUM".equals(assessment.getRiskLevel())) {
                data[2]++;
            } else if ("LOW".equals(assessment.getRiskLevel())) {
                data[3]++;
            }
        }

        List<DepartmentRiskResponse> response = new ArrayList<>();

        for (Map.Entry<String, long[]> entry : departmentData.entrySet()) {

            String department = entry.getKey();
            long[] data = entry.getValue();

            response.add(
                    new DepartmentRiskResponse(
                            department,
                            data[0],
                            data[1],
                            data[2],
                            data[3]));
        }

        return response;
    }

    public List<RiskAssessment> getRiskHistory(Integer staffId) {

        // Verify staff exists
        staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        return riskAssessmentRepository.findByStaffId(staffId);
    }
}