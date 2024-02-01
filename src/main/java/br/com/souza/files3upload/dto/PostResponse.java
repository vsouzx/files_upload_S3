package br.com.souza.files3upload.dto;

import br.com.souza.files3upload.database.model.Post;
import br.com.souza.files3upload.service.S3Service;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

    private UUID id;
    private String message;
    private LocalDateTime publicationTime;
    private byte[] fileBytes;

    public PostResponse(Post post, S3Service s3Service) throws Exception {
        this.id = post.getId();
        this.message = post.getMessage();
        this.publicationTime = post.getPublicationTime();
        this.fileBytes = s3Service.downloadFileFromS3(post.getFileName());
    }
}
