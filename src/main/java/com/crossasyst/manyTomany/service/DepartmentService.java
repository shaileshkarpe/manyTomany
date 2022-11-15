package com.crossasyst.manyTomany.service;

import com.crossasyst.manyTomany.entity.DepartmentEntity;
import com.crossasyst.manyTomany.mapper.DepartmentMapper;
import com.crossasyst.manyTomany.mapper.EmployeeMapper;
import com.crossasyst.manyTomany.model.DepartmentRequest;
import com.crossasyst.manyTomany.model.DepartmentResponse;

import com.crossasyst.manyTomany.repository.DepartmentRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class    DepartmentService {
    private final DepartmentRepository departmentRepository;
    private  final EmployeeMapper employeeMapper;
private  final DepartmentMapper departmentMapper;
    public DepartmentService(DepartmentRepository departmentRepository, EmployeeMapper employeeMapper, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.employeeMapper = employeeMapper;
        this.departmentMapper = departmentMapper;
    }

    public List<DepartmentRequest> getAllDepartments() {
        List<DepartmentEntity> departmentEntities =departmentRepository.findAll();
        List<DepartmentRequest>departmentRequests =departmentMapper.entityToDeptModels(departmentEntities);
        return departmentRequests;
    }
//    @Cacheable(cacheNames = "employee", key = "empId")
    public DepartmentRequest getByDeptId(Long deptId) {
        DepartmentEntity departmentEntity = departmentRepository.findById(deptId).get();
        DepartmentRequest departmentRequest = departmentMapper.entityToDeptModel(departmentEntity);
        return  departmentRequest;
    }


    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest) {
        DepartmentEntity departmentEntity =departmentRepository.save(departmentMapper.DeptModelToEntity(departmentRequest));
        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.setDeptId(departmentEntity.getDeptId());
        return  departmentResponse;
    }

    public void deleteDepartment(Long deptId) {
        departmentRepository.deleteById(deptId);
    }
}



