package com.oms.officemanagementsystem;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

//This class in querying the database. No SElECT* type queries are required to query database.
//VSCode is simply used to check if the queries have been implemented successfully or not.
//Database is not hosted in VSCode. It is hosted on a cloud called "neon_db" and accessed through VSCode.

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<APIResponse> getEmployeeData(int id) {
        Optional<Employee> emp = repository.findById(id);

        if (emp.isPresent()) {
            Employee e=emp.get();
            EmployeeResponse er = new EmployeeResponse();
            er.setEmployee(e);
            er.setMessage("Employee details found");
            return ResponseEntity.status(HttpStatus.OK).body(er);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse("Invalid Id"));
        }
    }

    public ResponseEntity<APIResponse> getEmployeeDataDeptid(Integer deptid){
        ArrayList<Employee> aemp=new ArrayList<>();
        for(int i=1;i<=repository.count();i++){
            Optional<Employee> emp=repository.findById(i);
            Employee e=emp.get();
            if(e.getDeptid()==deptid){
                aemp.add(e);
            }
        }

        if(aemp.isEmpty()){
            APIResponse apir=new APIResponse();
            apir.setMessage("No employee found with given department id");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apir);
        }
        else{
            EmployeeListResponse er=new EmployeeListResponse();
            er.setAle(aemp);
            er.setMessage("Employees with given department id found");
            return ResponseEntity.status(HttpStatus.OK).body(er);
        }
    }

    public ResponseEntity<APIResponse> getEmployeeDataProjectid(Integer projectid){
        ArrayList<Employee> aemp=new ArrayList<>();
        for(int i=1;i<=repository.count();i++){
            Optional<Employee> emp=repository.findById(i);
            Employee e=emp.get();
            if(e.getProjectid()==projectid){
                aemp.add(e);
            }
        }

        if(aemp.isEmpty()){
            APIResponse apir=new APIResponse();
            apir.setMessage("No employee found with given project id");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apir);
        }
        else{
            EmployeeListResponse er=new EmployeeListResponse();
            er.setAle(aemp);
            er.setMessage("Employees with given project id found");
            return ResponseEntity.status(HttpStatus.OK).body(er);
        }
    }

    public ResponseEntity<EmployeeListResponse> getAllEmployeeData(){
        List<Employee> le=repository.findAll();
        ArrayList<Employee> ale=new ArrayList<>(le);

        EmployeeListResponse er=new EmployeeListResponse();
        er.setAle(ale);
        er.setMessage("All employee details found");
        return ResponseEntity.status(HttpStatus.OK).body(er);
    }

    public ResponseEntity<EmployeeListResponse> getSalaryAboveMinSalary(Integer minSalary){
        List<Employee> le=repository.findAll();
        ArrayList<Employee> ale=new ArrayList<>();

        le.stream().filter(emp -> emp.getAnnualincome()>=minSalary).forEach(emp -> ale.add(emp));

        EmployeeListResponse er=new EmployeeListResponse();
        er.setAle(ale);
        er.setMessage("These are the employees with salary >= "+minSalary);
        return ResponseEntity.status(HttpStatus.OK).body(er);
    }

    public ResponseEntity<EmployeeListResponse> getEmployeesBetweenMinMaxAge(Integer minage, Integer maxage){
        List<Employee> le=repository.findAll();
        ArrayList<Employee> ale=new ArrayList<>();

        le.stream().filter(emp -> emp.getAge()>=minage && emp.getAge()<=maxage).forEach(emp -> ale.add(emp));

        EmployeeListResponse er=new EmployeeListResponse();
        er.setAle(ale);
        er.setMessage("These are the employees with age between "+minage+" and "+maxage);
        return ResponseEntity.status(HttpStatus.OK).body(er);
    }

    public ResponseEntity<EmployeeListResponse> getSalarySort(){
        List<Employee> le=repository.findAll();
        ArrayList<Employee> ale=new ArrayList<>();

        le.stream().sorted(Comparator.comparingDouble(emp -> emp.getAnnualincome())).forEach(emp-> ale.add(emp));

        EmployeeListResponse er=new EmployeeListResponse();
        er.setAle(ale);
        er.setMessage("Employees list with salary in sorted order");
        return ResponseEntity.status(HttpStatus.OK).body(er);
    }

    public ResponseEntity<EmployeeListResponse> getTopSalaryEmployees(Integer count){
        List<Employee> le=repository.findAll();
        ArrayList<Employee> ale=new ArrayList<>();

        le.stream().sorted(Comparator.comparingDouble(Employee::getAnnualincome).reversed()).limit(count).forEach(emp-> ale.add(emp));

        EmployeeListResponse er=new EmployeeListResponse();
        er.setAle(ale);
        er.setMessage("Employees list with top "+count+" salary");
        return ResponseEntity.status(HttpStatus.OK).body(er);
    }

    public ResponseEntity<APIResponse> getYoungestEmployee(){
        List<Employee> le=repository.findAll();

        Optional<Employee> emp=le.stream().min(Comparator.comparing(Employee::getAge));
        if(emp.isPresent()){
            Employee e=emp.get();
            EmployeeResponse er=new EmployeeResponse();
            er.setEmployee(e);
            er.setMessage("This is the youngest employee");
            return ResponseEntity.status(HttpStatus.OK).body(er);
        }
        else{
            APIResponse apir=new APIResponse();
            apir.setMessage("There is no youngest employee");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apir);
        }

    }

    public ResponseEntity<APIResponse> getOldestEmployee(){
        List<Employee> le=repository.findAll();

        Optional<Employee> emp=le.stream().max(Comparator.comparing(Employee::getAge));
        if(emp.isPresent()){
            Employee e=emp.get();
            EmployeeResponse er=new EmployeeResponse();
            er.setEmployee(e);
            er.setMessage("This is the oldest employee");
            return ResponseEntity.status(HttpStatus.OK).body(er);
        }
        else{
            APIResponse apir=new APIResponse();
            apir.setMessage("There is no oldest employee");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apir);
        }
    }

    public ResponseEntity<APIResponse> getHighEarner(Integer salary){
        List<Employee> le=repository.findAll();

        boolean result=le.stream().anyMatch(emp->emp.getAnnualincome().equals(salary));
        APIResponse apir=new APIResponse();
        if(result==true){
            apir.setMessage("Employee with salary "+salary+" exist in the list");
            return ResponseEntity.status(HttpStatus.OK).body(apir);
        }
        else{
            apir.setMessage("Employee with salary "+salary+" does not exist in the list");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apir);
        }
    }

    public ResponseEntity<APIResponse> getAdults(){
        List<Employee> le=repository.findAll();

        boolean result=le.stream().allMatch(emp -> emp.getAge()>=18);
        APIResponse apir=new APIResponse();
        if(result){
            apir.setMessage("All employees are adults");
            return ResponseEntity.status(HttpStatus.OK).body(apir);
        }
        else{
            apir.setMessage("All employees are not adults");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apir);
        }
    }

    public ResponseEntity<StListResponse> getEmployeeNames(){
        List<Employee> le=repository.findAll();
        List<String> l=le.stream().map(emp->emp.getName().toUpperCase()).toList();

        StListResponse str=new StListResponse();
        str.setItems(l);
        str.setMessage("Employee names list");
        return ResponseEntity.status(HttpStatus.OK).body(str);
    }

    public ResponseEntity<IntListResponse> getEmployeesDistinctAges(){
        List<Employee> le=repository.findAll();
        List<Integer> li=le.stream().map(Employee::getAge).distinct().toList();

        IntListResponse ilr=new IntListResponse();
        ilr.setLint(li);
        ilr.setMessage("List of distinct age of employees");
        return ResponseEntity.status(HttpStatus.OK).body(ilr);
    }

    public ResponseEntity<APIResponse> createEmployee (Employee employee){

        if(employee.getEmpid()==null || employee.getName()==null || employee.getAge()==null || employee.getGender()==null || employee.getAddress()==null || employee.getTelephoneno()==null || employee.getAnnualincome()==null || employee.getDeptid()==null || employee.getProjectid()==null){
            APIResponse apir = new APIResponse();
            apir.setMessage("Missing employee details. Employee details could not be inserted.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apir);
        }
        else{
            repository.save(employee);
            EmployeeResponse er = new EmployeeResponse();
            er.setEmployee(employee);
            er.setMessage("Employee created");
            return ResponseEntity.status(HttpStatus.CREATED).body(er);
        }
    }

    public ResponseEntity<APIResponse> updateEmployee (Employee employee){
        Optional<Employee> e1 = repository.findById(employee.getEmpid());

        if (e1.isPresent()) {
            Employee e2 = e1.get();
            e2.setName(employee.getName());
            e2.setAge(employee.getAge());
            e2.setGender(employee.getGender());
            e2.setAddress(employee.getAddress());
            e2.setTelephoneno(employee.getTelephoneno());
            e2.setAnnualincome(employee.getAnnualincome());
            e2.setDeptid(employee.getDeptid());
            e2.setProjectid(employee.getProjectid());

            repository.save(e2);
            EmployeeResponse er=new EmployeeResponse();
            er.setEmployee(e2);
            er.setMessage("Employee updated");
            return ResponseEntity.status(HttpStatus.OK).body(er);
        }
        else {
            APIResponse apir=new APIResponse();
            apir.setMessage("Invalid id");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apir);
        }
    }

    public ResponseEntity<APIResponse> deleteEmployee (Employee employee){
        Optional<Employee> e1 = repository.findById(employee.getEmpid());
        if (e1.isPresent()){
            Employee e = e1.get();
            repository.delete(e);
            //return "Employee with id "+employee.getId()+" deleted";
            APIResponse ar = new APIResponse("Employee with id " + employee.getEmpid() + " deleted");
            return ResponseEntity.status(HttpStatus.OK).body(ar);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse("Invalid id"));
        }
    }

}
