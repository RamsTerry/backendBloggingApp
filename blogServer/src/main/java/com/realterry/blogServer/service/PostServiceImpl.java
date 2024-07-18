package com.realterry.blogServer.service;

import com.realterry.blogServer.entity.Post;
import com.realterry.blogServer.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostRepository postRepository;

    public Post savePost(Post post){
       post.setLikeCount(0);
       post.setViewCount(0);
       post.setDate(new Date());
       return postRepository.save(post);
    }

    public List<Post> getAllPosts(){
      return postRepository.findAll();
    }

    public Post getPostById(Long postId){
        Optional<Post> optionalPost=postRepository.findById(postId);
        if(optionalPost.isPresent()){
            Post post=optionalPost.get();
            post.setViewCount(post.getViewCount()+1);
            return postRepository.save(post);
        }else{
            throw new EntityNotFoundException("Post not found");
        }
    }

    public void likePost(Long postId){
        Optional<Post> optionalPost=postRepository.findById(postId);
        if(optionalPost.isPresent()){
            Post post=optionalPost.get();
            post.setLikeCount(post.getLikeCount()+1);
            postRepository.save(post);
        }else{
            throw new EntityNotFoundException("Post not found with id: " +postId);
        }
    }

    public List<Post> searchByName(String name){
        return postRepository.findAllByNameContaining(name);
    }
}
