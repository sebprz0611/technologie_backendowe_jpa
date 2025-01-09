package com.jpacourse.service;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.UpdatePatientTO;
import com.jpacourse.dto.PatientVisitTO;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.PatientEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private VisitDao visitDao;

    @Autowired
    private DoctorDao doctorDao;

    @Transactional
    @Test
    public void testShouldFindPatientById() {
        // given
        Long patientId = 1L; // ID pacjenta z pliku data.sql
        // when
        PatientTO patientTO = patientService.findById(patientId);
        // then
        assertThat(patientTO).isNotNull();
        assertThat(patientTO.getId()).isEqualTo(patientId);

        assertThat(patientTO.getFirstName()).isEqualTo("Mariusz");
        assertThat(patientTO.getLastName()).isEqualTo("Makowski");
        assertThat(patientTO.getTelephoneNumber()).isEqualTo("+48 603 741 812");
        assertThat(patientTO.getEmail()).isEqualTo("mariuszmakowski@tlen.pl");
        assertThat(patientTO.getPatientNumber()).isEqualTo("P001");
        assertThat(patientTO.getWeight()).isEqualTo(75);
        assertThat(patientTO.getDateOfBirth()).isEqualTo(LocalDate.of(1982, 4, 15));

        AddressTO address = patientTO.getAddress();
        assertThat(address).isNotNull();
        assertThat(address.getId()).isEqualTo(1L);
        assertThat(address.getCity()).isEqualTo("Malbork");
        assertThat(address.getAddressLine1()).isEqualTo("ul. Wypoczynkowa 93");
        assertThat(address.getAddressLine2()).isEqualTo("Mieszkanie 13");
        assertThat(address.getPostalCode()).isEqualTo("82-200");


        Collection<PatientVisitTO> visits = patientTO.getVisits();
        assertThat(visits).isNotNull();
        assertThat(visits).hasSize(2); // Liczba wizyt w data.sql

        PatientVisitTO firstVisit = visits.iterator().next();
        assertThat(firstVisit).isNotNull();
        assertThat(firstVisit.getId()).isNotNull();
        assertThat(firstVisit.getTime()).isNotNull();
        assertThat(firstVisit.getDoctorFirstName()).isNotNull();
        assertThat(firstVisit.getDoctorLastName()).isNotNull();
    }

    @Transactional
    @Test
    public void testShouldUpdatePatient() {
        // given
        Long patientId = 1L;
        PatientEntity existingPatient = patientDao.findOne(patientId);
        assertThat(existingPatient).isNotNull();

        UpdatePatientTO updatePatientTO = new UpdatePatientTO();
        updatePatientTO.setId(patientId);
        updatePatientTO.setFirstName("UpdatedFirstName");
        updatePatientTO.setTelephoneNumber("123456789");
        updatePatientTO.setEmail("updatedemail@example.com");
        updatePatientTO.setPatientNumber("PN12345");
        updatePatientTO.setDateOfBirth(LocalDate.of(1990, 1, 1));
        updatePatientTO.setWeight(80); //

        AddressTO addressTO = new AddressTO();
        addressTO.setAddressLine1("Updated Address Line 1");
        addressTO.setCity("Updated City");
        updatePatientTO.setAddress(addressTO);

        // when
        patientService.update(updatePatientTO);

        // then
        PatientEntity updatedPatient = patientDao.findOne(patientId);
        assertThat(updatedPatient).isNotNull();
        assertThat(updatedPatient.getFirstName()).isEqualTo("UpdatedFirstName");
        assertThat(updatedPatient.getTelephoneNumber()).isEqualTo("123456789");
        assertThat(updatedPatient.getEmail()).isEqualTo("updatedemail@example.com");
        assertThat(updatedPatient.getPatientNumber()).isEqualTo("PN12345");
        assertThat(updatedPatient.getDateOfBirth()).isEqualTo(LocalDate.of(1990, 1, 1));
        assertThat(updatedPatient.getWeight()).isEqualTo(80); // Poprawione na weight
        assertThat(updatedPatient.getAddress()).isNotNull();
        assertThat(updatedPatient.getAddress().getAddressLine1()).isEqualTo("Updated Address Line 1");
        assertThat(updatedPatient.getAddress().getCity()).isEqualTo("Updated City");
    }

    @Transactional
    @Test
    public void testShouldRemovePatient() {
        // given
        Long patientId = 1L;
        assertThat(patientService.findById(patientId)).isNotNull();
        assertThat(visitDao.findByPatientId(patientId)).isNotEmpty();

        long initialDoctorCount = doctorDao.count();

        // when
        patientService.deleteById(patientId);

        // then
        assertThat(patientService.findById(patientId)).isNull();
        assertThat(visitDao.findByPatientId(patientId)).isEmpty();

        long finalDoctorCount = doctorDao.count();
        assertThat(finalDoctorCount).isEqualTo(initialDoctorCount);
    }
}
