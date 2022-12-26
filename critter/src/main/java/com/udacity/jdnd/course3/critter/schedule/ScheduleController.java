package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.EmployeeService;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private PetService petService;

	@PostMapping
	public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) throws Exception {
		return fromScheduleToScheduleDTO(scheduleService.createSchedule(fromScheduleDTOToSchedule(scheduleDTO)));
	}

	@GetMapping
	public List<ScheduleDTO> getAllSchedules() {
		List<Schedule> schedules = scheduleService.getAllSchedules();
		List<ScheduleDTO> scheduleDTOs = new ArrayList<ScheduleDTO>();
		for (Schedule schedule : schedules) {
			scheduleDTOs.add(fromScheduleToScheduleDTO(schedule));
		}
		return scheduleDTOs;
	}

	@GetMapping("/pet/{petId}")
	public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
		List<Schedule> schedules = scheduleService.getScheduleForPet(petId);
		return fromScheduleListToScheduleDTOList(schedules);

	}

	@GetMapping("/employee/{employeeId}")
	public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
		List<Schedule> schedules = scheduleService.getScheduleForEmployee(employeeId);
		return fromScheduleListToScheduleDTOList(schedules);
	}

	@GetMapping("/customer/{customerId}")
	public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
		List<Schedule> schedules = scheduleService.getScheduleForCustomer(customerId);
		return fromScheduleListToScheduleDTOList(schedules);
	}

	private ScheduleDTO fromScheduleToScheduleDTO(Schedule createSchedule) {
		ScheduleDTO scheduleDTO = new ScheduleDTO();
		BeanUtils.copyProperties(createSchedule, scheduleDTO);
		scheduleDTO.setEmployeeIds(scheduleService.findScheduleEmployeesIds(createSchedule.getId()));
		scheduleDTO.setPetIds(scheduleService.findSchedulePetsIds(createSchedule.getId()));
		return scheduleDTO;
	}

	private Schedule fromScheduleDTOToSchedule(ScheduleDTO scheduleDTO) throws Exception {
		Schedule schedule = new Schedule();
		BeanUtils.copyProperties(scheduleDTO, schedule);
		schedule.setEmployees(employeeService.findEmployesById(scheduleDTO.getEmployeeIds()));
		schedule.setPets(petService.getPetsById(scheduleDTO.getPetIds()));
		return schedule;
	}

	private List<ScheduleDTO> fromScheduleListToScheduleDTOList(List<Schedule> schedules) {
		List<ScheduleDTO> scheduleDTOs = new ArrayList<ScheduleDTO>();
		for (Schedule schedule : schedules) {
			scheduleDTOs.add(fromScheduleToScheduleDTO(schedule));
		}
		return scheduleDTOs;

	}

}
