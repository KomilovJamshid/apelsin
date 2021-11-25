package uz.jamshid.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.jamshid.apelsin.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
