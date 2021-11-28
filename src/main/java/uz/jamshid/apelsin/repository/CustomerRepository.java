package uz.jamshid.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.jamshid.apelsin.entity.Customer;

import java.util.Set;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "select *\n" +
            "from orders o\n" +
            "         join customer c on o.customer_id = c.id\n" +
            "where o.date NOT BETWEEN '2016-1-1' AND '2016-12-31'", nativeQuery = true)
    Set<Customer> getCustomerWithoutOrders();
}
