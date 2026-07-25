package com.oms.officemanagementsystem;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
            er.getAle().add(e);
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
            EmployeeResponse er=new EmployeeResponse();
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
            EmployeeResponse er=new EmployeeResponse();
            er.setAle(aemp);
            er.setMessage("Employees with given project id found");
            return ResponseEntity.status(HttpStatus.OK).body(er);
        }
    }

    public ResponseEntity<EmployeeResponse> getAllEmployeeData(){
        List<Employee> le=repository.findAll();
        ArrayList<Employee> ale=new ArrayList<>(le);

        EmployeeResponse er=new EmployeeResponse(ale);
        er.setMessage("All employee details found");
        return ResponseEntity.status(HttpStatus.OK).body(er);
    }

    public ResponseEntity<EmployeeResponse> getMinSalary(Integer minSalary){
        List<Employee> le=repository.findAll();
        ArrayList<Employee> ale=new ArrayList<>();

        le.stream().filter(emp -> emp.getAnnualincome()>=minSalary).forEach(emp -> ale.add(emp));

        EmployeeResponse er=new EmployeeResponse();
        er.setAle(ale);
        er.setMessage("These are the employees with salary >= "+minSalary);
        return ResponseEntity.status(HttpStatus.OK).body(er);
    }

    public ResponseEntity<EmployeeResponse> getMinMaxAge(Integer minage, Integer maxage){
        List<Employee> le=repository.findAll();
        ArrayList<Employee> ale=new ArrayList<>();

        le.stream().filter(emp -> emp.getAge()>=minage && emp.getAge()<=maxage).forEach(emp -> ale.add(emp));

        EmployeeResponse er=new EmployeeResponse();
        er.setAle(ale);
        er.setMessage("These are the employees with age between "+minage+" and "+maxage);
        return ResponseEntity.status(HttpStatus.OK).body(er);
    }

    public ResponseEntity<EmployeeResponse> getSalarySort(){
        List<Employee> le=repository.findAll();
        ArrayList<Employee> ale=new ArrayList<>();

        le.stream().sorted(Comparator.comparingDouble(emp -> emp.getAnnualincome())).forEach(emp-> ale.add(emp));

        EmployeeResponse er=new EmployeeResponse();
        er.setAle(ale);
        er.setMessage("Employees list with salary in sorted order");
        return ResponseEntity.status(HttpStatus.OK).body(er);
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
            er.getAle().add(employee);
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
            er.getAle().add(e2);
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
