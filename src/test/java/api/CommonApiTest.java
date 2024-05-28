package api;

import com.salmon.TestApplication;
import com.salmon.common.config.RestClientConfig;
import com.typicode.jsonPlaceholder.api.posts.PostsService;
import com.typicode.jsonPlaceholder.api.posts.get.PostRec200s;
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
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.fail;


@Slf4j
@SpringBootTest(classes = TestApplication.class)
public class CommonApiTest {
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

        var postsArray = Optional.ofNullable(postsService.getPosts200s(responseCode, uuid));

        var text = "";

        if (postsArray.isPresent()) {
            // concats Strings into a text without StringBuilder (https://docs.oracle.com/javase/specs/jls/se8/html/jls-15.html#jls-15.18.1)
            for (PostRec200s p : postsArray.get()) {
                text += p.body() + " " + p.title() + " ";
            }
        } else {
            fail("An empty 'GET /posts' response");
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
}
