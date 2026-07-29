package com.oms.officemanagementsystem;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository){
        this.repository=repository;
    }

    public ResponseEntity<APIResponse> getProjectDetails(Integer id){
        Optional<Project> project=repository.findById(id);

        if(project.isPresent()){
            Project p=project.get();

            ProjectResponse pr=new ProjectResponse();
            pr.setProject(p);
            pr.setMessage("Project details found");
            return ResponseEntity.status(HttpStatus.OK).body(pr);
        }
        else{
            APIResponse apir=new APIResponse();
            apir.setMessage("Invalid id");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apir);
        }
    }

    public ResponseEntity<APIResponse> getProjectDetailsStartdate(LocalDate startdate){
        ArrayList<Project> alp=new ArrayList<>();
        for(int i=101;i<= 100+repository.count();i++){
            Optional<Project> p1=repository.findById(i);
            Project p2=p1.get();
            if(p2.getStart_date().equals(startdate)){
                alp.add(p2);
            }
        }

        if(alp.isEmpty()){
            APIResponse apir=new APIResponse();
            apir.setMessage("No projects with given start date found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apir);
        }
        else{
            ProjectListResponse pr=new ProjectListResponse();
            pr.setAlp(alp);
            pr.setMessage("Projects with given start date found");
            return ResponseEntity.status(HttpStatus.OK).body(pr);
        }
    }

    public ResponseEntity<APIResponse> getProjectDetailsEnddate(LocalDate enddate){
        ArrayList<Project> alp=new ArrayList<>();
        for(int i=101;i<= 100+repository.count();i++){
            Optional<Project> p1=repository.findById(i);
            Project p2=p1.get();
            if(p2.getEnd_date().equals(enddate)){
                alp.add(p2);
            }
        }

        if(alp.isEmpty()){
            APIResponse apir=new APIResponse();
            apir.setMessage("No projects with given end date found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apir);
        }
        else{
            ProjectListResponse pr=new ProjectListResponse();
            pr.setAlp(alp);
            pr.setMessage("Projects with given end date found");
            return ResponseEntity.status(HttpStatus.OK).body(pr);
        }
    }

    public ResponseEntity<APIResponse> getProjectDetailsDeptid(Integer deptid){
        ArrayList<Project> alp=new ArrayList<>();
        for(int i=101;i<= 100+repository.count();i++){
            Optional<Project> p1=repository.findById(i);
            Project p2=p1.get();
            if(p2.getDeptid().equals(deptid)){
                alp.add(p2);
            }
        }

        if(alp.isEmpty()){
            APIResponse apir=new APIResponse();
            apir.setMessage("No projects with given deptid found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apir);
        }
        else{
            ProjectListResponse pr=new ProjectListResponse();
            pr.setAlp(alp);
            pr.setMessage("Projects with given deptid found");
            return ResponseEntity.status(HttpStatus.OK).body(pr);
        }
    }

    public ResponseEntity<ProjectListResponse> getAllProjectDetails(){
        List<Project> lp=repository.findAll();
        ArrayList<Project> alp=new ArrayList<>(lp);

        ProjectListResponse pr=new ProjectListResponse();
        pr.setAlp(alp);
        pr.setMessage("All project details found");
        return  ResponseEntity.status(HttpStatus.OK).body(pr);
    }

    public ResponseEntity<APIResponse> createProject(Project project){

        if(project.getProjectid()==null || project.getStart_date()==null || project.getEnd_date()==null || project.getDeptid()==null){
            APIResponse apir=new APIResponse();
            apir.setMessage("Missing project details. Given project details could not be inserted.");
            return ResponseEntity.status(HttpStatus.OK).body(apir);
        }
        else{
            repository.save(project);
            ProjectResponse pr2=new ProjectResponse();
            pr2.setProject(project);
            pr2.setMessage("New project added");
            return ResponseEntity.status(HttpStatus.CREATED).body(pr2);
        }
    }

    public ResponseEntity<APIResponse> updateProjectEndDate(Project project){
        Optional<Project> p1=repository.findById(project.getProjectid());


        if(p1.isPresent()){
            Project p2=p1.get();
            p2.setStart_date(project.getStart_date());
            p2.setEnd_date(project.getEnd_date());
            p2.setDeptid(project.getDeptid());

            repository.save(p2);

            ProjectResponse pr=new ProjectResponse();
            pr.setProject(p2);
            pr.setMessage("Project details updated");
            return ResponseEntity.status(HttpStatus.OK).body(pr);
        }
        else{
            APIResponse apir=new APIResponse();
            apir.setMessage("Invalid id");
            return ResponseEntity.status(HttpStatus.OK).body(apir);
        }
    }

    public ResponseEntity<APIResponse> deleteProject(Project project){
        Optional<Project> p=repository.findById(project.getProjectid());

        APIResponse apir=new APIResponse();
        if(p.isPresent()){
            Project p1=p.get();
            repository.delete(p1);
            apir.setMessage("Project with the given id deleted");
            return ResponseEntity.status(HttpStatus.OK).body(apir);
        }
        else{
            apir.setMessage("Invalid project id");
            return ResponseEntity.status(HttpStatus.OK).body(apir);
        }
    }

}
