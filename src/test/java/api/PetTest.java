package api;

import com.petstore.common.TestApplication;
import com.petstore.common.config.RestClientConfig;
import com.petstore.pet.PetService;
import com.petstore.pet.post.CategoryData;
import com.petstore.pet.post.PostResponse200sRec;
import com.petstore.pet.post.RequestData;
import com.petstore.pet.post.TagData;
import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class PetTest extends IntegrationTest {
    @Autowired
    private PetService petService;
    @Autowired
    RestClientConfig restClientConfig;


    @Test
    @Tag("smoke")
    @Description("Test creates a POST request to '/pet' EP and asserts that the response is equal to the request")
    public void postPetShouldResponseWith200CodeTest() {
        log.info("postPetShouldResponseWith200CodeTest started...");
        var uuid = UUID.randomUUID().toString();

        int responseCode = 200;

        // creating a request
        var request = RequestData.builder()
            .id(0L)
            .category(CategoryData.builder()
                          .id(0L)
                          .name("string")
                          .build())
            .name("doggie")
            .photoUrls(List.of("url"))
            .tags(List.of(TagData.builder()
                              .id(0L)
                              .name("string")
                              .build()))
            .status("available")
            .build();

        var response = petService.postPet200s(request, responseCode, uuid);


        assertNotNull(response);
        assertTrue(response.id() > 0);

        // setting 'response.id' to '0' because the EP responses with a random id's value; other fields keep the same
        request.setId(response.id());
        assertTrue(response.isEqualTo(request));
    }

    @Test
    @Tag("smoke")
    @Description("Test creates a POST request to '/pet' EP and asserts that the response not empty")
    public void postPetShouldResponseWith405CodeTest() {
        log.info("postPetShouldResponseWith405CodeTest started...");
        var uuid = UUID.randomUUID().toString();

        int responseCode = 200;

        var response = petService.postPet200s(RequestData.builder().build(), responseCode, uuid);


        // setting 'response.id' to '0' because the EP responses with a random id's value; other fields keep the same
//        request.setId(response.id());
//        assertTrue(response.isEqualTo(request));
    }


}
