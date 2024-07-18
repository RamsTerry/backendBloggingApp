package com.realterry.blogServer.service;

import com.realterry.blogServer.entity.Post;

import java.util.List;

public interface PostService {
    public Post savePost(Post post);

    public List<Post> getAllPosts();

    public Post getPostById(Long postId);

    public void likePost(Long postId);

    public List<Post> searchByName(String name);
}
