package com.oms.officemanagementsystem;

import org.springframework.data.jpa.repository.JpaRepository;

//This Jpa repository has no relation with git repository
//This interface will help in querying database

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}

//JpaRepository class contains FindAll method
//JPA's built-in findAll() to fetch every row from the employee table.