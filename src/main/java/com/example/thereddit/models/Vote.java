package com.example.thereddit.models;

import javax.persistence.*;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer voting;

    @ManyToOne
    private Post post;
    @ManyToOne
    private User user;

    public Vote() {
        this.voting = 0;
    }

    public Vote(Post post, User user) {
        this.post = post;
        this.user = user;
        this.voting = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVoting() {
        return voting;
    }

    public void setVoting(Integer voting) {
        this.voting = voting;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
