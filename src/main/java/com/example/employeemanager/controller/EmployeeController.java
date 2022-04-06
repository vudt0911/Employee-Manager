package com.example.employeemanager.controller;

import com.example.employeemanager.dao.EmployeeDao;
import com.example.employeemanager.entity.Department;
import com.example.employeemanager.entity.Employee;
import com.example.employeemanager.entity.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;

    //get all
    @GetMapping("/all")
    public String allEmployee(Model model){
        model.addAttribute("employees", employeeDao.getListEmployee());
        return "employees";
    }

    // edit
    @GetMapping("/edit/{id}")
    public String formEditEmployee(Model model, @PathVariable("id") int id){
        Employee employee = employeeDao.getEmployeeById(id);
//        if (employee.getEmployee_department().getName().equalsIgnoreCase("sale")){
//            model.addAttribute("department", employeeDao.sale());
//        }else{
//            model.addAttribute("department", employeeDao.technicians());
//        }
//
//        if (employee.getEmployee_position().getName().equalsIgnoreCase("GD")){
//            model.addAttribute("position", employeeDao.manager());
//        }else {
//            model.addAttribute("position", employeeDao.staff());
//        }
        model.addAttribute("employee", employee);
        return "form";
    }

    @PostMapping("/edit")
    public String editEmployee(@RequestParam("employee") Employee employee, Model model){
        System.out.println(employee);
//        employeeDao.updateEmployee(employee);
        model.addAttribute("employees", employeeDao.getListEmployee());
        return "redirect:/employee/all";
    }


}
