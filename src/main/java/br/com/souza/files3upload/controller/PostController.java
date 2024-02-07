package br.com.souza.files3upload.controller;

import br.com.souza.files3upload.dto.PostResponse;
import br.com.souza.files3upload.service.PostService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/v1/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Void> createNewPost(@RequestPart(value = "message") String message,
                                              @RequestPart(value = "file") MultipartFile file) throws Exception {
        postService.createNewPost(message, file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostResponse> findById(@PathVariable("uuid") String uuid) throws Exception {
        return new ResponseEntity<>(postService.findById(uuid), HttpStatus.OK);
    }
}
