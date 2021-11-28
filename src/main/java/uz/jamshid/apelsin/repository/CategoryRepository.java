package uz.jamshid.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.jamshid.apelsin.entity.Category;

import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "select *\n" +
            "from category c\n" +
            "         join product p on c.id = p.category_id\n" +
            "where p.id=:product_id", nativeQuery = true)
    Set<Category> getCategoryByProductId(Integer product_id);
}
