package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistence.dao.PatientDao;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientDao patientDao;

    public PatientService(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    public List<PatientTO> getAllPatients() {
        return patientDao.findAll()
                .stream()
                .map(PatientMapper::mapToTO)
                .collect(Collectors.toList());
    }

    public PatientTO getPatientById(Long id) {
        return patientDao.findById(id)
                .map(PatientMapper::mapToTO)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }
}