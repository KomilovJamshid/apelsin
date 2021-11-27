package uz.jamshid.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.jamshid.apelsin.entity.Product;

import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
