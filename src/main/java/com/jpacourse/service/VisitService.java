package com.jpacourse.service;

import com.jpacourse.dto.PatientVisitTO;

import java.util.List;

public interface VisitService {
    List<PatientVisitTO> findVisitsByPatientId(Long patientId);
}
