package com.onlyjavatech.samir.service.project_service;

import com.onlyjavatech.samir.model.post_model.Post;
import com.onlyjavatech.samir.model.post_model.PostRequestModel;
import com.onlyjavatech.samir.model.post_model.PostResponseModel;
import com.onlyjavatech.samir.repository.post_repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public PostResponseModel addProject(PostRequestModel postRequest) {
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        Post post = new Post();
        post.setId(id);
        post.setPostName(postRequest.getPostName());
        post.getCommentList().forEach(comment -> {
            post.addComment(comment);
        });
        return setPostResponseModel(postRepository.save(post));
    }

    public List<PostResponseModel> getAllPosts(){
        Iterable<Post> posts = postRepository.findAll();
        List<PostResponseModel> postResponseModelList = new ArrayList<>();
        posts.forEach(post -> {
            postResponseModelList.add(setPostResponseModel(post));
        });
        return postResponseModelList;
    }

    public PostResponseModel getPostById(String id){

        return setPostResponseModel(postRepository.findById(id).get());
    }

    public Post getPostByPostId(String id){
        return postRepository.findById(id).get();
    }
    public PostResponseModel updatePost(PostRequestModel request){
        Post post = postRepository.findById(request.getId()).get();
        post.setPostName(request.getPostName());

        return setPostResponseModel(postRepository.save(post));
    }

    public void deletePost(String id){
        postRepository.deleteById(id);
    }

    private PostResponseModel setPostResponseModel(Post request){
        PostResponseModel response = new PostResponseModel();
        response.setId(request.getId());
        response.setPostName(request.getPostName());
        return response;
    }
}
