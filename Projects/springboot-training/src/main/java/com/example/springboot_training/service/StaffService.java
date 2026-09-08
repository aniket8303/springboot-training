package com.example.springboot_training.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springboot_training.model.Staff;
import com.example.springboot_training.repository.StaffRepository;

@Service
public class StaffService {

    // to create a list of staff
    private final StaffRepository staffRepository;

    // constructor to add a staff
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    // to get all staff
    public List<Staff> getStaff() {
        return staffRepository.findAll();
    }

    // to add staff
    public Staff addStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    // to get staff by id
    public Staff getStaffById(int id) {
        return staffRepository.findById(id).orElseThrow(() -> new RuntimeException("Staff Not Found"));
    }

    // to update staff role
    public Staff updateStaff(int id, Staff staff) {
        Staff existingStaff = staffRepository.findById(id).orElseThrow(() -> new RuntimeException("Staff Not Found"));

        if (existingStaff != null) {
            existingStaff.setName(staff.getName());
            existingStaff.setDepartment(staff.getDepartment());
            existingStaff.setRole(staff.getRole());

            return staffRepository.save(existingStaff);
        }
        return null;
    }

    // to delete staff
    public void deleteStaff(Integer id) {

        staffRepository.deleteById(id);
    }

    // to find staff by department
    public List<Staff> getStaffsByDepartment(String department) {

        return staffRepository.findByDepartmentIgnoreCase(department);
    }

}
