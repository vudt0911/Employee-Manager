package com.example.employeemanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "timekeep")
public class Timekeep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "startTime")
    private Integer startTime;
    @Column(name = "endTime")
    private Integer endTime;
    @Column(name = "number Workday")
    private Integer numberWorkday;

    @ManyToOne(cascade = CascadeType.PERSIST)
    Employee timekeep_employee;
}
