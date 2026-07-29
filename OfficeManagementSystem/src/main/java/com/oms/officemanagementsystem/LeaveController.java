package com.oms.officemanagementsystem;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/leave")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService){
        this.leaveService=leaveService;
    }

    @GetMapping("/leaveid/{leaveid}")
    public ResponseEntity<APIResponse> getLeaveDetailsLeaveid(@PathVariable Integer leaveid){
        return leaveService.getLeaveDetailsLeaveid(leaveid);
    }

    @GetMapping("/fromdateleaves/{fromdate}")
    public ResponseEntity<APIResponse> getLeaveDetailsFromDate(@PathVariable LocalDate fromdate){
        return leaveService.getLeaveDetailsFromDate(fromdate);
    }

    @GetMapping("/todateleaves/{todate}")
    public ResponseEntity<APIResponse> getLeaveDetailsToDate(@PathVariable LocalDate todate){
        return leaveService.getLeaveDetailsToDate(todate);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<APIResponse> getLeaveDetailsStatus(@PathVariable String status){
        return leaveService.getLeaveDetailsStatus(status);
    }

    @GetMapping("/empid/{empid}")
    public ResponseEntity<APIResponse> getLeaveDetailsEmpid(@PathVariable Integer empid){
        return leaveService.getLeaveDetailsEmpid(empid);
    }

    @GetMapping("/all")
    public ResponseEntity<LeaveListResponse> getAllLeaveDetails(){
        return leaveService.getAllLeaveDetails();
    }

    @PostMapping
    public ResponseEntity<APIResponse> insertLeaveDetails(@RequestBody Leave leave){
        return leaveService.insertLeaveDetails(leave);
    }

    @PutMapping
    public ResponseEntity<APIResponse> updateStatus(@RequestBody Leave leave){
        return leaveService.updateLeaveDetails(leave);
    }

    @DeleteMapping
    public ResponseEntity<APIResponse> deleteLeaveDetails(@RequestBody Leave leave){
        return leaveService.deleteLeaveDetails(leave);
    }
}
