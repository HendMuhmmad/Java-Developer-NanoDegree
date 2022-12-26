package com.udacity.jdnd.course3.critter.pet;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Nationalized;

import com.udacity.jdnd.course3.critter.user.Customer;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Pet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Enumerated(EnumType.STRING)
    private PetType type;
	@Nationalized
    private String name;
	
	@ManyToOne
	@JoinColumn(name="owner_id")
    private Customer owner;
	
    private LocalDate birthDate;
    @Nationalized
    private String notes;
}
