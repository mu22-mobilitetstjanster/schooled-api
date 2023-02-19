package com.example.schooledmanagementapi.controller;

import com.example.schooledmanagementapi.model.Registration;
import com.example.schooledmanagementapi.model.RegistrationDetails;
import com.example.schooledmanagementapi.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagementController {

  @Autowired
  private RegistrationService registrationService;

  @PostMapping("register")
  public ResponseEntity<RegistrationDetails> registerToCourse(@RequestBody Registration registration) {
    RegistrationDetails details = registrationService.save(registration);
    return ResponseEntity.ok(details);
  }
}
