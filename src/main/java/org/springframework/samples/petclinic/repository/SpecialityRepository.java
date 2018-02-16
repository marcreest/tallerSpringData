package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.model.Specialty;

public interface SpecialityRepository extends JpaRepository<Specialty, Integer>{
	
	Specialty findFirstByName(String name);

}
