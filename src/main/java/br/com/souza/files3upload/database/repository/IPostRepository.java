package br.com.souza.files3upload.database.repository;

import br.com.souza.files3upload.database.model.Post;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepository extends MongoRepository<Post, UUID> {
}
