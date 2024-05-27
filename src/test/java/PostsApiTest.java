import com.salmon.TestApplication;
import com.salmon.common.config.RestClientConfig;
import com.typicode.jsonPlaceholder.api.posts.PostsService;
import com.typicode.jsonPlaceholder.api.posts.get.PostRec;
import io.qameta.allure.Description;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest(classes = TestApplication.class)
public class PostsApiTest {
    @Autowired
    private PostsService postsService;
    @Autowired
    private RestClientConfig restClientConfig;


    @Test
    @Tag("integrationAPI")
    @Epic("SalmonEpic")
    @Feature("SalmonFeature")
    @Story("SalmonStory")
    @Description("The test gets all posts and extracts the TOP 10 most frequently occurring words.")
    public void shouldFindTop10FrequentlyOccurringWordsFromPostsTest() {
        log.info("shouldFindTop10FrequentlyOccurringWordsFromPostsTest started...");
        // unique for each test
        var uuid = UUID.randomUUID().toString();

        var responseCode = 200;

        var postsArray = postsService.getPosts200(responseCode, uuid);

        var text = "";

        // concats Strings into a text without StringBuilder (https://docs.oracle.com/javase/specs/jls/se8/html/jls-15.html#jls-15.18.1)
        for (PostRec p : postsArray) {
            text += p.body() + " " + p.title() + " ";
        }

        // converting to a words array
        String[] words = text.split("\\s+");

        var table = new HashMap<String, Integer>();

        // iterating throw the array and adding words to the HashMap, counting them
        for (String word : words) {
            var uniqueWord = word.toLowerCase();

            if (table.containsKey(uniqueWord)) {
                table.replace(uniqueWord, table.get(uniqueWord),
                              table.get(uniqueWord) + 1);
            } else {
                table.put(uniqueWord, 1);
            }
        }

        Comparator<Map.Entry<String, Integer>> comparator = Map.Entry.comparingByValue();

        log.info("TOP-10 words: ");

        // sorting words by their amount, sending TOP-10 of them to the log
        table
            .entrySet()
            .stream()
            .sorted(comparator.reversed())
            .limit(10)
            .forEach(entry -> log.info("{} {}", entry.getKey(), entry.getValue()));
    }

    @Test
    @Tag("integrationAPI")
    @Epic("SalmonEpic")
    @Feature("SalmonFeature")
    @Story("SalmonStory")
    @Description("Test creates a request to '/posts' EP and asserts that the response not empty")
    public void getPostsShouldResponseWithNonEmptyPostListTest() {
        log.info("getPostsShouldResponseWithNonEmptyPostListTest started...");
        var uuid = UUID.randomUUID().toString();

        int responseCode = 200;

        var response = postsService.getPosts200(responseCode, uuid);

        assertTrue(response.length > 0);
    }

    @Test
    @Tag("integrationAPI")
    @Epic("SalmonEpic")
    @Feature("SalmonFeature")
    @Story("SalmonStory")
    @Description("The test creates a GET request to '/posts/{number}' and asserts that the post contains accurate information")
    public void getPostShouldGetAPostTest() {
        log.info("getPostShouldGetAPostTest started...");
        var uuid = UUID.randomUUID().toString();

        int responseCode = 200;

        // requesting an exact post
        var response = postsService.getPost200(1, responseCode, uuid);

        assertEquals("1", response.id());
        assertEquals("1", response.userId());
        assertEquals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", response.title());
        assertTrue(response.body().contains("recusandae consequuntur"));
    }

    @Test
    @Tag("integrationAPI")
    @Epic("SalmonEpic")
    @Feature("SalmonFeature")
    @Story("SalmonStory")
    @Description("The test creates a GET request to '/posts/{postId}/comments' and asserts that the post contains comments")
    public void getPostCommentsShouldGetAllPostCommentsTest() {
        log.info("getPostCommentsShouldGetAllPostCommentsTest started...");
        var uuid = UUID.randomUUID().toString();

        int responseCode = 200;

        // requesting post comments
        var response = postsService.getPostComments200(1, responseCode, uuid);

        // asserts that the post has an accurate amount of comments
        assertEquals(5, response.length);
    }

    @Test
    @Tag("integrationAPI")
    @Epic("SalmonEpic")
    @Feature("SalmonFeature")
    @Story("SalmonStory")
    @Description("The test creates a GET request to '/comments?postId={postId}' and asserts that the post contains comments")
    public void getCommentsByPostIdShouldGetAllPostCommentsTest() {
        log.info("getCommentsByPostIdShouldGetAllPostCommentsTest started...");
        var uuid = UUID.randomUUID().toString();

        int responseCode = 200;

        // requesting comments by 'postId'
        var response = postsService.getCommentByPostId200(1, responseCode, uuid);

        // asserts that the post has an accurate amount of comments
        assertEquals(5, response.length);
    }

    @Test
    @Tag("integrationAPI")
    @Epic("SalmonEpic")
    @Feature("SalmonFeature")
    @Story("SalmonStory")
    @Description("Test creates GET requests to '/comments?postId={postId}' and '/posts/{postId}/comments' and compares the responses")
    public void getCommentsByPostIdResponseShouldBeEqualToGetPostCommentsTest() {
        log.info("getCommentsByPostIdResponseShouldBeEqualToGetPostCommentsTest started...");
        var uuid = UUID.randomUUID().toString();

        int responseCode = 200;

        // requesting both EPs
        var getCommentByPostIdResponse = postsService.getCommentByPostId200(1, responseCode, uuid);
        var getPostCommentsResponse = postsService.getPostComments200(1, responseCode, uuid);

        // assert that responses has equal lengths
        assertEquals(getCommentByPostIdResponse.length, getPostCommentsResponse.length);

        // assert that response elements equal to each other
        for (int i = 0; i < getCommentByPostIdResponse.length; i++) {
            assertEquals(getPostCommentsResponse[i], getCommentByPostIdResponse[i]);
        }
    }
}
