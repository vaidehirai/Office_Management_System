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

    @GetMapping("/age")
    public ResponseEntity<EmployeeResponse> getMinMaxAge(@RequestParam Integer min, Integer max){
        return service.getMinMaxAge(min, max);
    }

    @GetMapping("/salary/sort")
    public ResponseEntity<EmployeeResponse> getSalarySort(){
        return service.getSalarySort();
    }

    @GetMapping("/top-salary")
    public ResponseEntity<EmployeeResponse> getTopSalary(@RequestParam Integer count){
        return service.getTopSalary(count);
    }

    @GetMapping("/youngest")
    public ResponseEntity<EmployeeResponse> getYoungestEmployee(){
        return service.getYoungestEmployee();
    }

    @GetMapping("/oldest")
    public ResponseEntity<EmployeeResponse> getOldestEmployee(){
        return service.getOldestEmployee();
    }

    @GetMapping("/high-earner")
    public ResponseEntity<APIResponse> getHighEarner(@RequestParam Integer salary){
        return service.getHighEarner(salary);
    }

    @GetMapping("/adults")
    public ResponseEntity<APIResponse> getAdults(){
        return service.getAdults();
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
