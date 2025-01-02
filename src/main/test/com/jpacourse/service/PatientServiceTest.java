package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.enums.TreatmentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Transactional
    @Test
    public void testShouldRemovePatientAndCascadeVisits() {
        // given
        Long patientId = 1L;
        PatientTO patientTO = patientService.getPatientById(patientId);

        // sprawdzenie pacjenta i wizyt
        assertThat(patientTO).isNotNull();
        assertThat(patientTO.getVisits()).isNotEmpty();

        int visitCountBefore = patientTO.getVisits().size();

        // when
        patientService.deletePatientById(patientId);

        // then
        // usuniecie pacjenta
        assertThatThrownBy(() -> patientService.getPatientById(patientId))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Patient with ID " + patientId + " not found");

        // usuniecie wizyt pacjenta
        long remainingVisits = patientService.getAllPatients().stream()
                .flatMap(patient -> patient.getVisits().stream())
                .filter(visit -> visit.getPatientId().equals(patientId))
                .count();
        assertThat(remainingVisits).isZero();
    }

    @Transactional
    @Test
    public void testShouldReturnPatientWithCorrectTO() {
        // given
        Long patientId = 1L;

        // when
        PatientTO patientTO = patientService.getPatientById(patientId);

        // then
        assertThat(patientTO).isNotNull();
        assertThat(patientTO.getId()).isEqualTo(patientId);
        assertThat(patientTO.getFirstName()).isEqualTo("Mariusz"); // Dostosuj dane do testowej bazy
        assertThat(patientTO.getLastName()).isEqualTo("Makowski");
        assertThat(patientTO.getWeight()).isEqualTo(75); // Sprawdzenie dodatkowego pola

        // Weryfikacja wizyt
        List<VisitTO> visits = patientTO.getVisits();
        assertThat(visits).isNotEmpty();
        assertThat(visits).hasSize(2); // Dostosuj do danych testowych

        VisitTO firstVisit = visits.get(0);
        assertThat(firstVisit.getTime()).isEqualTo(LocalDateTime.of(2024, 12, 2, 14, 0));
        assertThat(firstVisit.getDoctorFirstName()).isEqualTo("Magdalena");
        assertThat(firstVisit.getDoctorLastName()).isEqualTo("Kaczmarek");
        assertThat(firstVisit.getTreatmentTypes()).containsExactlyInAnyOrder(
                TreatmentType.Badanie.name()
        );

        VisitTO secondVisit = visits.get(1);
        assertThat(secondVisit.getTime()).isEqualTo(LocalDateTime.of(2024, 6, 10, 9, 11, 30));
        assertThat(secondVisit.getDoctorFirstName()).isEqualTo("Magdalena");
        assertThat(secondVisit.getDoctorLastName()).isEqualTo("Kaczmarek");
        assertThat(secondVisit.getTreatmentTypes()).containsExactlyInAnyOrder(
                TreatmentType.Badanie.name()
        );
    }
}
