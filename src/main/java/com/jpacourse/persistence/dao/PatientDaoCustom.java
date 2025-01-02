package com.jpacourse.persistence.dao;

import java.time.LocalDateTime;

public interface PatientDaoCustom {
    void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitTime, String visitDescription);
}
