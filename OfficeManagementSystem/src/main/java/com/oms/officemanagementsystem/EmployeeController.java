package com.oms.officemanagementsystem;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service){
        this.service=service;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<APIResponse> getEmployeeData(@PathVariable Integer id){
        return service.getEmployeeData(id);
    }

    @GetMapping("/deptid/{deptid}")
    public ResponseEntity<APIResponse> getEmployeeDataDeptid(@PathVariable Integer deptid){
        return service.getEmployeeDataDeptid(deptid);
    }

    @GetMapping("/projectid/{projectid}")
    public ResponseEntity<APIResponse> getEmployeeDataProjectid(@PathVariable Integer projectid){
        return service.getEmployeeDataProjectid(projectid);
    }

    @GetMapping("/all")
    public ResponseEntity<EmployeeResponse> getAllEmployeeData(){
        return service.getAllEmployeeData();
    }

    @GetMapping("/salary")
    public ResponseEntity<EmployeeResponse> getMinSalary(@RequestParam Integer minSalary){
        return service.getMinSalary(minSalary);
    }

    @PostMapping
    public  ResponseEntity<APIResponse> createEmployee(@RequestBody Employee employee){
        return service.createEmployee(employee);
    }

    @PutMapping
    public ResponseEntity<APIResponse> updateEmployee(@RequestBody Employee employee){
        return service.updateEmployee(employee);
    }

    @DeleteMapping
    public ResponseEntity<APIResponse> deleteEmployee(@RequestBody Employee employee){
        return service.deleteEmployee(employee);
    }
}
