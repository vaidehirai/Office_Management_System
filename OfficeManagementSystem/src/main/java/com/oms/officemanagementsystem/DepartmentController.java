package com.oms.officemanagementsystem;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service){
        this.service=service;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<APIResponse> getDepartment(@PathVariable Integer id){
        return service.getDepartment(id);
    }

    @GetMapping("/deptname/{name}")
    public ResponseEntity<APIResponse> getDepartmentDeptname(@PathVariable String name){
        return service.getDepartmentDeptname(name);
    }

    @GetMapping("/deptcode/{code}")
    public ResponseEntity<APIResponse> getDepartmentDeptcode(@PathVariable String code){
        return service.getDepartmentDeptcode(code);
    }

    @GetMapping("/all")
    public ResponseEntity<DepartmentListResponse> getAllDepartments(){
        return service.getAllDepartments();
    }

    @PostMapping
    public ResponseEntity<APIResponse> createDepartment(@RequestBody Department dept){
        return service.createDepartment(dept);
    }

    @PutMapping
    public ResponseEntity<APIResponse> updateDepartment(@RequestBody Department dept){
        return service.updateDepartment(dept);
    }

    @DeleteMapping
    public ResponseEntity<APIResponse> deleteDepartment(@RequestBody Department dept){
        return service.deleteDepartment(dept);
    }
}
