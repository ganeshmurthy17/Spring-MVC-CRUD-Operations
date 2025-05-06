package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController
{
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService theEmployeeService)
    {
        employeeService = theEmployeeService;
    }

    @GetMapping("/list")
    public String getList(Model theModel)
    {
        List<Employee> list = employeeService.findAll();

        theModel.addAttribute("employees",list);

        return "employees/employee-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel)
    {
        Employee e1 = new Employee();

        theModel.addAttribute("employee",e1);

        return "employees/employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@ModelAttribute("employeeId")int theId,Model theModel)
    {
        Employee theEmp = employeeService.findById(theId);

        theModel.addAttribute("employee",theEmp);

        return "/employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmp(@ModelAttribute("employee") Employee theEmployee)
    {
        employeeService.save(theEmployee);

        return "redirect:/employee/list";
    }

    @GetMapping("/delete")
    public String delete(@ModelAttribute("employeeId") int theId)
    {
        employeeService.deleteById(theId);

        return "redirect:/employee/list";
    }
}
