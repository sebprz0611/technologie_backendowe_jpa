package com.jpacourse.mapper;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.UpdatePatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.AddressEntity;
import com.jpacourse.persistence.entity.PatientEntity;

import java.util.List;
import java.util.stream.Collectors;


public class PatientMapper {

    public static PatientTO mapToTO(final PatientEntity patientEntity){
        if (patientEntity == null)
        {
            return null;
        }
        final PatientTO patientTO = new PatientTO();
        patientTO.setId(patientEntity.getId());
        patientTO.setFirstName(patientEntity.getFirstName());
        patientTO.setLastName(patientEntity.getLastName());
        patientTO.setTelephoneNumber(patientEntity.getTelephoneNumber());
        patientTO.setEmail(patientEntity.getEmail());
        patientTO.setPatientNumber(patientEntity.getPatientNumber());
        patientTO.setDateOfBirth(patientEntity.getDateOfBirth());
        patientTO.setWeight(patientEntity.getWeight());

        AddressTO addressTO = AddressMapper.mapToTO(patientEntity.getAddress());
        patientTO.setAddress(addressTO);

        List<VisitTO> visits = patientEntity
                .getVisits()
                .stream()
                .map(VisitMapper::mapToPatientVisitTO)
                .collect(Collectors.toList());

        patientTO.setVisits(visits);

        return patientTO;
    }

    public static PatientEntity mapToEntity(PatientEntity patientEntity, final UpdatePatientTO patientTO){
        if (patientTO == null)
        {
            return patientEntity;
        }

        if (patientTO.getFirstName() != null) {
            patientEntity.setFirstName(patientTO.getFirstName());
        }

        if (patientTO.getLastName() != null) {
            patientEntity.setLastName(patientTO.getLastName());
        }

        if (patientTO.getTelephoneNumber() != null) {
            patientEntity.setTelephoneNumber(patientTO.getTelephoneNumber());
        }

        if (patientTO.getEmail() != null) {
            patientEntity.setEmail(patientTO.getEmail());
        }

        if (patientTO.getPatientNumber() != null) {
            patientEntity.setPatientNumber(patientTO.getPatientNumber());
        }

        if (patientTO.getDateOfBirth() != null) {
            patientEntity.setDateOfBirth(patientTO.getDateOfBirth());
        }

        if (patientTO.getWeight() != null) {
            patientEntity.setWeight(patientTO.getWeight());
        }

        if (patientTO.getAddress() != null) {
            AddressEntity addressEntity = AddressMapper.mapToEntity(patientTO.getAddress());
            patientEntity.setAddress(addressEntity);
        }

        return patientEntity;
    }

}
