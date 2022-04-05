package com.onlyjavatech.samir.model.commnet_model;

import com.onlyjavatech.samir.model.post_model.Post;

public class CommentResponseModel {
    private String id;
    private String commentMessage;
    private Post post;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommentMessage() {
        return commentMessage;
    }

    public void setCommentMessage(String commentMessage) {
        this.commentMessage = commentMessage;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
