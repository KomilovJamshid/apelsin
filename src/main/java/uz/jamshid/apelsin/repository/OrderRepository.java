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

    @Query(value = "select count(o.id), c.country\n" +
            "from orders o\n" +
            "         join customer c on o.customer_id = c.id\n" +
            "where o.date BETWEEN '2016-1-1' AND '2016-12-31'\n" +
            "group by c.country", nativeQuery = true)
    Set<Order> getNumberOfProductsInYear();

    @Query(value = "select o.id, o.date, sum(d.quantity * p.price)\n" +
            "from orders o\n" +
            "         left join invoice i on o.id = i.order_id\n" +
            "         join detail d on o.id = d.order_id\n" +
            "         join product p on d.product_id = p.id\n" +
            "where i.order_id IS NULL\n" +
            "  and d.order_id IS NULL\n" +
            "group by o.id, o.date", nativeQuery = true)
    Set<Order> getOrdersWithoutInvoices();
}
