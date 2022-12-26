package com.udacity.jdnd.course3.critter.user;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
	@Query("select p.id from Customer c join c.pets p where c.id = :id")
	List<Long> findPetsIds(long id);
	Customer findByPetsId(Long id);
	

}
