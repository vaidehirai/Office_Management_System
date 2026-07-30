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
    public ResponseEntity<EmployeeListResponse> getAllEmployeeData(){
        return service.getAllEmployeeData();
    }

    @GetMapping("/salary")
    public ResponseEntity<EmployeeListResponse> getSalaryAboveMinSalary(@RequestParam Integer minSalary){
        return service.getSalaryAboveMinSalary(minSalary);
    }

    @GetMapping("/age")
    public ResponseEntity<EmployeeListResponse> getEmployeesBetweenMinMaxAge(@RequestParam Integer min, Integer max){
        return service.getEmployeesBetweenMinMaxAge(min, max);
    }

    @GetMapping("/salary/sort")
    public ResponseEntity<EmployeeListResponse> getSalarySort(){
        return service.getSalarySort();
    }

    @GetMapping("/top-salary")
    public ResponseEntity<EmployeeListResponse> getTopSalaryEmployees(@RequestParam Integer count){
        return service.getTopSalaryEmployees(count);
    }

    @GetMapping("/youngest")
    public ResponseEntity<APIResponse> getYoungestEmployee(){
        return service.getYoungestEmployee();
    }

    @GetMapping("/oldest")
    public ResponseEntity<APIResponse> getOldestEmployee(){
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

    @GetMapping("/names")
    public ResponseEntity<StListResponse> getEmployeeNames(){
        return service.getEmployeeNames();
    }

    @GetMapping("/distinct-ages")
    public ResponseEntity<IntListResponse> getEmployeesDistinctAges(){
        return service.getEmployeesDistinctAges();
    }

    @GetMapping("/count-by-gender")
    public ResponseEntity<LongResponse> getCountByGender(@RequestParam String gender){
        return service.getCountByGender(gender);
    }

    @GetMapping("/group-by-department")
    public ResponseEntity<EmployeeMapResponse> getEmployeesGroupByDept(){
        return service.getEmployeesGroupByDept();
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
