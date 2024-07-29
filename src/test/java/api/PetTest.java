package api;

import com.petstore.common.config.RestClientConfig;
import com.petstore.pet.PetObjectFactory;
import com.petstore.pet.PetService;
import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class PetTest extends IntegrationTest {
    @Autowired
    private PetService petService;
    @Autowired
    private RestClientConfig restClientConfig;
    @Autowired
    private PetObjectFactory petObjectFactory;

    @Test
    @Tag("smoke")
    @Description("Test creates a POST request to '/pet' EP and asserts that the response is equal to the request")
    public void postPetShouldResponseWith200CodeTest() {
        log.info("postPetShouldResponseWith200CodeTest started...");
        var uuid = UUID.randomUUID().toString();

        int responseCode = 200;

        // creating a request
        var request = petObjectFactory.getPetRequest();

        var response = petService.postPet200s(request, responseCode, uuid);


        assertNotNull(response);
        assertTrue(response.id() > 0);

        // setting 'response.id' to '0' because the EP responses with a random id's value; other fields keep the same
        request.setId(response.id());
        assertTrue(response.isEqualTo(request));
    }

    @Test
    @Tag("smoke")
    @Description("Test creates a POST request to '/pet' EP with incorrect value and asserts that the response has an error status")
    public void postPetShouldResponseWith500CodeTest() {
        log.info("postPetShouldResponseWith500CodeTest started...");
        var uuid = UUID.randomUUID().toString();

        int responseCode = 500;

        // creating a request
        var request = petObjectFactory.getPetRequest().setId("asd");

        var response = petService.postPet500s(request, responseCode, uuid);


        assertNotNull(response);
        assertEquals(responseCode, response.code());
        assertEquals("something bad happened", response.message());
    }


    @Test
    @Tag("smoke")
    @Description("Test creates a POST request to '/pet/{petId}/uploadImage' EP and asserts the successfull response")
    public void uploadImageShouldResponseWith200CodeTest() {
        log.info("uploadImageShouldResponseWith200CodeTest started...");
        var uuid = UUID.randomUUID().toString();

        int responseCode = 200;

        // creating a request
        var request = petObjectFactory.getPetRequest();

        var response = petService.postPet200s(request, responseCode, uuid);

        var filePath = "src/test/resources/picture.jpeg";

        var uploadImageResp = petService.uploadImage(com.petstore.pet.petId.uploadImage.RequestData.builder()
                                                         .petId(response.id())
                                                         .file(new File(filePath))
                                                         .build(), responseCode, uuid);

        assertTrue(uploadImageResp.message().contains("uploaded"));
    }


    @Test
    @Tag("smoke")
    @Description("Test creates a PUT request to '/pet' EP and asserts that the response is equal to the request")
    public void putPetShouldResponseWith200CodeTest() {
        log.info("postPetShouldResponseWith200CodeTest started...");
        var uuid = UUID.randomUUID().toString();

        int responseCode = 200;

        // creating a request
        var request = petObjectFactory.getPetRequest();

        var response = petService.putPet200s(request, responseCode, uuid);


        assertNotNull(response);
        assertTrue(response.id() > 0);

        // setting 'response.id' to '0' because the EP responses with a random id's value; other fields keep the same
        request.setId(response.id());
        assertTrue(response.isEqualTo(request));
    }

    @Test
    @Tag("smoke")
    @Description("Test creates a PUT request to '/pet' EP and asserts 200 response code")
    public void putPetShouldResponseWith500CodeTest() {
        log.info("putPetShouldResponseWith400CodeTest started...");
        var uuid = UUID.randomUUID().toString();

        int responseCode = 500;

        // creating a request
        var request = petObjectFactory.getPetRequest();
        request.setId("asd");

        var response = petService.putPet500s(request, responseCode, uuid);


        assertNotNull(response);
    }
}
