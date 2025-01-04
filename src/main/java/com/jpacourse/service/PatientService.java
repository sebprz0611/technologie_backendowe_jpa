package com.jpacourse.service;

import com.jpacourse.dto.AddPatientVisitTO;
import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.UpdatePatientTO;

public interface PatientService {
    PatientTO findById(final Long id);

    void deleteById(final Long id);

    void addVisit(AddPatientVisitTO addPatientVisitTO);

    void update(UpdatePatientTO updatePatientTO);
}
