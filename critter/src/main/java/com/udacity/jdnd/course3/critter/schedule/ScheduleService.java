package com.udacity.jdnd.course3.critter.schedule;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class ScheduleService {
	@Autowired
	private ScheduleRepository scheduleRepository;
	public Schedule createSchedule(Schedule schedule) {
		return scheduleRepository.save(schedule);
	}
	
	public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }
	
    public List<Schedule> getScheduleForPet(long petId) {
    	return scheduleRepository.findAllByPetsId(petId);
    }

    public List<Schedule> getScheduleForEmployee (long employeeId) {
    	return scheduleRepository.findAllByEmployeesId(employeeId);
    }

    public List<Schedule> getScheduleForCustomer(long customerId) {
    	return scheduleRepository.findAllByPetsOwnerId(customerId);
    }
    public List<Long> findScheduleEmployeesIds(long scheduleId){
    	return scheduleRepository.findScheduleEmployeesIds(scheduleId);
    }
    public List<Long> findSchedulePetsIds(long scheduleId){
    	return scheduleRepository.findSchedulePetsIds(scheduleId);
    }

}
