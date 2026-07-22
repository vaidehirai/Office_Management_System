package com.oms.officemanagementsystem;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //all getter and setters get generated during compilation phase automatically
@AllArgsConstructor //all arguments constructor get created
@NoArgsConstructor //no arguments constructor get created
@Table(name="employee") //Telling which table is represented by this POJO

@Entity //Telling POJO is a row in PostgreSQL table
public class Employee {

    @Id //telling that id is primary key in table Employees
    private Integer empid;
    private String name;
    private Integer age;
    private String gender;
    private String address;
    private String telephoneno;
    private Integer annualincome;
    private Integer deptid;
    private Integer projectid;

}
