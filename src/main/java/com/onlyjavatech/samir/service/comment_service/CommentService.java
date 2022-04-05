package com.onlyjavatech.samir.service.comment_service;

import com.onlyjavatech.samir.model.commnet_model.Comment;
import com.onlyjavatech.samir.model.commnet_model.CommentRequestModel;
import com.onlyjavatech.samir.model.commnet_model.CommentResponseModel;
import com.onlyjavatech.samir.repository.comment_repository.CommentRepository;
import com.onlyjavatech.samir.service.project_service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostService postService;

    public CommentResponseModel addComment(CommentRequestModel request) {
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        Comment comment = new Comment();
        comment.setId(id);
        comment.setCommentMessage(request.getCommentMessage());
        comment.setPost(postService.getPostByPostId(request.getPostId()));

        return setCommentResponseModel(commentRepository.save(comment));
    }

    public CommentResponseModel getCommentById(String id) {
        return setCommentResponseModel(commentRepository.findById(id).get());
    }

    public List<CommentResponseModel> getComments() {
        Iterable<Comment> comments = commentRepository.findAll();
        List<CommentResponseModel> commentResponseModelList = new ArrayList<>();
        comments.forEach(comment -> {
            commentResponseModelList.add(setCommentResponseModel(comment));
        });
        return commentResponseModelList;
    }

    public CommentResponseModel updateComment(CommentRequestModel request) {
        Comment comment = commentRepository.findById(request.getId()).get();
        comment.setCommentMessage(request.getCommentMessage());
        return setCommentResponseModel(commentRepository.save(comment));
    }

    public void removeComment(String id) {
        commentRepository.deleteById(id);
    }

    private CommentResponseModel setCommentResponseModel(Comment request) {
        CommentResponseModel response = new CommentResponseModel();
        response.setId(request.getId());
        response.setCommentMessage(request.getCommentMessage());
        return response;
    }
}
