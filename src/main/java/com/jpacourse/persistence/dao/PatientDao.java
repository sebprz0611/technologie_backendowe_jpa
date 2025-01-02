package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientDao extends JpaRepository<PatientEntity, Long>, PatientDaoCustom {
}
