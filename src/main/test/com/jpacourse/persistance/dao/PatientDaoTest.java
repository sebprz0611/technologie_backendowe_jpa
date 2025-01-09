package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.impl.DoctorDaoImpl;
import com.jpacourse.persistence.dao.impl.PatientDaoImpl;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoTest {

    @Autowired
    PatientDaoImpl patientDao;

    @Autowired
    DoctorDaoImpl doctorDao;

    @Test
    @Transactional
    public void testAddVisitToPatient() {
        // given
        Long patientId = 1L; // ID pacjenta z data.sql
        Long doctorId = 2L; // ID lekarza z data.sql
        LocalDateTime visitDate = LocalDateTime.of(2025, 12, 12, 10, 10);
        String visitDescription = "Nowa wizyta kontrolna";

        PatientEntity patientBefore = patientDao.findOne(patientId);
        int initialVisitCount = patientBefore.getVisits().size();

        // when
        patientDao.addVisitToPatient(patientId, doctorId, visitDate, visitDescription);

        // then
        PatientEntity patientAfter = patientDao.findOne(patientId);
        assertThat(patientAfter).isNotNull();
        assertEquals(initialVisitCount + 1, patientAfter.getVisits().size());

        VisitEntity newVisit = patientAfter.getVisits().stream()
                .filter(visit -> visit.getDescription().equals(visitDescription))
                .findFirst()
                .orElse(null);

        assertThat(newVisit).isNotNull();
        assertEquals(doctorId, newVisit.getDoctor().getId());
        assertEquals(visitDate, newVisit.getTime());
        assertEquals(visitDescription, newVisit.getDescription());
    }

    @Test
    public void testFindByLastName() {
        // given

        // when
        List<PatientEntity> patients = patientDao.findByLastName("Makowski");

        // then
        assertThat(patients).isNotNull();
        assertThat(patients).hasSize(1); // Zgodnie z data.sql powinien być 1 pacjent
        assertThat(patients.get(0).getFirstName()).isEqualTo("Mariusz");
        assertThat(patients.get(0).getLastName()).isEqualTo("Makowski");
    }

    @Test
    public void testFindPatientsByVisitCountGreaterThan() {
        // given

        // when
        List<PatientEntity> patients = patientDao.findPatientsByVisitCountGreaterThan(1L);

        // then
        assertThat(patients).isNotNull();
        assertThat(patients).hasSize(1);
        assertThat(patients.get(0).getLastName()).isEqualTo("Makowski");
    }

    @Test
    public void testFindByWeightGreaterThan() {
        // given
        Integer weightThreshold = 70; // Waga do filtrowania pacjentów

        // when
        List<PatientEntity> patients = patientDao.findByWeightGreaterThan(weightThreshold);

        // then
        assertThat(patients).isNotNull();
        assertThat(patients).isNotEmpty(); // Powinno być co najmniej kilku pacjentów
        assertThat(patients).allMatch(patient -> patient.getWeight() > weightThreshold);

        // optional
        assertThat(patients).extracting("lastName").contains("Makowski", "Borkowski");
    }

}