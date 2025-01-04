package com.jpacourse.persistence.entity;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name = "VISIT")
public class VisitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String description;

	@Column(nullable = false)
	private LocalDateTime time;

	// Dwukierunkowa relacja Visit i Doctor
	@ManyToOne(optional = false)
	@JoinColumn(name = "doctor_id")

	private DoctorEntity doctor;

	// Dwukierunkowa relacja Visit i Patient
	@ManyToOne(optional = false)
	@JoinColumn(name = "patient_id")

	private PatientEntity patient;

	// Dwukierunkowa relacja Visit i MedicalTreatment
	@OneToMany(mappedBy = "visit", cascade = CascadeType.ALL)

	private Collection<MedicalTreatmentEntity> medicalTreatment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public DoctorEntity getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorEntity doctor) {
		this.doctor = doctor;
	}

	public PatientEntity getPatient() {
		return patient;
	}

	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}

	public Collection<MedicalTreatmentEntity> getMedicalTreatment() {
		return medicalTreatment;
	}

	public void setMedicalTreatment(Collection<MedicalTreatmentEntity> medicalTreatment) {
		this.medicalTreatment = medicalTreatment;
	}
}
