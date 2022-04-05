package com.onlyjavatech.samir.model.post_model;

import javax.persistence.Column;
import javax.persistence.Id;

public class PostResponseModel {
    private String id;
    private String postName;

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
}
