package uz.jamshid.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.jamshid.apelsin.entity.Payment;

import javax.persistence.Tuple;
import java.util.Set;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Query(value = "select i.id, sum(p.amount) - i.amount as reimburse_amount\n" +
            "from payment p\n" +
            "         join invoice i on p.invoice_id = i.id\n" +
            "group by i.id\n" +
            "HAVING sum(p.amount - i.amount) != 0", nativeQuery = true)
    Set<Tuple> getOverpaidInvoices();
}
