package com.example.springboot_training.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springboot_training.model.Staff;

@Service
public class StaffService {

    // to create a list of staff
    private final List<Staff> staffList = new ArrayList<>();

    // constructor to add a staff
    public StaffService() {
        staffList.add(new Staff(1, "Rahul", "Cardiology", "Nurse"));
        staffList.add(new Staff(2, "Priya", "Emergency", "Doctor"));
        staffList.add(new Staff(3, "Amit", "Neurology", "Nurse"));
    }

    // to get all staff
    public List<Staff> getStaff() {
        return staffList;
    }

    // to add staff
    public Staff addStaff(Staff staff) {
        staffList.add(staff);
        return staff;
    }

    // to get staff by id
    public Staff getStaffById(int id) {
        for (Staff staff : staffList) {
            if (staff.getId() == id) {
                return staff;
            }
        }
        return null;
    }

    // to update staff role
    public Staff updateStaff(int id, Staff staff) {
        for (Staff staff2 : staffList) {
            if (staff2.getId() == id) {
                staff2.setName(staff.getName());
                staff2.setDepartment(staff.getDepartment());
                staff2.setRole(staff.getRole());
                return staff2;
            }
        }
        return null;
    }

    // to delete staff
    public String deleteStaff(int id) {
        boolean removed = staffList.removeIf(staff -> staff.getId() == id);

        if (removed) {
            return "Staff deleted successfully";
        }

        return "Staff not found";
    }

    // to find staff by department
    public List<Staff> getStaffsByDepartment(String department) {
        List<Staff> staffByDepartment = new ArrayList<>();
        for (Staff staff : staffList) {
            if (staff.getDepartment().equalsIgnoreCase(department)) {
                staffByDepartment.add(staff);
            }
        }
        return staffByDepartment;
    }
}
