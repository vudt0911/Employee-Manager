package com.example.employeemanager.controller;

import com.example.employeemanager.dao.DepartmentDao;
import com.example.employeemanager.dao.EmployeeDao;
import com.example.employeemanager.dao.PositionDao;
import com.example.employeemanager.entity.Department;
import com.example.employeemanager.entity.Employee;
import com.example.employeemanager.entity.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    PositionDao positionDao;

    //get all
    @GetMapping("/all")
    public String allEmployee(Model model){
        List<Department> departmentList = departmentDao.getListDepartment();
        model.addAttribute("listDepartment", departmentList);
        model.addAttribute("employees", employeeDao.getListEmployee());
        return "employees";
    }

    // edit
    @GetMapping("/edit/{id}")
    public String formEditEmployee(Model model, @PathVariable("id") int id){
        Employee employee = employeeDao.getEmployeeById(id);
        List<Department> departmentList = departmentDao.getListDepartment();
        List<Position> positionList = positionDao.getListPosition();
        model.addAttribute("listDepartment", departmentList);
        model.addAttribute("listPosition", positionList);
        model.addAttribute("employee", employee);
        return "form";
    }

    @PostMapping("/save")
    public String editEmployee(Employee employee, @RequestParam("depart_id") Integer depart_id, @RequestParam("pos_id") Integer pos_id,Model model){
        System.out.println(employee);
        Position position = positionDao.getPositionById(pos_id);
        Department department = departmentDao.getDepartmentById(depart_id);
        employee.setEmployee_department(department);
        employee.setEmployee_position(position);
        if (employee.getId() != null){
            employeeDao.updateEmployee(employee);
        }else{
            employeeDao.addEmployee(employee);
        }
        model.addAttribute("employees", employeeDao.getListEmployee());
        return "redirect:/employee/all";
    }

    // add new
    @GetMapping("/add")
    public String formAddEmploy(Model model){
        Employee employee = new Employee();
        List<Department> departmentList = departmentDao.getListDepartment();
        List<Position> positionList = positionDao.getListPosition();
        model.addAttribute("listDepartment", departmentList);
        model.addAttribute("listPosition", positionList);
        model.addAttribute("employee", employee);
        return "form";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id, Model model){
        employeeDao.deleteStudent(id);
        model.addAttribute("employees", employeeDao.getListEmployee());
        return "redirect:/employee/all";
    }

    //search Employees By Name/Email/Address
    @PostMapping("/search")
    public String searchEmployees(@RequestParam("keyword") String keyword, Model model){
        List<Employee> employeeList = employeeDao.searchEmployee(keyword);
        model.addAttribute("employees", employeeList);
        return "employees";
    }

    @GetMapping("/department/{id}")
    public String filterEmpByDepartment(@PathVariable("id") Integer id_department, Model model){
        List<Employee> listEmployeeFilter = departmentDao.filterEmployeeByDepartment(id_department);
        List<Department> departmentList = departmentDao.getListDepartment();
        model.addAttribute("listDepartment", departmentList);
        model.addAttribute("employees", listEmployeeFilter);
        return "employees";
    }
    @ModelAttribute
    public List<Department> listDepartment(){
        return departmentDao.getListDepartment();
    }

}
