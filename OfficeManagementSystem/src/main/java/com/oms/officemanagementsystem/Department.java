package com.oms.officemanagementsystem;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "department")
@Entity

public class Department {

    @Id
    Integer deptid;
    String name;
    String code;
}
