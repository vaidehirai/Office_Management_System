package com.oms.officemanagementsystem;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService deptservice;

    public DepartmentController(DepartmentService deptservice){
        this.deptservice = deptservice;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<APIResponse> getDepartment(@PathVariable Integer id){
        return deptservice.getDepartment(id);
    }

    @GetMapping("/deptname/{name}")
    public ResponseEntity<APIResponse> getDepartmentDeptname(@PathVariable String name){
        return deptservice.getDepartmentDeptname(name);
    }

    @GetMapping("/deptcode/{code}")
    public ResponseEntity<APIResponse> getDepartmentDeptcode(@PathVariable String code){
        return deptservice.getDepartmentDeptcode(code);
    }

    @GetMapping("/all")
    public ResponseEntity<DepartmentListResponse> getAllDepartments(){
        return deptservice.getAllDepartments();
    }

    @GetMapping("/names")
    public ResponseEntity<StListResponse> getDepartmentNames(){
        return deptservice.getDepartmentNames();
    }

    @GetMapping("/employee-count")
    public ResponseEntity<LongResponse> getDepartmentWiseEmployeeCount(@RequestParam Integer deptid){
        return deptservice.getDepartmentWiseEmployeeCount(deptid);
    }

    @GetMapping("/average-salary")
    public ResponseEntity<PriMapResponse> getDepartmentWiseAverageSalary(){
        return deptservice.getDepartmentWiseAverageSalary();
    }

    @GetMapping("/total-salary")
    public ResponseEntity<PriMapResponse> getDepartmentWiseTotalSalary(){
        return deptservice.getDepartmentWiseTotalSalary();
    }

    @GetMapping("/employee-names")
    public ResponseEntity<Map<Integer, List<String>>> getDepartmentWiseEmployeeNames(){
        return deptservice.getDepartmentWiseEmployeeNames();
    }

    @PostMapping
    public ResponseEntity<APIResponse> createDepartment(@RequestBody Department dept){
        return deptservice.createDepartment(dept);
    }

    @PutMapping
    public ResponseEntity<APIResponse> updateDepartment(@RequestBody Department dept){
        return deptservice.updateDepartment(dept);
    }

    @DeleteMapping
    public ResponseEntity<APIResponse> deleteDepartment(@RequestBody Department dept){
        return deptservice.deleteDepartment(dept);
    }
}
