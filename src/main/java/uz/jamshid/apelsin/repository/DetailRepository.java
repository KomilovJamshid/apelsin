package uz.jamshid.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.jamshid.apelsin.entity.Detail;

import javax.persistence.Tuple;
import java.util.Set;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Integer> {

    @Query(value = "select p.id, sum(d.quantity)\n" +
            "from product p\n" +
            "         join detail d on p.id = d.product_id\n" +
            "group by p.id\n" +
            "HAVING sum(d.quantity) > 10", nativeQuery = true)
    Set<Tuple> getHighDemandProduct();

    @Query(value = "select *\n" +
            "from product p\n" +
            "        join detail d on p.id = d.product_id\n" +
            "where d.quantity >= 8", nativeQuery = true)
    Set<Detail> getBulkProducts();

    @Query(value = "select o.id, o.date, sum(quantity * p.price)\n" +
            "from orders o\n" +
            "         left join invoice i on o.id = i.order_id\n" +
            "         left join detail d on o.id = d.order_id\n" +
            "         join product p on p.id = d.product_id\n" +
            "where i.order_id IS NULL\n" +
            "  and d.order_id is not null\n" +
            "group by o.id, o.date", nativeQuery = true)
    Set<Tuple> getOrdersWithoutInvoices();

    Detail findByOrderId(Integer order_id);
}
