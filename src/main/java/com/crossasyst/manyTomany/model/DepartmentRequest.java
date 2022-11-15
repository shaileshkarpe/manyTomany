package com.crossasyst.manyTomany.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequest {
    private String deptId;
    private String deptName;

}

