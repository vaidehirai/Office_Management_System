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
@Table(name="Leave")

@Entity
public class Leave {

    @Id
    private Integer leaveid;
    private LocalDate from_date;
    private LocalDate to_date;
    private String status;
    private Integer empid;
}
