package com.example.employeemanager.dao;

import com.example.employeemanager.entity.Department;
import com.example.employeemanager.entity.Employee;
import com.example.employeemanager.util.ConnectionDB;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DepartmentDao {
    @Autowired
    EmployeeDao employeeDao;
    public List<Department> getListDepartment() {
        Transaction transaction = null;
        Session session = ConnectionDB.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        List<Department> departmentList = session.createQuery("from department ").list();
        transaction.commit();
        session.close();
        return departmentList;
    }

    public Department getDepartmentById(int id) {
        Department department = null;
        Session session = ConnectionDB.getSessionFactory().openSession();
        try {
            department=
                    session.get(Department.class, id); // search employee
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return department;
    }

    public List<Employee> filterEmployeeByDepartment(Integer id_department){
        Transaction transaction = null;
        Session session = ConnectionDB.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        List<Employee> employeeList = employeeDao.getListEmployee();
        List<Employee> listEmployeeFilter =  employeeList.stream().filter(employee -> employee.getEmployee_department().getId()==id_department).collect(Collectors.toList());
        transaction.commit();
        session.close();
        return listEmployeeFilter;
    }
}
