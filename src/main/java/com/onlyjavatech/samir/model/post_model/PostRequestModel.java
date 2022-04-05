package com.onlyjavatech.samir.model.post_model;

import com.onlyjavatech.samir.model.commnet_model.Comment;

import java.util.List;

public class PostRequestModel {
    private String id;
    private String postName;
    private List<Comment> commentList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
