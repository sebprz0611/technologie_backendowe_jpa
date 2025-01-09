package com.jpacourse.rest;

import com.jpacourse.dto.PatientVisitTO;
import com.jpacourse.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VisitController {

    private final VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/visits")
    public List<PatientVisitTO> getVisitsByPatientId(@RequestParam Long patientId) {
        return visitService.findVisitsByPatientId(patientId);
    }
}
