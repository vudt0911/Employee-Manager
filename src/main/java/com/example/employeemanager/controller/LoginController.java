package com.example.employeemanager.controller;

import com.example.employeemanager.dao.EmployeeDao;
import com.example.employeemanager.entity.Employee;
import com.example.employeemanager.login.FormLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    EmployeeDao employeeDao;

    @GetMapping(value = {"", "/"})
    public String loginAction(){
        return "login";
    }

    public String loginEmployee(@ModelAttribute("formlogin")FormLogin formLogin, Model model){
        List<Employee> employeeList = employeeDao.getListEmployee();
        for (Employee e: employeeList) {
            if (e.getEmail().equals(formLogin.getEmail()) && formLogin.getPassword().equals("123")){
                return "time-keep";
            }else if (e.getEmail().equals("admin") && formLogin.getPassword().equals("123")){
                return "employees";
            }
        }
        return "error";
    }
}
