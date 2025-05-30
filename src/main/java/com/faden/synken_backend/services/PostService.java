package com.faden.synken_backend.services;

import com.faden.synken_backend.dtos.PostRequestDTO;
import com.faden.synken_backend.dtos.PostResponseDTO;
import com.faden.synken_backend.exceptions.post.PostNotFoundException;
import com.faden.synken_backend.exceptions.user.UserNotFoundException;
import com.faden.synken_backend.models.Post;
import com.faden.synken_backend.models.User;
import com.faden.synken_backend.repositories.UserRepository;
import com.faden.synken_backend.repositories.PostRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    private Post getPost(UUID id) {
        return postRepository.findById(id).
                orElseThrow(() -> new PostNotFoundException("Post não encontrado via ID: " + id));
    }

    @Transactional
    public PostResponseDTO createPost(PostRequestDTO postRequestDTO) {

        LocalDateTime currentDate = LocalDateTime.now();

        User foundUser = userRepository.findByUsername(postRequestDTO.username()).
                orElseThrow(() -> new UserNotFoundException("Usuário não encontrado: " + postRequestDTO.username()));

        Post newPost = new Post();

        BeanUtils.copyProperties(postRequestDTO, newPost);
        newPost.setCreatedAt(currentDate);
        newPost.setUser(foundUser);

        postRepository.save(newPost);
        return new PostResponseDTO(newPost);
    }

    @Transactional
    public PostResponseDTO updatePost(UUID id, PostRequestDTO postRequestDTO) {

        LocalDateTime currentDate = LocalDateTime.now();
        Post postToBeUpdated = getPost(id);

        BeanUtils.copyProperties(postRequestDTO, postToBeUpdated);
        postToBeUpdated.setUpdatedAt(currentDate);

        Post updatedPost = postRepository.save(postToBeUpdated);
        return new PostResponseDTO(updatedPost);
    }

    @Transactional
    public void deletePost(UUID id) {

        Post foundPost = getPost(id);
        postRepository.delete(foundPost);
    }

    public PostResponseDTO getPostByID(UUID id) {

        Post foundPost = getPost(id);
        return new PostResponseDTO(foundPost);
    }

    public List<PostResponseDTO> getAllPosts() {

        List<Post> foundPosts = postRepository.findAll();
        return foundPosts.stream().map(post -> new PostResponseDTO(post)).toList();
    }
}