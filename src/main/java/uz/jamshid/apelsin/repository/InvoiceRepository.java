package uz.jamshid.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.jamshid.apelsin.entity.Invoice;

import java.util.Set;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    @Query(value = "select * from invoice where issued > due", nativeQuery = true)
    Set<Invoice> getExpiredInvoices();

    @Query(value = "select i.id, i.issued, i.amount, i.due, order_id, o.date\n" +
            "from invoice i\n" +
            "         join orders o on i.order_id = o.id\n" +
            "where issued < o.date", nativeQuery = true)
    Set<Invoice> getWrongDateInvoice();

    @Query(value = "select p.invoice_id, sum(p.amount) as total_payment, i.amount, sum(p.amount - i.amount) as reumberse_amount\n" +
            "from invoice i\n" +
            "         join payment p on i.id = p.invoice_id\n" +
            "GROUP BY p.invoice_id, i.amount", nativeQuery = true)
    Set<Invoice> getOverpaidInvoices();
}
