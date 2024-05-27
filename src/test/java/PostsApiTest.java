import com.salmon.TestApplication;
import com.salmon.common.config.RestClientConfig;
import com.typicode.jsonPlaceholder.api.posts.PostsService;
import com.typicode.jsonPlaceholder.api.posts.get.Post;
import io.qameta.allure.Description;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;


@Slf4j
@SpringBootTest(classes = TestApplication.class)
public class PostsApiTest {
    @Autowired
    private PostsService postsService;
    @Autowired
    private RestClientConfig restClientConfig;

    @Test
    @Description("The test gets all posts and extracts the TOP 10 most frequently occurring words.")
    public void shouldFindTop10FrequentlyOccurringWordsFromPostsTest() {
        log.info("shouldFindTop10FrequentlyOccurringWordsFromPostsTest started...");

        // unique for each test
        var uuid = UUID.randomUUID().toString();

        var postArray = postsService.getPosts200(200, uuid);

        var text = "";

        // concats Strings into a text without StringBuilder (https://docs.oracle.com/javase/specs/jls/se8/html/jls-15.html#jls-15.18.1)
        for (Post p : postArray) {
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
    @Description("Test creates a request to ")
    public void getPostsShouldResponseWithNonEmptyPostListTest() {
        log.info("getPostsShouldResponseWithNonEmptyPostListTest started...");

        var uuid = UUID.randomUUID().toString();

        int code = 200;

        var response = postsService.getPosts200(code, uuid);

        assertTrue(response.length > 0);
    }
}
