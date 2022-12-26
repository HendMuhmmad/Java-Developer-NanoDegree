package com.udacity.jdnd.course3.critter.user;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Nationalized;

import com.udacity.jdnd.course3.critter.pet.Pet;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Customer extends User {
	private String phoneNumber;
	@Nationalized
    private String notes;
	@OneToMany(mappedBy = "owner" ,fetch = FetchType.LAZY)
    private List<Pet> pets;
}
