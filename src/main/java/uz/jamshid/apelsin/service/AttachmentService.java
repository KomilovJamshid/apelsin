package uz.jamshid.apelsin.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.jamshid.apelsin.entity.Attachment;
import uz.jamshid.apelsin.entity.AttachmentContent;
import uz.jamshid.apelsin.payload.ApiResponse;
import uz.jamshid.apelsin.repository.AttachmentContentRepository;
import uz.jamshid.apelsin.repository.AttachmentRepository;

import java.util.Iterator;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @SneakyThrows
    public ApiResponse upload(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        Attachment attachment = new Attachment(null, file.getOriginalFilename(), file.getSize(), file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment);

        AttachmentContent attachmentContent = new AttachmentContent(null, file.getBytes(), savedAttachment);
        attachmentContentRepository.save(attachmentContent);

        return new ApiResponse("Photo uploaded", true, savedAttachment.getId());
    }
}
