package com.example.thereddit.services;

import com.example.thereddit.models.Post;

import java.util.List;

public interface PostService {

    void getDefaultPosts ();
    List<Post> getAllPosts ();
    void savePost (String name, Post post);
    void raiseRating (String name, Integer id);
    void lowerRating (String name, Integer id);
    List<Post> get10TopPosts ();
    String correctRedirect (String name, Integer id);

}
