package uz.jamshid.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.jamshid.apelsin.entity.Product;

import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select d.product_id, sum(d.quantity) as total_quantity\n" +
            "from product p\n" +
            "         join detail d on p.id = d.product_id\n" +
            "GROUP BY d.product_id\n" +
            "HAVING sum(d.quantity) > 10", nativeQuery = true)
    Set<Product> getHighDemandProduct();
}
