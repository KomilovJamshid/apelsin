package uz.jamshid.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.jamshid.apelsin.entity.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}
