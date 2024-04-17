package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.MachineData;
import com.example.demo.service.TimestampService;

@RestController
@RequestMapping("/api/machine-data")
public class MachineDataController {

    @Autowired
    private TimestampService tservice;

    @GetMapping
    public List<MachineData> getAllMachineData() {
        return tservice.getAllMachineData();
    }
}