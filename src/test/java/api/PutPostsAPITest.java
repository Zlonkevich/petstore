package api;

import com.salmon.TestApplication;
import com.salmon.common.config.RestClientConfig;
import com.typicode.jsonPlaceholder.api.posts.PostsService;
import com.typicode.jsonPlaceholder.api.posts.put.PostEntity;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest(classes = TestApplication.class)
public class PutPostsAPITest {
    @Autowired
    private PostsService postsService;
    @Autowired
    private RestClientConfig restClientConfig;

    @Test
    @Tag("integration")
    @Epic("SalmonEpic")
    @Feature("SalmonFeature")
    @Story("GetPostsApiTest")
    @Description("Test creates PUT request to '/posts/{postId}' EP and asserts that the post updated successfully")
    public void putPostsShouldUpdateAPostTest() {
        log.info("putPostsShouldUpdateAPostTest started...");
        var uuid = UUID.randomUUID().toString();

        // test params
        int responseCode = 200;

        var post = PostEntity.builder()
            .id(1)
            .title("foo")
            .body("bar")
            .userId(1)
            .build();

        var response = postsService.putPost200s(post, responseCode, uuid);

        // Documentation has an example of the EPs output, but the real one has a different structure
        assertTrue(response.isEqualTo(post), "And this is OK, because the server has a real problem with the response. See documentation");
    }

    @Test
    @Tag("integration")
    @Epic("SalmonEpic")
    @Feature("SalmonFeature")
    @Story("GetPostsApiTest")
    @Description("Test creates PUT request with incorrect 'id' value to '/posts/{postId}' EP and asserts response code 404")
    public void putPostsShouldNotUpdateAPostTest() {
        log.info("putPostsShouldNotUpdateAPostTest started...");
        var uuid = UUID.randomUUID().toString();
        log.info("Test will fail and this is OK, because the server responded with incorrect response code: 500 instead of 404");

        // test params
        int responseCode = 404;

        var post = PostEntity.builder()
            .id(0)
            .title("foo")
            .body("bar")
            .userId(1)
            .build();

        var response = postsService.putPost400s(post, responseCode, uuid);

        // some asserts
    }
}
