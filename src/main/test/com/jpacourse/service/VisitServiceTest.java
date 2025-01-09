package com.jpacourse.service;

import com.jpacourse.dto.VisitTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class VisitServiceTest {

    @Autowired
    private VisitService visitService;

    @Test
    public void testFindVisitsByPatientId() {
        // given
        Long patientId = 1L; // Pacjent z pliku data.sql (Mariusz Makowski)

        // when
        List<VisitTO> visits = visitService.findVisitsByPatientId(patientId);

        // then
        assertThat(visits).isNotNull();
        assertThat(visits).hasSize(2); // Zgodnie z data.sql pacjent 1 ma 2 wizyty

        // verify the details of the first visit
        VisitTO firstVisit = visits.get(0);
        assertThat(firstVisit.getId()).isNotNull();
        assertThat(firstVisit.getDoctorFirstName()).isNotEmpty();
        assertThat(firstVisit.getDoctorLastName()).isNotEmpty();
        assertThat(firstVisit.getTime()).isNotNull();

        // verify the details of the second visit
        VisitTO secondVisit = visits.get(1);
        assertThat(secondVisit.getId()).isNotNull();
        assertThat(secondVisit.getDoctorFirstName()).isNotEmpty();
        assertThat(secondVisit.getDoctorLastName()).isNotEmpty();
        assertThat(secondVisit.getTime()).isNotNull();
    }
}
