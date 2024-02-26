package com.example.kafkaproducer.controller;

import com.example.kafkaproducer.dtos.Employee;
import com.example.kafkaproducer.service.EmpEvent;
import com.example.kafkaproducer.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/produce")
public class EmpProducerController {
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplateObject;
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplateString;

    private EmpService empService;

    @PostMapping("/send")
    public ResponseEntity<?> produceMessage(@RequestBody Employee emp) {
        if (null != emp) {
            EmpEvent event = new EmpEvent();
            event.setEmp(emp);
            event.setStatus("success");
            CompletableFuture<?> uploadData = kafkaTemplateObject.send("emp-topic", event);
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.ok("Data uploading error found");
    }
@GetMapping("/kafkaCloudStream/{msg}")
    public ResponseEntity<?> dataForCloudStream(@PathVariable String msg) {
        CompletableFuture<SendResult<String, String>> send = kafkaTemplateString.send("processor-in", msg);
        return ResponseEntity.ok("Data send to processor-in topics");
    }
}
