package com.oms.officemanagementsystem;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service){
        this.service=service;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<APIResponse> getProjectDetails(@PathVariable Integer id){
        return service.getProjectDetails(id);
    }

    @GetMapping("/startdate/{startdate}")
    public ResponseEntity<APIResponse> getProjectDetailsStartdate(@PathVariable LocalDate startdate){
        return service.getProjectDetailsStartdate(startdate);
    }

    @GetMapping("/enddate/{enddate}")
    public ResponseEntity<APIResponse> getProjectDetailsEnddate(@PathVariable LocalDate enddate){
        return service.getProjectDetailsEnddate(enddate);
    }

    @GetMapping("/deptid/{deptid}")
    public ResponseEntity<APIResponse> getProjectDetailsDeptid(@PathVariable Integer deptid){
        return service.getProjectDetailsDeptid(deptid);
    }

    @GetMapping("/all")
    public ResponseEntity<ProjectListResponse> getAllProjectDetails(){
        return service.getAllProjectDetails();
    }

    @GetMapping("/group-by-department")
    public ResponseEntity<ProjectMapResponse> getProjectGroupByDept(){
        return service.getProjectGroupByDept();
    }

    @PostMapping
    public ResponseEntity<APIResponse> createProject(@RequestBody Project project){
        return service.createProject(project);
    }

    @PutMapping
    public ResponseEntity<APIResponse> updateProjectEndDate(@RequestBody Project project){
        return service.updateProjectEndDate(project);
    }

    @DeleteMapping
    public ResponseEntity<APIResponse> deleteProject(@RequestBody Project project){
        return service.deleteProject(project);
    }

}
