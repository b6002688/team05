package com.cpe.backend.Employee.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Collection;
import java.util.stream.Collectors;


import com.cpe.backend.Employee.entity.Employee;
import com.cpe.backend.Employee.entity.Phonetype;
import com.cpe.backend.Employee.entity.Province;
import com.cpe.backend.Employee.entity.Position;

import com.cpe.backend.Employee.repository.EmployeeRepository;
import com.cpe.backend.Employee.repository.PhonetypeRepository;
import com.cpe.backend.Employee.repository.ProvinceRepository;
import com.cpe.backend.Employee.repository.PositionRepository;


import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class EmployeeController {
    @Autowired
    private final EmployeeRepository employeeRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private PhonetypeRepository phonetypeRepository;

    EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/Employee")
    public Collection<Employee> Employee() {
        return employeeRepository.findAll().stream().collect(Collectors.toList());
    }
    @PostMapping("/Employee/{name}/{date}/{email}/{password}/{phonenumber}/{position_id}/{phonetype_id}/{province_id}")
    public Employee newEmployee(Employee newEmployee,
    @PathVariable String name,
    @PathVariable Date date,
    @PathVariable String address,
    @PathVariable String email,
    @PathVariable String password,
    @PathVariable String phonenumber,
    @PathVariable long position_id,
    @PathVariable long phonetype_id,
    @PathVariable long province_id)
    
    {
    Position position = positionRepository.findById(position_id);
    Phonetype phonetype = phonetypeRepository.findById(phonetype_id);
    Province province = provinceRepository.findById(province_id);


    newEmployee.setName(name);
    newEmployee.setDate(date);
    newEmployee.setEmail(email);
    newEmployee.setPassword(password);
    newEmployee.setPhonenumber(phonenumber);
    newEmployee.setPosition(position);
    newEmployee.setPhonetype(phonetype);
    newEmployee.setProvince(province);
    return employeeRepository.save(newEmployee); 
    
    }
}