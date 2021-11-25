package uz.jamshid.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.jamshid.apelsin.entity.Detail;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Integer> {
}
