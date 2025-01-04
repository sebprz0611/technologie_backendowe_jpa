package com.jpacourse.mapper;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.VisitEntity;

import java.util.stream.Collectors;

public class VisitMapper {

    public static VisitTO mapToTO(VisitEntity visit) {
        VisitTO visitTO = new VisitTO();
        visitTO.setTime(visit.getTime());
        visitTO.setDoctorFirstName(visit.getDoctor().getFirstName());
        visitTO.setDoctorLastName(visit.getDoctor().getLastName());
        visitTO.setPatientId(visit.getPatient().getId());
        visitTO.setTreatmentTypes(
                visit.getMedicalTreatments().stream()
                        .map(treatment -> treatment.getType().name())
                        .collect(Collectors.toList())
        );
        return visitTO;
    }
}
