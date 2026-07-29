package com.oms.officemanagementsystem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProjectListResponse extends APIResponse{

    private ArrayList<Project> alp;
}
