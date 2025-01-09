package com.jpacourse.service.impl;

import com.jpacourse.dto.AddPatientVisitTO;
import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.UpdatePatientTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class PatientServiceImpl implements PatientService {

    private final PatientDao patientDao;

    @Autowired
    public PatientServiceImpl(PatientDao patientDao)
    {
        this.patientDao = patientDao;
    }

    @Override
    public PatientTO findById(Long id) {
        final PatientEntity entity = patientDao.findOne(id);
        return PatientMapper.mapToTO(entity);
    }

    @Override
    public void deleteById(Long id) {
        patientDao.delete(id);
    }

    @Override
    public void addVisit(AddPatientVisitTO addPatientVisitTO) {
        LocalDateTime formattedDate = LocalDateTime.parse(addPatientVisitTO.getVisitDate());
        patientDao.addVisitToPatient(addPatientVisitTO.getPatientId(), addPatientVisitTO.getDoctorId(), formattedDate, addPatientVisitTO.getVisitDescription());
    }

    @Override
    public void update(UpdatePatientTO updatePatientTO) {
        PatientEntity entity = patientDao.findOne(updatePatientTO.getId());
        entity = PatientMapper.mapToEntity(entity, updatePatientTO);

        patientDao.update(entity);
    }

    @Override
    public List<PatientTO> findPatientsByLastName(String lastName) {
        List<PatientEntity> entities = patientDao.findByLastName(lastName);
        return entities.stream()
                .map(PatientMapper::mapToTO)
                .collect(Collectors.toList());
    }

}
