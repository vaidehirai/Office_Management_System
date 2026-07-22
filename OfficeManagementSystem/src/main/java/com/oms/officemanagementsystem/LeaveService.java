package com.oms.officemanagementsystem;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;

    public LeaveService(LeaveRepository leaveRepository){
        this.leaveRepository=leaveRepository;
    }

    public ResponseEntity<APIResponse> getLeaveDetailsLeaveid(int id){
        Optional<Leave> leave =leaveRepository.findById(id);

        if(leave.isPresent()){
            Leave l=leave.get();

            LeaveResponse lr=new LeaveResponse();
            lr.getAl().add(l);
            lr.setMessage("Leave details found");
            return ResponseEntity.status(HttpStatus.OK).body(lr);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse("Invalid Id"));
        }
    }

    public ResponseEntity<APIResponse> getLeaveDetailsFromDate(LocalDate fromdate){

        ArrayList<Leave> arrll=new ArrayList<>();
        for(int i=1;i< leaveRepository.count();i++){
            Optional<Leave> l1=leaveRepository.findById(i);
            Leave l2=l1.get();

            if(l2.getFrom_date().equals(fromdate)){
                arrll.add(l2);
            }
        }

        if(arrll.isEmpty()){
            APIResponse apir=new APIResponse();
            apir.setMessage("No details of leaves found for given from_date");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apir);
        }
        else{
            LeaveResponse lr=new LeaveResponse();
            lr.setAl(arrll);
            lr.setMessage("Details of leaves for the given from_date found");
            return ResponseEntity.status(HttpStatus.OK).body(lr);
        }
    }

    public ResponseEntity<APIResponse> getLeaveDetailsToDate(LocalDate todate){
        ArrayList<Leave> arrll=new ArrayList<>();
        for(int i=1;i< leaveRepository.count();i++){
            Optional<Leave> l1=leaveRepository.findById(i);
            Leave l2=l1.get();

            if(l2.getTo_date().equals(todate)){
                arrll.add(l2);
            }
        }

        if(arrll.isEmpty()){
            APIResponse apir=new APIResponse();
            apir.setMessage("No details of leaves found for given to_date");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apir);
        }
        else{
            LeaveResponse lr=new LeaveResponse();
            lr.setAl(arrll);
            lr.setMessage("Details of leaves for the given to_date found");
            return ResponseEntity.status(HttpStatus.OK).body(lr);
        }
    }

    public ResponseEntity<APIResponse> getLeaveDetailsStatus(String status){
        ArrayList<Leave> arrll=new ArrayList<>();
        for(int i=1;i< leaveRepository.count();i++){
            Optional<Leave> l1=leaveRepository.findById(i);
            Leave l2=l1.get();

            if(l2.getStatus().equals(status)){
                arrll.add(l2);
            }
        }

        if(arrll.isEmpty()){
            APIResponse apir=new APIResponse();
            apir.setMessage("No details of leaves found for given status");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apir);
        }
        else{
            LeaveResponse lr=new LeaveResponse();
            lr.setAl(arrll);
            lr.setMessage("Details of leaves for the given status found");
            return ResponseEntity.status(HttpStatus.OK).body(lr);
        }
    }

    public ResponseEntity<APIResponse> getLeaveDetailsEmpid(Integer empid){
        ArrayList<Leave> arrll=new ArrayList<>();
        for(int i=1;i< leaveRepository.count();i++){
            Optional<Leave> l1=leaveRepository.findById(i);
            Leave l2=l1.get();

            if(l2.getEmpid().equals(empid)){
                arrll.add(l2);
            }
        }

        if(arrll.isEmpty()){
            APIResponse apir=new APIResponse();
            apir.setMessage("No details of leaves found for given empid");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apir);
        }
        else{
            LeaveResponse lr=new LeaveResponse();
            lr.setAl(arrll);
            lr.setMessage("Details of leaves for the given empid found");
            return ResponseEntity.status(HttpStatus.OK).body(lr);
        }
    }

    public ResponseEntity<LeaveResponse> getAllLeaveDetails(){
        List<Leave> ll=leaveRepository.findAll();
        ArrayList<Leave> all=new ArrayList<>(ll);

        LeaveResponse lr=new LeaveResponse(all);
        lr.setMessage("All leave details found");
        return ResponseEntity.status(HttpStatus.OK).body(lr);
    }

    public ResponseEntity<APIResponse> insertLeaveDetails(Leave leave){

        if(leave.getLeaveid()==null || leave.getFrom_date()==null || leave.getTo_date()==null || leave.getStatus()==null || leave.getEmpid()==null){
            APIResponse apir=new APIResponse();
            apir.setMessage("Missing Leave details. Provided leave details could not be inserted");
            return ResponseEntity.status(HttpStatus.OK).body(apir);
        }
        else{
            leaveRepository.save(leave);
            LeaveResponse lr=new LeaveResponse();
            lr.getAl().add(leave);
            lr.setMessage("New leave entry created");
            return ResponseEntity.status(HttpStatus.CREATED).body(lr);
        }

    }

    public ResponseEntity<APIResponse> updateLeaveDetails(Leave leave){
        Optional<Leave> l1=leaveRepository.findById(leave.getLeaveid());

        APIResponse apir=new APIResponse();
        if(l1.isPresent()){
            Leave l2=l1.get();

            l2.setLeaveid(leave.getLeaveid());
            l2.setFrom_date(leave.getFrom_date());
            l2.setTo_date(leave.getTo_date());
            l2.setStatus(leave.getStatus());
            l2.setEmpid(leave.getEmpid());

            leaveRepository.save(l2);
            apir.setMessage("Status of id "+leave.getLeaveid()+" updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(apir);
        }
        else {
            apir.setMessage("Invalid id");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apir);
        }
    }

    public ResponseEntity<APIResponse> deleteLeaveDetails(Leave leave){
        Optional<Leave> l1=leaveRepository.findById(leave.getLeaveid());
        if(l1.isPresent()){
            Leave l2=l1.get();
            leaveRepository.delete(l2);

            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Leave details of given id deleted"));
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse("Invalid Id"));
        }
    }
}
