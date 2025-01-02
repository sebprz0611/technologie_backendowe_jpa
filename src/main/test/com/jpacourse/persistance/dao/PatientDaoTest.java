package com.jpacourse.persistance.dao;


import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    public void testShouldAddVisitToPatientWithDoctorRelationship() {
        // given
        Long patientId = 1L;
        Long doctorId = 1L;
        LocalDateTime visitTime = LocalDateTime.of(2025, 1, 2, 10, 0);
        String visitDescription = "Kontrolne badanie";

        // when
        patientDao.addVisitToPatient(patientId, doctorId, visitTime, visitDescription);

        // then
        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
        assertThat(patient).isNotNull();
        assertThat(patient.getVisits()).isNotEmpty();

        VisitEntity visit = patient.getVisits().stream()
                .filter(v -> v.getTime().equals(visitTime))
                .findFirst()
                .orElse(null);

        assertThat(visit).isNotNull();
        assertThat(visit.getDescription()).isEqualTo(visitDescription);
        assertThat(visit.getDoctor()).isNotNull();
        assertThat(visit.getDoctor().getId()).isEqualTo(doctorId);
    }
}
