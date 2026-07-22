package com.oms.officemanagementsystem;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name="Project")

@Entity
public class Project {

    @Id
    Integer projectid;
    LocalDate start_date;
    LocalDate end_date;
    Integer deptid;

}
