package com.realterry.blogServer.service;

import com.realterry.blogServer.entity.Comment;

import java.util.List;

public interface CommentService {
    public Comment createComment(Long postId, String postedBy, String content);

    public List<Comment> getAllCommentByPostId(Long postId);
}
