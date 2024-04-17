package com.example.demo.entities;

import java.time.LocalDateTime;

import com.example.demo.config.LocalDateTimeDeserializer;
import com.example.demo.config.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "machine_data")
public class MachineData {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonProperty("ts")
	private LocalDateTime ts;
	@Column
	@JsonProperty("machine_status")
    private Integer machineStatus;
	@Column
	@JsonProperty("vibration")
    private Integer vibration;

    // Constructors, getters, and setters

    public LocalDateTime getTs() {
        return ts;
    }

    public void setTs(LocalDateTime ts) {
        this.ts = ts;
    }

    public Integer getMachineStatus() {
        return machineStatus;
    }

    public void setMachineStatus(Integer machineStatus) {
        this.machineStatus = machineStatus;
    }

    public Integer getVibration() {
        return vibration;
    }

    public void setVibration(Integer vibration) {
        this.vibration = vibration;
    }
}


