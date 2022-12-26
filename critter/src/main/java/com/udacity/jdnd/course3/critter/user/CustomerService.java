package com.udacity.jdnd.course3.critter.user;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.exception.NotFoundException;

@Service
@Transactional
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Customer getOwnerByPet(long petId) {
		return customerRepository.findByPetsId(petId);
	}
	
	public Customer getOwnerById(long id) throws NotFoundException {
		 Optional<Customer> optionalCustomer= customerRepository.findById(id);
		 if(optionalCustomer.isPresent()) {
			 return optionalCustomer.get();
		 }
		 else {
			 throw new NotFoundException("Customer not Found");
		 }		
	}

	public List<Long> getPetsIds(long id) {
		return customerRepository.findPetsIds(id);
	}
	

}
