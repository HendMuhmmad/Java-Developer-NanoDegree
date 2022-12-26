package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private EmployeeService employeeService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
    	return fromCustomerToCustomerDTO(customerService.saveCustomer(fromCustomerDTOToCustomer(customerDTO)));
    	
    }

	@GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
		List<Customer> customers = customerService.getAllCustomers();
		List<CustomerDTO> customerDTO = new ArrayList<CustomerDTO>();
		for(Customer customer: customers) {
			customerDTO.add(fromCustomerToCustomerDTO(customer));
		}
		return customerDTO;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
    	return fromCustomerToCustomerDTO(customerService.getOwnerByPet(petId));
    }
    
    private CustomerDTO fromCustomerToCustomerDTO(Customer saveCustomer) {
    	CustomerDTO customerDTO = new CustomerDTO();
    	BeanUtils.copyProperties(saveCustomer, customerDTO);
    	customerDTO.setPetIds(customerService.getPetsIds(saveCustomer.getId()));
		return customerDTO;
	}

	private Customer fromCustomerDTOToCustomer(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDTO, customer);
		return customer;
	}


    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return fromEmployeeToEmployeeDTO(employeeService.saveEmployee(fromEmployeeDTOToEmployee(employeeDTO)));
    }

	@PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) throws Exception {
        return fromEmployeeToEmployeeDTO(employeeService.getEmployee(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) throws Exception {
    	Employee employee = employeeService.getEmployee(employeeId);
    	employee.setDaysAvailable(daysAvailable);
    	employeeService.saveEmployee(employee);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
    	List<Employee> employees = employeeService.findEmployeesForService(employeeDTO.getDate().getDayOfWeek(),employeeDTO.getSkills());
    	List<EmployeeDTO> employeeDTOs = new ArrayList<EmployeeDTO>(); 
    	for(Employee employee : employees) {
    		employeeDTOs.add(fromEmployeeToEmployeeDTO(employee));
    	}
    	return employeeDTOs;
    }
    
    private EmployeeDTO fromEmployeeToEmployeeDTO(Employee saveEmployee) {
    	EmployeeDTO employeeDTO = new EmployeeDTO();
    	BeanUtils.copyProperties(saveEmployee, employeeDTO);
		return employeeDTO;
	}

	private Employee fromEmployeeDTOToEmployee(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDTO, employee);
		return employee;
	}


}
