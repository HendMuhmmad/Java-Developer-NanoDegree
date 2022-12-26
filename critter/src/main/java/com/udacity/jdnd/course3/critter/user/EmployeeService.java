package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.exception.NotFoundException;



@Service
@Transactional
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Employee getEmployee(long employeeId) throws NotFoundException {
		 Optional<Employee> optionalEmployee= employeeRepository.findById(employeeId);
		 if(optionalEmployee.isPresent()) {
			 return optionalEmployee.get();
		 }
		 else {
			 throw new NotFoundException("Employee not found");
		 }
	}

	public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) throws NotFoundException {
		 Optional<Employee> optionalEmployee= employeeRepository.findById(employeeId);
		 if(optionalEmployee.isPresent()) {
			 Employee employee = optionalEmployee.get();
			 employee.setDaysAvailable(daysAvailable);
		 }
		 else {
			 throw new NotFoundException("Employee not found");
		 }
	}
	
	public List<Employee> findEmployesById(List<Long> ids){
		if(ids != null){
			return employeeRepository.findAllById(ids);
		}
		throw new IllegalArgumentException("Employees Ids must not be null");
		
		
	}

	public List<Employee> findEmployeesForService(DayOfWeek dayAvailable, Set<EmployeeSkill> skills) {
		return employeeRepository.findEmployeesForService(dayAvailable,skills,skills.size());
		
	}
}
