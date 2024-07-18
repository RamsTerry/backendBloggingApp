package com.realterry.blogServer.controller;

import com.realterry.blogServer.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "*")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("comments/create")
    public ResponseEntity<?> createPost(@RequestParam Long postId,@RequestParam String postedBy,@RequestBody String content){
        try{
           return ResponseEntity.ok(commentService.createComment(postId,postedBy,content));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

    @GetMapping("comments/{postId}")
    public ResponseEntity<?>getCommentsByPostId(@PathVariable Long postId){
        try{
            return ResponseEntity.ok(commentService.getAllCommentByPostId(postId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }
}
