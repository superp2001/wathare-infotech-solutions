package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.MachineData;
import com.example.demo.repository.TimestampRepo;


@Service
public class TimestampService {
	    @Autowired
	    private TimestampRepo trepo;
	    public void saveMachineData(MachineData machineData) {
	        trepo.save(machineData);
	    }

	    public void saveMachineData(List<MachineData> machineDataList) {
	        trepo.saveAll(machineDataList);
	    }
	   //this is for sending the data from database to react
	    public List<MachineData> getAllMachineData() {
	        return trepo.findAll();
	    }

}
