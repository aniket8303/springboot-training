package com.example.springboot_training.model;

import jakarta.persistence.*;

@Entity
public class RiskAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer staffId;

    private String staffName;

    private int hoursWorked;

    private int consecutiveShifts;

    private int restHours;

    private int departmentWorkload;

    private int experienceYears;

    private int riskScore;

    private String riskLevel;

    public RiskAssessment() {
    }

    public RiskAssessment(
            Integer staffId,
            String staffName,
            int hoursWorked,
            int consecutiveShifts,
            int restHours,
            int departmentWorkload,
            int experienceYears,
            int riskScore,
            String riskLevel) {

        this.staffId = staffId;
        this.staffName = staffName;
        this.hoursWorked = hoursWorked;
        this.consecutiveShifts = consecutiveShifts;
        this.restHours = restHours;
        this.departmentWorkload = departmentWorkload;
        this.experienceYears = experienceYears;
        this.riskScore = riskScore;
        this.riskLevel = riskLevel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public int getConsecutiveShifts() {
        return consecutiveShifts;
    }

    public void setConsecutiveShifts(int consecutiveShifts) {
        this.consecutiveShifts = consecutiveShifts;
    }

    public int getRestHours() {
        return restHours;
    }

    public void setRestHours(int restHours) {
        this.restHours = restHours;
    }

    public int getDepartmentWorkload() {
        return departmentWorkload;
    }

    public void setDepartmentWorkload(int departmentWorkload) {
        this.departmentWorkload = departmentWorkload;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public int getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(int riskScore) {
        this.riskScore = riskScore;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }
}