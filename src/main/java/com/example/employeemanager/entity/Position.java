package com.example.employeemanager.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name="factor_Salary")
    private Integer factorSalary;

    @OneToMany(mappedBy = "employee_position",cascade = CascadeType.PERSIST)
    private List<Employee> position_employee;
}
