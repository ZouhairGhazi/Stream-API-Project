package fr.codebusters.Stream.API.Project.repository;

import fr.codebusters.Stream.API.Project.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
