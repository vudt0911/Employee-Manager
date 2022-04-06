package com.example.employeemanager.util;

import com.example.employeemanager.entity.Department;
import com.example.employeemanager.entity.Employee;
import com.example.employeemanager.entity.Position;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class ConnectionDB {

    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();

            // Hibernate settings equivalent to hibernate.cfg.xml's properties
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
            settings.put(Environment.URL, "jdbc:mysql://localhost:3306/employee_manager?useSSL=false");
            settings.put(Environment.USER, "root");
            settings.put(Environment.PASS, "12345678");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

            settings.put(Environment.SHOW_SQL, "true");

            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

            settings.put(Environment.HBM2DDL_AUTO, "update");

            configuration.setProperties(settings);

            configuration.addAnnotatedClass(Department.class);
            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(Position.class);
//            configuration.addAnnotatedClass(Timekeep.class);

            sessionFactory = configuration.buildSessionFactory();
            System.out.println("ket noi thanh cong");
            return sessionFactory;
        }
        return sessionFactory;
    }

    public static void main(String[] args) {
        ConnectionDB.getSessionFactory().openSession();
    }
}
