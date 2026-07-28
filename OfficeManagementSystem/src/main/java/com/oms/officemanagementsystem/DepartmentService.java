package com.oms.officemanagementsystem;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository){
        this.repository=repository;
    }

    public ResponseEntity<APIResponse> getDepartment(Integer id){
        Optional<Department> dept=repository.findById(id);

        if(dept.isPresent()){
            Department d=dept.get();
            DepartmentResponse dr=new DepartmentResponse();
            dr.setDepartment(d);
            dr.setMessage("Department details found");
            return ResponseEntity.status(HttpStatus.OK).body(dr);
        }
        else{
            APIResponse apir=new APIResponse();
            apir.setMessage("Invalid Department id");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apir);
        }
    }

    public ResponseEntity<APIResponse> getDepartmentDeptname(String name){

        ArrayList<Department> aldept=new ArrayList<>();
        for(int i=1;i<=repository.count();i++){
            Optional<Department> dep=repository.findById(i);
            Department dep2=dep.get();

            if (dep2.getName().equals(name)){
                aldept.add(dep2);
            }
        }

        if(aldept.isEmpty()){
            APIResponse apir=new APIResponse();
            apir.setMessage("No department found with given department name");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apir);
        }
        else{
            DepartmentListResponse dlr=new DepartmentListResponse();
            dlr.setAld(aldept);
            dlr.setMessage("Departments with given department name found");
            return ResponseEntity.status(HttpStatus.OK).body(dlr);
        }
    }

    public ResponseEntity<APIResponse> getDepartmentDeptcode(String code){
        ArrayList<Department> aldept=new ArrayList<>();
        for(int i=1;i<=repository.count();i++){
            Optional<Department> dep=repository.findById(i);
            Department dep2=dep.get();

            if (dep2.getCode().equals(code)){
                aldept.add(dep2);
            }
        }

        if(aldept.isEmpty()){
            APIResponse apir=new APIResponse();
            apir.setMessage("No department found with given department code");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apir);
        }
        else{
            DepartmentListResponse dlr=new DepartmentListResponse();
            dlr.setAld(aldept);
            dlr.setMessage("Departments with given department code found");
            return ResponseEntity.status(HttpStatus.OK).body(dlr);
        }
    }

    public ResponseEntity<DepartmentListResponse> getAllDepartments(){
        List<Department> ld=repository.findAll();
        ArrayList<Department> ald=new ArrayList<>(ld);

        DepartmentListResponse dlr=new DepartmentListResponse(ald);
        dlr.setMessage("All department details found");
        return ResponseEntity.status(HttpStatus.OK).body(dlr);
    }

    public ResponseEntity<APIResponse> createDepartment(Department dept){

        if(dept.getDeptid()==null || dept.getName()==null || dept.getCode()==null){
            APIResponse apir=new APIResponse();
            apir.setMessage("Missing department details. Department details could not be inserted");
            return ResponseEntity.status(HttpStatus.OK).body(apir);
        }
        else{
            repository.save(dept);
            DepartmentResponse dr=new DepartmentResponse();
            dr.setDepartment(dept);
            dr.setMessage("New department created");
            return ResponseEntity.status(HttpStatus.CREATED).body(dr);
        }

    }

    public ResponseEntity<APIResponse> updateDepartment(Department dept){
        Optional<Department> d1=repository.findById(dept.getDeptid());

        if(d1.isPresent()){
            Department d2=d1.get();
            d2.setName(dept.getName());
            d2.setCode(dept.getCode());

            repository.save(d2);
            DepartmentResponse dr=new DepartmentResponse();
            dr.setDepartment(d2);
            dr.setMessage("Name of department with id "+dept.getDeptid()+" updated");
            return ResponseEntity.status(HttpStatus.OK).body(dr);
        }
        else{
            APIResponse apir=new APIResponse();
            apir.setMessage("Invalid Department id");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apir);
        }
    }

    public ResponseEntity<APIResponse> deleteDepartment(Department dept){
        Optional<Department> d =repository.findById(dept.getDeptid());
        APIResponse apir=new APIResponse();

        if(d.isPresent()){
            repository.delete(d.get());
            apir.setMessage("Department with the given id deleted");
            return ResponseEntity.status(HttpStatus.OK).body(apir);
        }
        else{
            apir.setMessage("Invalid department id");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apir);
        }
    }
}
