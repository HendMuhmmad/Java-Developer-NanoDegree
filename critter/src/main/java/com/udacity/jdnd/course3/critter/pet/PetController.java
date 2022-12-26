package com.udacity.jdnd.course3.critter.pet;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.udacity.jdnd.course3.critter.user.CustomerService;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
	@Autowired
	private PetService petService;
	
	@Autowired
	private CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) throws Exception {
    	Pet pet = fromPetDTOToPet(petDTO);
    	return fromPetToPetDTO(petService.SavePet(pet));
    }

	@GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) throws Exception {
		return fromPetToPetDTO(petService.getPet(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
    	List<PetDTO> petsDTO = new ArrayList<PetDTO>();
    	List<Pet> pets = petService.getPets();
    	for(Pet pet : pets) {
    		petsDTO.add(fromPetToPetDTO(pet));
    	}
    	return petsDTO;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
    	List<PetDTO> petsDTO = new ArrayList<PetDTO>();
    	List<Pet> pets = petService.getPetsByOwner(ownerId);
    	for(Pet pet : pets) {
    		petsDTO.add(fromPetToPetDTO(pet));
    	}
    	return petsDTO;
    }
    
    private Pet fromPetDTOToPet(PetDTO petDTO) throws Exception {
    	Pet pet = new Pet();
    	BeanUtils.copyProperties(petDTO, pet);
    	pet.setOwner(customerService.getOwnerById(petDTO.getOwnerId()));	
		return pet;
	}
    
	private PetDTO fromPetToPetDTO(Pet pet) {
		PetDTO petDTO = new PetDTO();
    	BeanUtils.copyProperties(pet,petDTO);
    	petDTO.setOwnerId(pet.getOwner().getId());
		return petDTO;
	}
}
