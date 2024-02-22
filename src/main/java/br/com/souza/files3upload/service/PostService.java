package br.com.souza.files3upload.service;

import br.com.souza.files3upload.database.model.Post;
import br.com.souza.files3upload.database.repository.IPostRepository;
import br.com.souza.files3upload.dto.PostResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    public List<PostResponse> findAll() throws Exception {
        List<PostResponse> response = new ArrayList<>();
        for(Post post : iPostRepository.findAll()){
            response.add(new PostResponse(post, s3Service));
        }

        return response;
    }
}
