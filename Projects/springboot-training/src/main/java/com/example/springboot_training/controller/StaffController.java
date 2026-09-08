package com.example.springboot_training.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class StaffController {

    // Staff Service is injected
    private final StaffService staffService;

    // Constructor for Staff Service
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    // To get all Staff
    @GetMapping("/api/staff")
    public ResponseEntity<List<Staff>> getStaff() {
        return ResponseEntity.ok(staffService.getStaff());
    }

    // To add staff
    @PostMapping("/api/staff")
    public ResponseEntity<Staff> addStaff(@Valid @RequestBody Staff staff) {
        Staff savedStaff = staffService.addStaff(staff);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedStaff);
    }

    // To get staff by ID
    @GetMapping("/api/staff/{id}")
    public Staff getStaffById(@PathVariable int id) {
        return staffService.getStaffById(id);
    }

    // To update staff by id
    @PutMapping("/api/staff/{id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable int id, @RequestBody Staff staff) {
        return ResponseEntity.ok(staffService.updateStaff(id, staff));
    }

    // TO Delete Staff by Id
    @DeleteMapping("/api/staff/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable int id) {
        staffService.deleteStaff(id);

        return ResponseEntity.noContent().build();
    }

    // To Find Staff By department
    @GetMapping(path = "/api/staff/department")
    public List<Staff> getStaffsByDepartment(@RequestParam String department) {
        return staffService.getStaffsByDepartment(department);
    }

}
