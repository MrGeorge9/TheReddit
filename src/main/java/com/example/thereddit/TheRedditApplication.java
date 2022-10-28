package com.example.thereddit;

import com.example.thereddit.services.PostServiceIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TheRedditApplication implements CommandLineRunner {

    private PostServiceIml postServiceIML;

    @Autowired
    public TheRedditApplication(PostServiceIml postServiceIML) {
        this.postServiceIML = postServiceIML;
    }

    public static void main(String[] args) {
        SpringApplication.run(TheRedditApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //postServiceIML.getDefaultPosts();
    }
}
