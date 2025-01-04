package com.jpacourse.mapper;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.entity.PatientEntity;

import java.util.stream.Collectors;

public class PatientMapper {

    public static PatientTO mapToTO(PatientEntity patient) {
        PatientTO to = new PatientTO();
        to.setId(patient.getId());
        to.setFirstName(patient.getFirstName());
        to.setLastName(patient.getLastName());
        to.setTelephoneNumber(patient.getTelephoneNumber());
        to.setEmail(patient.getEmail());
        to.setPatientNumber(patient.getPatientNumber());
        to.setDateOfBirth(patient.getDateOfBirth());
        to.setWeight(patient.getWeight());

        // Delegacja mapowania wizyt do VisitMapper
        to.setVisits(patient.getVisits().stream()
                .map(VisitMapper::mapToTO)
                .collect(Collectors.toList()));

        return to;
    }
}
