package com.oms.officemanagementsystem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LeaveResponse extends APIResponse{

    //private Leave leave;
    ArrayList<Leave> al=new ArrayList<>();
}
