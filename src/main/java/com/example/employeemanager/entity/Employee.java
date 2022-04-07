package com.example.employeemanager.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Integer age;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;

    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "department_id")
    private Department employee_department;

    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "position_id")
    private Position employee_position;


}
