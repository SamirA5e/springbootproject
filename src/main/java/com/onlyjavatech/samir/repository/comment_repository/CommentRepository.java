package com.onlyjavatech.samir.repository.comment_repository;

import com.onlyjavatech.samir.model.commnet_model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, String> {
}
