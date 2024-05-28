package api;

import com.salmon.TestApplication;
import com.salmon.common.config.RestClientConfig;
import com.typicode.jsonPlaceholder.api.posts.PostsService;
import com.typicode.jsonPlaceholder.api.posts.post.Post;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;

@Slf4j
@SpringBootTest(classes = TestApplication.class)
public class PostPostsAPITest {
    @Autowired
    private PostsService postsService;
    @Autowired
    private RestClientConfig restClientConfig;


    @Test
    @Tag("integration")
    @Epic("SalmonEpic")
    @Feature("SalmonFeature")
    @Story("GetPostsApiTest")
    @Description("Test creates POST request to '/posts' EP and asserts that the response not empty")
    public void postPostsShouldAPostTest() {
        log.info("postPostsShouldAPostTest started...");
        var uuid = UUID.randomUUID().toString();

        int responseCode = 201;

        var response = postsService.postPost200s(Post.builder()
                                                 .title("foo")
                                                 .body("bar")
                                                 .userId(1)
                                                 .build(), responseCode, uuid);

        assertFalse(response.id().isEmpty());
    }

    @Test
    @Tag("integration")
    @Epic("SalmonEpic")
    @Feature("SalmonFeature")
    @Story("GetPostsApiTest")
    @Description("Test sends POST request to '/posts' with an empty body and asserts an error response")
    public void postPostsWithEmptyBodyShouldNotCreateAPostTest() {
        log.info("postPostsWithEmptyBodyShouldNotCreateAPostTest started...");
        var uuid = UUID.randomUUID().toString();

        int responseCode = 400;

        // POST /posts always responses with 201 code
        // Here should be postsService.postPost400s() method invocation and proper assert
    }

    @Test
    @Tag("integration")
    @Epic("SalmonEpic")
    @Feature("SalmonFeature")
    @Story("GetPostsApiTest")
    @Description("Test sends a POST request to '/posts' with no body and asserts an error response")
    public void postPostsWithNoBodyShouldNotCreateAPostTest() {
        log.info("postPostsWithNoBodyShouldNotCreateAPostTest started...");
        var uuid = UUID.randomUUID().toString();

        int responseCode = 400;

        // POST /posts always responses with 201 code
        // Here should be postsService.postPost400s() method invocation and proper assert
    }

    @AfterAll
    public static void tearDown() {
        // some tearDown actions here
    }
}
