package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter @Setter
public class Employee extends User {
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private Set<EmployeeSkill> skills;
	
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private Set<DayOfWeek> daysAvailable;

}
