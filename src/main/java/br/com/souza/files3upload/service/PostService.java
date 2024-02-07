package br.com.souza.files3upload.service;

import br.com.souza.files3upload.database.model.Post;
import br.com.souza.files3upload.database.repository.IPostRepository;
import br.com.souza.files3upload.dto.PostResponse;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class PostService {

    private final S3Service s3Service;
    private final IPostRepository iPostRepository;

    public PostService(S3Service s3Service,
                       IPostRepository iPostRepository) {
        this.s3Service = s3Service;
        this.iPostRepository = iPostRepository;
    }

    @Transactional
    public void createNewPost(String message, MultipartFile file) throws Exception {
        UUID uuid = UUID.randomUUID();
        String fileName = file != null && !file.isEmpty() ? uuid + file.getOriginalFilename() : null;

        iPostRepository.save(Post.builder()
                .id(uuid)
                .message(message)
                .fileName(fileName)
                .publicationTime(LocalDateTime.now())
                .build());

        if (fileName != null) {
            s3Service.uploadFileIntoS3(file, fileName);
        }
    }

    public PostResponse findById(String uuid) throws Exception {
        Post post = iPostRepository.findById(UUID.fromString(uuid))
                .orElseThrow(Exception::new);

        return new PostResponse(post, s3Service);
    }
}
