package uz.jamshid.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.jamshid.apelsin.entity.Order;

import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "select *\n" +
            "from orders o\n" +
            "         left join detail d on o.id = d.order_id\n" +
            "where order_id IS NULL\n" +
            "  AND date < '2016-09-06'", nativeQuery = true)
    Set<Order> getOrdersWithoutDetail();
}
