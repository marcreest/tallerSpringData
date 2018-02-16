/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.repository.SpecialityRepository;
import org.springframework.samples.petclinic.repository.VetRepository;

/**
 * PetClinic Spring Boot Application.
 * 
 * @author Dave Syer
 *
 */
@SpringBootApplication
public class PetClinicApplication {
	
	private static final Logger log = LoggerFactory.getLogger(PetClinicApplication.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PetClinicApplication.class, args);
    }
    
    @Bean
	public CommandLineRunner demoVetRepository(VetRepository vetRepository, SpecialityRepository specialityRepository) {
		return (args) -> {
			log.info("*****************************************************");
			log.info("BOOTCAMP - Spring y Spring Data - vetRepository");
			log.info("*****************************************************");
			
			//TODO Añade aquí tu código
			Vet veterinario = new Vet();
			
			veterinario.setFirstName( "Israel" );
			veterinario.setLastName( "Fernández" );

			veterinario = vetRepository.save( veterinario );
			
			Vet resultado = vetRepository.findOne( veterinario.getId() );
			System.out.println( "Se ha insertado: " + resultado.getFirstName() + " " + resultado.getLastName() + "." );
			
			List<Specialty> especialidades = specialityRepository.findAll();
			for( Specialty s: especialidades ) {
				veterinario.addSpecialty( s );
			}
			vetRepository.save( veterinario );
			
			List<Vet> listaVeterinarios = vetRepository.findAll();
			
			for( Vet v: listaVeterinarios ) {
				System.out.print( "Veterinario: " + v.getFirstName() + " " + v.getLastName() + ". Especialidades: " );
				for( Specialty s: v.getSpecialties() ) {
					System.out.print( s.getName() + ", " );
				}
				System.out.print( "\n" );
			}
			
			//Obtener una lista de Vets filtrando por lastName.
			System.out.println( "Obtener una lista de Vets filtrando por lastName." );
			List<Vet> lista1 = vetRepository.findByFirstName( "Israel" );
			for( Vet v: lista1 ) {
				System.out.print( "Veterinario: " + v.getFirstName() + " " + v.getLastName() + ". Especialidades: " );
				for( Specialty s: v.getSpecialties() ) {
					System.out.print( s.getName() + ", " );
				}
				System.out.print( "\n" );
			}
			
			//Obtener una lista de Vets filtrando por firstName y lastName.
			System.out.println( "Obtener una lista de Vets filtrando por firstName y lastName." );
			List<Vet> lista2 = vetRepository.findByFirstNameAndLastName( "Israel", "Fernández" );
			for( Vet v: lista2 ) {
				System.out.print( "Veterinario: " + v.getFirstName() + " " + v.getLastName() + ". Especialidades: " );
				for( Specialty s: v.getSpecialties() ) {
					System.out.print( s.getName() + ", " );
				}
				System.out.print( "\n" );
			}
			
			//Obtener una lista de Vets filtrando buscando en firstName o lastName.
			System.out.println( "Obtener una lista de Vets filtrando buscando en firstName o lastName." );
			List<Vet> lista3 = vetRepository.findByFirstNameOrLastName( "Israel", "" );
			for( Vet v: lista3 ) {
				System.out.print( "Veterinario: " + v.getFirstName() + " " + v.getLastName() + ". Especialidades: " );
				for( Specialty s: v.getSpecialties() ) {
					System.out.print( s.getName() + ", " );
				}
				System.out.print( "\n" );
			}
		};
	}
    
}
