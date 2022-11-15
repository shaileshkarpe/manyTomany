package com.crossasyst.manyTomany.controller;


import com.crossasyst.manyTomany.model.DepartmentRequest;
import com.crossasyst.manyTomany.model.DepartmentResponse;
import com.crossasyst.manyTomany.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/departments")
    List<DepartmentRequest> getAllDepartment() {
        return departmentService.getAllDepartments();
    }

    @GetMapping(value = "/departments/{deptId}")
    public ResponseEntity<DepartmentRequest> getByDeptId(@PathVariable Long deptId) {
        DepartmentRequest departmentRequest = departmentService.getByDeptId(deptId);
        return new ResponseEntity<>(departmentRequest, HttpStatus.OK);
    }

    @PostMapping(value = "/departments")
    public ResponseEntity<DepartmentResponse> createDepartment(@RequestBody DepartmentRequest departmentRequest) {
        DepartmentResponse departmentResponse = departmentService.createDepartment(departmentRequest);
        return new ResponseEntity<>(departmentResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/departments/{deptId}")
    public void deleteDepartment(@PathVariable Long deptId) {
        departmentService.deleteDepartment(deptId);
    }
}
