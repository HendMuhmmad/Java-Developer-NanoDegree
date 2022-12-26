package com.udacity.jdnd.course3.critter.schedule;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	@Query("select p.id from Schedule s join s.pets p where s.id = :scheduleId")
	List<Long> findSchedulePetsIds(Long scheduleId);
	@Query("select e.id from Schedule s join s.employees e where s.id = :scheduleId")
	List<Long> findScheduleEmployeesIds(Long scheduleId);
	List<Schedule> findAllByPetsId(Long petId);
	List<Schedule> findAllByEmployeesId(Long employeeId);
	List<Schedule> findAllByPetsOwnerId(Long ownerId);
}
