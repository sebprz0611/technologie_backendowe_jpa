package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.rest.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao
{
    @Autowired
    DoctorDao doctorDao;

    @Override
    public void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String visitDescription) {
        PatientEntity patientEntity = findOne(patientId);

        if (patientEntity == null) {
            throw new EntityNotFoundException(patientId);
        }

        DoctorEntity doctorEntity = doctorDao.findOne(doctorId);

        if(doctorEntity == null)  {
            throw new EntityNotFoundException(doctorId);
        }

        VisitEntity visit = new VisitEntity();
        visit.setDoctor(doctorEntity);
        visit.setPatient(patientEntity);
        visit.setTime(visitDate);
        visit.setDescription(visitDescription);

        if(patientEntity.getVisits() == null) {
            patientEntity.setVisits(new ArrayList<>());
        }

        patientEntity.getVisits().add(visit);

        update(patientEntity);
    }

    @Override
    public List<PatientEntity> findByLastName(String lastName) {
        String query = "SELECT p FROM PatientEntity p WHERE p.lastName = :lastName";
        return entityManager.createQuery(query, PatientEntity.class)
                .setParameter("lastName", lastName)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsByVisitCountGreaterThan(Long visitCount) {
        String query = "SELECT p FROM PatientEntity p " +
                "JOIN p.visits v " +
                "GROUP BY p " +
                "HAVING COUNT(v) > :visitCount";
        return entityManager.createQuery(query, PatientEntity.class)
                .setParameter("visitCount", visitCount)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findByWeightGreaterThan(Integer weight) {
        String query = "SELECT p FROM PatientEntity p WHERE p.weight > :weight";
        return entityManager.createQuery(query, PatientEntity.class)
                .setParameter("weight", weight)
                .getResultList();
    }
}
