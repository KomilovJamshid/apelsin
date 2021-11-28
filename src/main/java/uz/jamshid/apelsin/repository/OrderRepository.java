package uz.jamshid.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.jamshid.apelsin.entity.Order;


import javax.persistence.Tuple;
import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "select *\n" +
            "from orders o\n" +
            "         left join detail d on o.id = d.order_id\n" +
            "where order_id IS NULL\n" +
            "  AND date < '2016-09-06'", nativeQuery = true)
    Set<Order> getOrdersWithoutDetail();

    @Query(value = "select *\n" +
            "from customer c\n" +
            "         join orders o on c.id = o.customer_id\n" +
            "    and o.id = (\n" +
            "        select subOrders.id\n" +
            "        from orders subOrders\n" +
            "        where subOrders.customer_id = o.customer_id\n" +
            "        order by subOrders.date DESC\n" +
            "        limit 1\n" +
            "    )", nativeQuery = true)
    Set<Order> getCustomersLastOrders();

    @Query(value = "select count(o.id), country\n" +
            "from orders o\n" +
            "         join customer c on o.customer_id = c.id\n" +
            "where o.date BETWEEN '2016-1-1' AND '2016-12-31'\n" +
            "group by country", nativeQuery = true)
    Set<Tuple> getNumberOfProductsInYear();
}
