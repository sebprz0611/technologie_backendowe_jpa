package com.jpacourse.service.impl;

import com.jpacourse.dto.PatientVisitTO;
import com.jpacourse.mapper.VisitMapper;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitServiceImpl implements VisitService {

    private final VisitDao visitDao;

    @Autowired
    public VisitServiceImpl(VisitDao visitDao) {
        this.visitDao = visitDao;
    }

    @Override
    public List<PatientVisitTO> findVisitsByPatientId(Long patientId) {
        List<VisitEntity> visits = visitDao.findByPatientId(patientId);
        return visits.stream()
                .map(VisitMapper::mapToPatientVisitTO)
                .collect(Collectors.toList());
    }
}
