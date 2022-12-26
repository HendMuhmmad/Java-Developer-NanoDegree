package com.udacity.jdnd.course3.critter.pet;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.exception.NotFoundException;


@Service
@Transactional
public class PetService {
	
	@Autowired
	private  PetRepository petRepository;
	
	public Pet SavePet(Pet pet) {
		return petRepository.save(pet);
	}
	
	public Pet getPet(Long petId) throws NotFoundException {
		 Optional<Pet> optionalPet= petRepository.findById(petId);
		 if(optionalPet.isPresent()) {
			 return optionalPet.get();
		 }
		 else {
			 throw new NotFoundException("Pet Not Found");
		 }
	}
	
	public List<Pet> getPetsById(List<Long>ids) throws Exception{
		if(ids != null){
			return petRepository.findAllById(ids);
		}
		throw new IllegalArgumentException("Pets Ids must not be null");	
	}
	
	public List<Pet> getPets(){
		return petRepository.findAll();
	}
	
	public List<Pet> getPetsByOwner(long ownerId){
		return petRepository.findAllByOwnerId(ownerId);
	}

}
