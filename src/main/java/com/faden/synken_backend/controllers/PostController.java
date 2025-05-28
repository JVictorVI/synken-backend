package com.faden.synken_backend.controllers;

import com.faden.synken_backend.dtos.PostRequestDTO;
import com.faden.synken_backend.dtos.PostResponseDTO;
import com.faden.synken_backend.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/post/new")
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody @Valid PostRequestDTO postRequestDTO) {
        PostResponseDTO newPost = postService.createPost(postRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
    }

    @PutMapping("/post/update/{id}")
    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable(value = "id") UUID id, @RequestBody @Valid PostRequestDTO postRequestDTO) {

        PostResponseDTO updatedPost = postService.updatePost(id, postRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPost);
    }

    @DeleteMapping("/post/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(value = "id") UUID id) {
        postService.deletePost(id);
        return ResponseEntity.status(HttpStatus.OK).body("Post deletado com sucesso.");
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<PostResponseDTO> getPost(@PathVariable(value = "id") UUID id) {

        PostResponseDTO foundPost = postService.getPostByID(id);
        return ResponseEntity.status(HttpStatus.OK).body(foundPost);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDTO>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

}
