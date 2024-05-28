package api;

import com.salmon.TestApplication;
import com.salmon.common.config.RestClientConfig;
import com.typicode.jsonPlaceholder.api.posts.PostsService;
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
import org.springframework.test.context.event.annotation.AfterTestClass;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest(classes = TestApplication.class)
public class GetPostsAPITest {
    @Autowired
    private PostsService postsService;
    @Autowired
    private RestClientConfig restClientConfig;


    @Test
    @Tag("integration")
    @Epic("SalmonEpic")
    @Feature("SalmonFeature")
    @Story("GetPostsApiTest")
    @Description("Test creates a request to '/posts' EP and asserts that the response not empty")
    public void getPostsShouldResponseWithNonEmptyPostListTest() {
        log.info("getPostsShouldResponseWithNonEmptyPostListTest started...");
        var uuid = UUID.randomUUID().toString();

        int responseCode = 200;

        var response = postsService.getPosts200s(responseCode, uuid);

        assertTrue(response.length > 0);
    }

    @Test
    @Tag("integration")
    @Epic("SalmonEpic")
    @Feature("SalmonFeature")
    @Story("GetPostsApiTest")
    @Description("The test creates a GET request to '/posts/{number}' and asserts that the post contains accurate information")
    public void getPostShouldGetAPostTest() {
        log.info("getPostShouldGetAPostTest started...");
        var uuid = UUID.randomUUID().toString();

        int responseCode = 200;

        // requesting an exact post
        var response = postsService.getPost200s(1, responseCode, uuid);

        assertEquals("1", response.id());
        assertEquals("1", response.userId());
        assertEquals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", response.title());
        assertTrue(response.body().contains("recusandae consequuntur"));
    }

    @Test
    @Tag("integration")
    @Epic("SalmonEpic")
    @Feature("SalmonFeature")
    @Story("GetPostsApiTest")
    @Description("The test creates a GET request to '/posts/{number}' with invalid postId and asserts that response is empty")
    public void getPostWithInvalidPostIdShouldNotGetAPostTest() {
        log.info("getPostWithInvalidPostIdShouldNotGetAPostTest started...");
        var uuid = UUID.randomUUID().toString();

        int responseCode = 404;

        // requesting an exact post
        var response = postsService.getPost400s(0, responseCode, uuid);

        assertNull(response.title());
        assertNull(response.body());
        assertNull(response.id());
        assertNull(response.userId());
    }

    @Test
    @Tag("integration")
    @Epic("SalmonEpic")
    @Feature("SalmonFeature")
    @Story("GetPostsApiTest")
    @Description("The test creates a GET request to '/posts/{postId}/comments' and asserts that the post contains comments")
    public void getPostCommentsPathParamShouldGetAllPostCommentsTest() {
        log.info("getPostCommentsPathParamShouldGetAllPostCommentsTest started...");
        var uuid = UUID.randomUUID().toString();

        // test params
        int postId = 1;
        int postsAmount = 5;
        int responseCode = 200;

        // requesting post comments
        var response = postsService.getPostCommentsPathParam200s(postId, responseCode, uuid);

        // asserts that the post has an accurate amount of comments
        assertEquals(postsAmount, response.length);
    }

    @Test
    @Tag("integration")
    @Epic("SalmonEpic")
    @Feature("SalmonFeature")
    @Story("GetPostsApiTest")
    @Description("The test creates a GET request to '/comments?postId={postId}' and asserts that the post contains exact amount of comments")
    public void getPostCommentsQueryParamShouldGetAllPostCommentsTest() {
        log.info("getPostCommentsQueryParamShouldGetAllPostCommentsTest started...");
        var uuid = UUID.randomUUID().toString();

        // test params
        int postId = 1;
        int postsAmount = 5;
        int responseCode = 200;

        // requesting comments by 'postId'
        var response = postsService.getPostCommentsQueryParam200s(postId, responseCode, uuid);

        // asserts that the post has an accurate amount of comments
        assertEquals(postsAmount, response.length);
    }

    @Test
    @Tag("integration")
    @Epic("SalmonEpic")
    @Feature("SalmonFeature")
    @Story("GetPostsApiTest")
    @Description("Test creates GET requests to '/comments?postId={postId}' and '/posts/{postId}/comments' and compares the responses")
    public void getPostCommentsQueryParamResponseShouldBeEqualTogetPostCommentsPathParamTest() {
        log.info("getPostCommentsQueryParamResponseShouldBeEqualTogetPostCommentsPathParamTest started...");
        var uuid = UUID.randomUUID().toString();

        // test params
        int postId = 1;
        int responseCode = 200;

        // requesting both EPs
        var getCommentByPostIdResponse = postsService.getPostCommentsQueryParam200s(postId, responseCode, uuid);
        var getPostCommentsResponse = postsService.getPostCommentsPathParam200s(postId, responseCode, uuid);

        // assert that responses has equal lengths
        assertEquals(getCommentByPostIdResponse.length, getPostCommentsResponse.length, "Response lengths not equal to each other");

        // assert that response elements equal to each other
        for (int i = 0; i < getCommentByPostIdResponse.length; i++) {
            assertEquals(getPostCommentsResponse[i], getCommentByPostIdResponse[i]);
        }


    }

    @AfterAll
    public static void tearDown() {
        // some tearDown actions here
    }


}
