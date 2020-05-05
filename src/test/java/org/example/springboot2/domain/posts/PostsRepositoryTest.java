package org.example.springboot2.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void saveGetList() {
        String title = "타이틀";
        String content = "컨텐트";
        String author = "작성자";

        Posts posts = Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        postsRepository.save(posts);

        List<Posts> postsList = postsRepository.findAll();
        Posts posts0 = postsList.get(0);

        assertThat(posts0.getTitle()).isEqualTo(title);
        assertThat(posts0.getContent()).isEqualTo(content);
        assertThat(posts0.getAuthor()).isEqualTo(author);

    }
}
