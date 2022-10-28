package com.example.thereddit.services;

import com.example.thereddit.models.Post;
import com.example.thereddit.models.User;
import com.example.thereddit.models.Vote;
import com.example.thereddit.repositories.PostRepository;
import com.example.thereddit.repositories.UserRepository;
import com.example.thereddit.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceIml implements PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;
    private VoteRepository voteRepository;

    @Autowired
    public PostServiceIml(PostRepository postRepository, UserRepository userRepository, VoteRepository voteRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
    }
    public void getDefaultPosts (){
        postRepository.save(new Post(40, "McDavid scored a hattrick and saved his team", "https://cdn.webnoviny.sk/sites/32/2020/08/blackhawks_oilers_hockey_89321-7ae541b0f3f34043a66c5724aa0e6aaf-scaled-1-676x451.jpg"));
        postRepository.save(new Post(14, "Winter is coming", "https://c.tadst.com/gfx/600x337/winter-lake.jpg?1"));
        postRepository.save(new Post(86, "Spider-man: No Way Home made it to the top 3 of marvel movies", "https://www.svethardware.cz/namisto-filmu-spider-man-no-way-home-ziskali-malware-neprohledli-stary-trik/56719/img/s2.jpeg"));
        postRepository.save(new Post(66, "CD project just announced a new project", "https://en.cdprojektred.com/wp-content/themes/evp-cdpr/img/cdpr-default.jpg"));
        postRepository.save(new Post(28, "Lets go fishing", "https://img.poki.com/cdn-cgi/image/quality=78,width=600,height=600,fit=cover,f=auto/4206da66a0e5deca9115d19a4bc0c63f.png"));
        postRepository.save(new Post(55, "Canada is a beautiful place", "https://cdn.theculturetrip.com/wp-content/uploads/2020/08/e1he4b.jpg"));
        postRepository.save(new Post(38, "Mars colonies on the rise", "https://cdn.mos.cms.futurecdn.net/AQh73c64EohNEyuNhcqsQf.jpg"));
        postRepository.save(new Post(99, "Microsoft and Apple merged together", "https://images.macrumors.com/t/01PJDK4giliGBSuyRJCCe_GrZqY=/2500x/article-new/2021/05/Apple-vs-Microsoft-feature.jpg"));
        postRepository.save(new Post(48, "Imagine dragons EU tour", "https://muzikercdn.com/uploads/products/4354/435452/main_fec81d8e.jpg"));
        postRepository.save(new Post(13, "Traveling magazine", "https://wttc.org/DesktopModules/MVC/NewsArticleList/images/141_20201013185512_Consumer%20Survey%20Finds%2070%20Percent%20of%20Travelers%20Plan%20to%20Holiday%20in%202021.jpg"));
        postRepository.save(new Post(71, "Fly to the moon", "https://media.nature.com/w700/magazine-assets/d41586-022-01252-7/d41586-022-01252-7_20373968.jpg"));
    }
    public List<Post> getAllPosts (){
        List<Post> sortedPosts = postRepository.findAll();
        sortedPosts.sort((o1, o2) -> o2.getRating()-o1.getRating());
        return sortedPosts;
    }

    public List<Post> get10TopPosts (){
        List<Post> all = postRepository.findAll();
        all.sort((o1, o2) -> o2.getRating()-o1.getRating());
        List<Post> top10 = new ArrayList<>();

        if (all.size()>10){
            for (int i = 0; i < 10; i++) {
                top10.add(all.get(i));
            }
            return top10;
        } else return all;
    }

    public void savePost (String name, Post post){
        User owner =  userRepository.findByName(name);
        post.setUser(owner);
        postRepository.save(post);
    }

    public void raiseRating (String name, Integer id){
        User cuurentUser =  userRepository.findByName(name);
        Post currentPost =  postRepository.findById(id).get();
        Vote currentVoting = voteRepository.findByUserEqualsAndPostEquals(cuurentUser,currentPost);

        if (currentVoting==null){
            voteRepository.save(new Vote(currentPost, cuurentUser));
        }

        currentVoting = voteRepository.findByUserEqualsAndPostEquals(cuurentUser,currentPost);

        if (currentVoting.getVoting()<1){
            Post post = postRepository.findById(id).get();
            post.setRating(post.getRating()+1);
            postRepository.save(post);

            currentVoting.setVoting(currentVoting.getVoting()+1);
            voteRepository.save(currentVoting);
        }
    }

    public void lowerRating (String name, Integer id){
        User cuurentUser =  userRepository.findByName(name);
        Post currentPost =  postRepository.findById(id).get();
        Vote currentVoting = voteRepository.findByUserEqualsAndPostEquals(cuurentUser,currentPost);

        if (currentVoting==null){
            voteRepository.save(new Vote(currentPost, cuurentUser));
        }

        currentVoting = voteRepository.findByUserEqualsAndPostEquals(cuurentUser,currentPost);

        if (currentVoting.getVoting()>-1){
            Post post = postRepository.findById(id).get();
            post.setRating(post.getRating()-1);
            postRepository.save(post);

            currentVoting.setVoting(currentVoting.getVoting()-1);
            voteRepository.save(currentVoting);
        }
    }

    public String correctRedirect (String name, Integer id){
        int position = 0;
        List<Post> all = postRepository.findAll();
        all.sort((o1, o2) -> o2.getRating()-o1.getRating());

        for (int i = 0; i < all.size(); i++) {
            if (postRepository.findById(id).get().equals(all.get(i))){
                position = i;
                if (position<10){
                    return "redirect:/mainPage/"+name+"#"+id;
                } else return "redirect:/allPosts/"+name+"#"+id;
            }
        }
        return "redirect:/mainPage/"+name;
    }

}
