package com.example.employeemanager.dao;

import com.example.employeemanager.entity.Department;
import com.example.employeemanager.entity.Employee;
import com.example.employeemanager.entity.Position;
import com.example.employeemanager.util.ConnectionDB;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PositionDao {
    public List<Position> getListPosition() {
        Transaction transaction = null;
        Session session = ConnectionDB.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        List<Position> positionList = session.createQuery("from position ").list();
        transaction.commit();
        session.close();
        return positionList;
    }

    public Position getPositionById(int id) {
        Position position = null;
        Session session = ConnectionDB.getSessionFactory().openSession();
        try {
            position=
                    session.get(Position.class, id); // search employee
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return position;
    }
}
