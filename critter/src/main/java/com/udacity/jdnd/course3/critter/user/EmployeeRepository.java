package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	@Query("select e from Employee e  join e.daysAvailable d join e.skills s where :day = d and s in :skills group by e having count(s) = :skillsLength")
	List<Employee> findEmployeesForService(DayOfWeek day, Set<EmployeeSkill> skills, long skillsLength);

}
