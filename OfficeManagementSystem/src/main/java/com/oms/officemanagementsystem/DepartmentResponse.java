package com.oms.officemanagementsystem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DepartmentResponse extends APIResponse{

    //private Department department;
    ArrayList<Department> ald=new ArrayList<>();
}
