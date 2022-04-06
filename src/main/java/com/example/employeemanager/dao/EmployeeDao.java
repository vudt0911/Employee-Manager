package com.example.employeemanager.dao;

import com.example.employeemanager.entity.Department;
import com.example.employeemanager.entity.Employee;
import com.example.employeemanager.entity.Position;
import com.example.employeemanager.util.ConnectionDB;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeDao {

    public List<Employee> getListEmployee() {
        Transaction transaction = null;
        Session session = ConnectionDB.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        List<Employee> employeeList = session.createQuery("from employee ").list();
        transaction.commit();
        session.close();
        return employeeList;
    }

    public Employee getEmployeeById(int id) {
        Employee emp = null;
        Session session = ConnectionDB.getSessionFactory().openSession();
        try {
            emp=
             session.get(Employee.class, id); // search employee
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return emp;
    }

    public void addEmployee(Employee employee) {
        Transaction transaction = null;
        Session session = ConnectionDB.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(employee); // add employee vao database
        transaction.commit();
        session.close();
    }

    public void updateEmployee(Employee employee) {
        Transaction transaction = null;
        Session session = ConnectionDB.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.saveOrUpdate(employee); // update employee vao database
        transaction.commit();
        session.close();
    }

    public void deleteStudent(int id) {
        Transaction transaction = null;
        Session session = ConnectionDB.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.remove(session.get(Employee.class, id)); //removeemployee vao database
        transaction.commit();
        session.close();
    }

    public List<Employee> searchEmployee(String keywords){
        String keywordToLowerCase = keywords.toLowerCase();
        Employee employee = new Employee();
        Transaction transaction = null;
        Session session = ConnectionDB.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        EmployeeDao employeeDao = new EmployeeDao();
        List<Employee> list = employeeDao.getListEmployee().stream().filter(empl ->
                        empl.getName().toLowerCase().contains(keywordToLowerCase)||
                        empl.getEmail().toLowerCase().contains(keywordToLowerCase) ||
                        empl.getAddress().toLowerCase().contains(keywordToLowerCase))
                .collect(Collectors.toList());

        transaction.commit();
        session.close();
        return list;
    }

    public Department sale(){
        Session session = ConnectionDB.getSessionFactory().openSession();
        return session.find(Department.class, 1);
    }
    public Department technicians(){
        Session session = ConnectionDB.getSessionFactory().openSession();
        return session.find(Department.class, 2);
    }
    public Position manager(){
        Session session = ConnectionDB.getSessionFactory().openSession();
        return session.find(Position.class, 1);
    }
    public Position staff(){
        Session session = ConnectionDB.getSessionFactory().openSession();
        return session.find(Position.class, 2);
    }

    public static void main(String[] args) {
        EmployeeDao employeeDao = new EmployeeDao();
        Session session = ConnectionDB.getSessionFactory().openSession();

        // get employ By Id
//        Employee employee = employeeDao.getEmployeeById(2);
//        System.out.println(employee);

        // add employ
//        Department department = session.find(Department.class, 1);
//        Position position = session.find(Position.class, 2);
//        Employee employee = new Employee(5,"quang", 22, "quang@gmail.com", "0987656787","HN", department, position);
//        employeeDao.addEmployee(employee);

        //update employee
//        Department department = session.find(Department.class, 2);
//        Position position = session.find(Position.class, 2);
//        Employee employee = new Employee(1,"vu-update", 22, "vu@gmail.com", "0987656787","HN", department, position);
//        employeeDao.updateEmployee(employee);

        // delete employee
//        employeeDao.deleteStudent(3);

        //search
//        List<Employee> listSearch = employeeDao.searchEmployee("Vu");
//        System.out.println(listSearch );


//        List<Employee> list = employeeDao.getListEmployee();
//        System.out.println(list );
    }
}
