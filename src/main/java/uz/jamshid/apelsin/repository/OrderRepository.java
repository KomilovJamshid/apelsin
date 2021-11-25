package uz.jamshid.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.jamshid.apelsin.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
