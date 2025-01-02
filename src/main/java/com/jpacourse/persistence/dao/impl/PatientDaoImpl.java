package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDaoCustom;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Repository
public class PatientDaoImpl implements PatientDaoCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitTime, String visitDescription) {
        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
        DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);

        if (patient == null) {
            throw new IllegalArgumentException("Patient with ID " + patientId + " not found");
        }
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor with ID " + doctorId + " not found");
        }

        VisitEntity newVisit = new VisitEntity();
        newVisit.setPatient(patient);
        newVisit.setDoctor(doctor);
        newVisit.setTime(visitTime);
        newVisit.setDescription(visitDescription);

        patient.getVisits().add(newVisit);
        entityManager.merge(patient);
    }
}

