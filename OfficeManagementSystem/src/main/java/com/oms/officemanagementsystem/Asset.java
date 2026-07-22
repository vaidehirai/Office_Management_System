package com.oms.officemanagementsystem;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="asset")

@Entity
public class Asset {

    @Id
    Integer assetid;
    String assetname;
    Integer assetvalue;
    Integer empid;
}
