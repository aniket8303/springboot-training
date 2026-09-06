package com.example.springboot_training.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot_training.model.Staff;
import com.example.springboot_training.service.StaffService;

@RestController
public class StaffController {

    // Staff Service is injected
    private final StaffService staffService;

    // Constructor for Staff Service
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    // To get all Staff
    @GetMapping("/api/staff")
    public List<Staff> getStaff() {
        return staffService.getStaff();
    }

    // To add staff
    @PostMapping("/api/staff")
    public Staff addStaff(@RequestBody Staff staff) {
        return staffService.addStaff(staff);
    }

    // To get staff by ID
    @GetMapping("/api/staff/{id}")
    public Staff getStaffById(@PathVariable int id) {
        return staffService.getStaffById(id);
    }

    // To update staff by id
    @PutMapping("/api/staff/{id}")
    public Staff updateStaff(@PathVariable int id, @RequestBody Staff staff) {
        return staffService.updateStaff(id, staff);
    }

    // TO Delete Staff by Id
    @DeleteMapping("/api/staff/{id}")
    public String deleteStaff(@PathVariable int id) {
        return staffService.deleteStaff(id);
    }

    // To Find Staff By department
    @GetMapping(path = "/api/staff/department")
    public List<Staff> getStaffsByDepartment(@RequestParam String department) {
        return staffService.getStaffsByDepartment(department);
    }

}
