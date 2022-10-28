package com.example.thereddit.repositories;

import com.example.thereddit.models.Post;
import com.example.thereddit.models.User;
import com.example.thereddit.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository <Vote, Integer> {
    Vote findByUserEqualsAndPostEquals (User user, Post post);
}
