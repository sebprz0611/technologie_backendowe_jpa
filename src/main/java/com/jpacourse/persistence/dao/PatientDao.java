package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long>
{
    void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String visitDescription);
    List<PatientEntity> findByLastName(String lastName);
    List<PatientEntity> findPatientsByVisitCountGreaterThan(Long visitCount);

}

