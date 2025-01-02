package com.jpacourse.mapper;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.MedicalTreatmentEntity;
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

        to.setVisits(patient.getVisits().stream().map(visit -> {
            VisitTO visitTO = new VisitTO();
            visitTO.setTime(visit.getTime());
            visitTO.setDoctorFirstName(visit.getDoctor().getFirstName());
            visitTO.setDoctorLastName(visit.getDoctor().getLastName());
            visitTO.setTreatmentTypes(
                    visit.getMedicalTreatments().stream()
                            .map(treatment -> treatment.getType().name())
                            .collect(Collectors.toList())
            );
            return visitTO;
        }).collect(Collectors.toList()));

        return to;
    }
}

