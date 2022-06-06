package com.sales.presentation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sales.application.DepartmentService;
import com.sales.domain.department.Department;
import com.sales.presentation.dto.DepartmentGetRequest;
import com.sales.presentation.dto.DepartmentGetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Scope("prototype")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final Department department;

    @Autowired
    public DepartmentController(
            DepartmentService departmentService,
            Department department) {
        this.departmentService = departmentService;
        this.department = department;
    }

    @RequestMapping(value = "/api/department", method = RequestMethod.GET)
    public DepartmentGetResponse getDepartment(@RequestBody DepartmentGetRequest departmentGetRequest){
        Department department = this.department.createDepartment();
        department = this.departmentService.getDepartmentByCd(new ObjectMapper().convertValue(departmentGetRequest, new TypeReference<>() { }));

        return getDepartmentGetResponse(department);
    }

    private DepartmentGetResponse getDepartmentGetResponse(Department department) {
        DepartmentGetResponse departmentGetResponse = new DepartmentGetResponse();

        departmentGetResponse.setDepartmentCd(department.getDepartmentCd());
        departmentGetResponse.setDepartmentNameEn(department.getDepartmentNameEn());
        departmentGetResponse.setDepartmentNameJa(department.getDepartmentNameJa());
        Optional.ofNullable(department.getExpirationStart()).ifPresent(object -> departmentGetResponse.setExpirationStart(object.toString()));
        Optional.ofNullable(department.getExpirationEnd()).ifPresent(object -> departmentGetResponse.setExpirationEnd(object.toString()));
        Optional.ofNullable(department.getExpirationDate()).ifPresent(object -> departmentGetResponse.setExpirationDate(object.toString()));
        Optional.ofNullable(department.getInsertTimestamp()).ifPresent(object -> departmentGetResponse.setInsertTimestamp(object.toString()));
        departmentGetResponse.setInsertUser(department.getInsertUser());
        Optional.ofNullable(department.getInsertTimestamp()).ifPresent(object -> departmentGetResponse.setUpdateTimestamp(object.toString()));
        departmentGetResponse.setUpdateUser(department.getUpdateUser());

        return departmentGetResponse;
    }
}
