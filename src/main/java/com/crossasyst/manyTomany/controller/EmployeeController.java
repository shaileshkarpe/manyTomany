package com.crossasyst.manyTomany.controller;

import com.crossasyst.manyTomany.model.DepartmentRequest;
import com.crossasyst.manyTomany.model.EmployeeRequest;
import com.crossasyst.manyTomany.model.EmployeeResponse;
import com.crossasyst.manyTomany.service.DepartmentService;
import com.crossasyst.manyTomany.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }




    @GetMapping(value = "/employees")
    List<EmployeeRequest> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping(value = "/employees/{empId}")
    public ResponseEntity<EmployeeRequest> getById(@PathVariable Long empId) {
        EmployeeRequest employeeRequest = employeeService.getById(empId);
        return new ResponseEntity<>(employeeRequest, HttpStatus.OK);
    }

    @PostMapping(value = "/employees")
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = employeeService.createEmployee(employeeRequest);

        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/employees/{empId}")
    public ResponseEntity<EmployeeRequest> updateEmployee(@PathVariable Long empId, @RequestBody EmployeeRequest employeeRequest) {

        EmployeeRequest employeeRequest1 = employeeService.updateEmployee(empId, employeeRequest);
        return new ResponseEntity<>(employeeRequest1, HttpStatus.OK);
    }

    @DeleteMapping(value = "/employees/{empId}")
    public void deleteEmployee(@PathVariable Long empId) {
        employeeService.deleteEmployee(empId);
    }

    @GetMapping(value = "/employee/{employeeId}/department")
    public ResponseEntity<List<DepartmentRequest>> findDepartmentByEmployeeId(@PathVariable Long employeeId) {
        List<DepartmentRequest> departmentRequests = employeeService.findDepartmentByEmployeeId(employeeId);
        return new ResponseEntity<>(departmentRequests, HttpStatus.OK);
    }

    @PutMapping(value = "/employees/{empId}/departments/{deptId}")
    public ResponseEntity<EmployeeRequest> addDeptToEmp(@PathVariable Long empId, @PathVariable Long deptId) {
        return new ResponseEntity<>(employeeService.addDepartmentToEmployee(empId, deptId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/employees/{empId}/departments/{deptId}")
    public ResponseEntity<?> removeDeptFromEmp(@PathVariable Long empId, @PathVariable Long deptId) {
        return new ResponseEntity<>(employeeService.removeDepartmentFromEmployee(empId, deptId), HttpStatus.OK);
    }

}