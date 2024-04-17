package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entities.MachineData;
import com.example.demo.service.TimestampService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class TimestampControl {
	
	@Autowired
    private TimestampService tservice;

    @PostMapping("/machinedata")
    public ResponseEntity<String> saveMachineData(@RequestBody List<MachineData> machineDataList) {
        try {
            tservice.saveMachineData(machineDataList);
            return ResponseEntity.ok("Machine data saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving machine data: " + e.getMessage());
        }
    }
    
    
    //============================================================================================
    
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file");
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            List<MachineData> machineDataList = new ArrayList<>(); // Create a list to hold MachineData objects
            while ((line = br.readLine()) != null) {
                List<MachineData> parsedData = parseJsonToMachineData(line);
                machineDataList.addAll(parsedData); // Add parsed MachineData objects to the list
            }
            // Save all MachineData objects in the list
            tservice.saveMachineData(machineDataList);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Data uploaded successfully");
    }

    private List<MachineData> parseJsonToMachineData(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, new TypeReference<List<MachineData>>(){});
        } catch (JsonProcessingException e) {
            // Handle exception appropriately
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    
    
    
    //this is for sending data from database to react comp
    
    @GetMapping
    public List<MachineData> getAllMachineData() {
        return tservice.getAllMachineData();
    }

    }
