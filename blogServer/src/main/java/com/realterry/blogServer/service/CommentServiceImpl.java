package com.realterry.blogServer.service;

import com.realterry.blogServer.entity.Comment;
import com.realterry.blogServer.entity.Post;
import com.realterry.blogServer.repository.CommentRepository;
import com.realterry.blogServer.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    public Comment createComment(Long postId,String postedBy,String content){
        Optional<Post>optionalPost=postRepository.findById(postId);

        if(optionalPost.isPresent()){
            Comment comment=new Comment();
            comment.setPost(optionalPost.get());
            comment.setContent(content);
            comment.setPostedBy(postedBy);
            comment.setCreatedAt(new Date());

            return commentRepository.save(comment);
        }
        throw new EntityNotFoundException("Post not found");
    }

    public List<Comment> getAllCommentByPostId(Long postId){
        return commentRepository.findByPostId(postId);
    }
}
