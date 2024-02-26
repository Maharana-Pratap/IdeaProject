package com.example.kafkaproducer.service;

import com.example.kafkaproducer.dtos.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpEvent {
    private String status;
    private Employee emp;
}
