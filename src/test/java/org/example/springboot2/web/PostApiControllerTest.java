package org.example.springboot2.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.jni.Local;
import org.example.springboot2.domain.posts.Posts;
import org.example.springboot2.domain.posts.PostsRepository;
import org.example.springboot2.web.dto.PostsSaveRequestDto;
import org.example.springboot2.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @After
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void posts_register() throws Exception {
        String title = "title111";
        String content = "content222";
        String author = "author333";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        String url = "http://localhost:" + this.port + "/api/v1/posts";

        /*
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        */

        ResultActions resultActions = mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)));

        resultActions.andExpect(status().isOk())
                .andDo(print());

        List<Posts> posts = postsRepository.findAll();

        assertThat(posts.get(0).getTitle()).isEqualTo(title);
        assertThat(posts.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void posts_update() throws Exception {
        String title = "title";
        String content = "content";
        String author = "author";

        Posts posts = Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        Posts savedPosts = postsRepository.save(posts);

        Long updateId = savedPosts.getId();
        String updateTitle = "updateTitle";
        String updateContent = "updateContent";
        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(updateTitle)
                .content(updateContent)
                .build();

        String url = "http://localhost:" + this.port + "/api/v1/posts/" + updateId;

        ResultActions resultActions = mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)));

        resultActions.andExpect(status().isOk())
                .andDo(print());

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(updateTitle);
        assertThat(all.get(0).getContent()).isEqualTo(updateContent);
    }

    @Test
    public void posts_getItem() throws Exception {
        Posts saved_posts = save_posts();

        String url = "http://localhost:" + this.port + "/api/v1/posts/" + saved_posts.getId();

        ResultActions resultActions = mvc.perform(get(url).contentType(MediaType.APPLICATION_JSON_UTF8));

        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.title", is(saved_posts.getTitle())))
                .andExpect(jsonPath("$.content", is(saved_posts.getContent())))
                .andExpect(jsonPath("$.author", is(saved_posts.getAuthor())));
    }

    public Posts save_posts() {
        String title = "title333";
        String content = "content333";
        String author = "author333";

        Posts posts = Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        Posts savedPosts = postsRepository.save(posts);

        return savedPosts;
    }

    @Test
    public void BaseTimeEntity_register() {
        LocalDateTime now = LocalDateTime.of(2020,04,22, 0, 0, 0);
        Posts posts = Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build();

        System.out.println(now);
        postsRepository.save(posts);

        List<Posts> postList = postsRepository.findAll();
        Posts savedPost = postList.get(0);

        System.out.println("=======================");
        System.out.println(savedPost.getCreatedDate());
        System.out.println(savedPost.getCreatedBy());
        System.out.println(savedPost.getModifiedDate());
        System.out.println(savedPost.getLastModifiedBy());
        System.out.println("=======================");

        assertThat(savedPost.getCreatedDate()).isAfter(now);
        assertThat(savedPost.getModifiedDate()).isAfter(now);
    }

}
