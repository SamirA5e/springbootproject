package com.onlyjavatech.samir.controller.comment_controller;

import com.onlyjavatech.samir.model.commnet_model.CommentRequestModel;
import com.onlyjavatech.samir.model.commnet_model.CommentResponseModel;
import com.onlyjavatech.samir.service.comment_service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping()
    public CommentResponseModel addComment(@RequestBody CommentRequestModel comment){
        return commentService.addComment(comment);
    }

    @GetMapping("{comment-id}")
    public CommentResponseModel getCommentById(@PathVariable(value = "comment-id") String id){
        return commentService.getCommentById(id);
    }

    @GetMapping()
    public List<CommentResponseModel> getComments(){
        return commentService.getComments();
    }

    @PutMapping()
    public CommentResponseModel updateComment(@RequestBody CommentRequestModel request){
        return commentService.updateComment(request);
    }

    @DeleteMapping("/{comment-id}")
    public void removeComment(@PathVariable(value = "comment-id") String id){
        commentService.removeComment(id);
    }
}
