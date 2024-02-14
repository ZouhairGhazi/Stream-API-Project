package fr.codebusters.Stream.API.Project.repository;

import fr.codebusters.Stream.API.Project.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
