package com.example.employeemanager.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private Long salary;

//    @OneToMany(mappedBy = "position_department", cascade = CascadeType.PERSIST)
//    private List<Position> department_position;

    @OneToMany(mappedBy = "employee_department", cascade = CascadeType.PERSIST)
    private List<Employee> department_employee;
}
