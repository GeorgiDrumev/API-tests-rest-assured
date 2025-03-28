import io.restassured.RestAssured;
import org.assertj.core.api.SoftAssertions;
import org.example.dtos.json.placeholder.*;
import org.example.factories.PatchUpdateJsonPlaceHolderObjectFactory;
import org.example.factories.PostJsonPlaceHolderObjectFactory;
import org.example.factories.PutUpdateJsonPlaceHolderObjectFactory;
import org.junit.*;


public class JsonPlaceHolderTests {

    private SoftAssertions softly;

    @Before
    public void softAssertSetup() {
        softly = new SoftAssertions();
    }

    @Test
    public void updatePostUsingPatchTest() {
        PatchUpdateJsonPlaceHolderObjectFactory patchUpdateJsonPlaceHolderObjectFactory = new PatchUpdateJsonPlaceHolderObjectFactory();
        PatchUpdateJsonPlaceholderRequestDto patchUpdateJsonPlaceholderRequestDto = patchUpdateJsonPlaceHolderObjectFactory.
                patchUpdateJsonPlaceholderRequestDto("Patched Title");

        PatchUpdateJsonObjectResponseDto patchUpdateJsonObjectResponseDto = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(patchUpdateJsonPlaceholderRequestDto)
                .patch("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200)
                .extract()
                .as(PatchUpdateJsonObjectResponseDto.class);

        System.out.println(patchUpdateJsonObjectResponseDto);
        softly.assertThat(patchUpdateJsonObjectResponseDto.getTitle()).isEqualTo("Patched Title");
    }

    @Test
    public void postCreateJsonPlaceHolderTest() {
        PostJsonPlaceHolderObjectFactory postJsonPlaceHolderObjectFactory = new PostJsonPlaceHolderObjectFactory();
        PostJsonPlaceholderRequestDto requestDto = postJsonPlaceHolderObjectFactory.
                postJsonPlaceholderRequestDto("1", "101", "Updated Content", "Updated Title");

        PostJsonObjectResponseDto postJsonObjectResponseDto = RestAssured.given()
                .body(requestDto)
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201)
                .extract()
                .as(PostJsonObjectResponseDto.class);

        softly.assertThat(postJsonObjectResponseDto.getId()).isEqualTo(101);
    }

    @Test
    public void putUpdateJsonPlaceholderTest() {
        PutUpdateJsonPlaceHolderObjectFactory updateJsonPlaceHolderObjectFactory = new PutUpdateJsonPlaceHolderObjectFactory();
        PutUpdateJsonPlaceholderRequestDto requestDto = updateJsonPlaceHolderObjectFactory.
                UpdateJsonPlaceholderRequestDto("1", "Updated Content", "Updated Title");

        PutUpdateJsonObjectResponseDto updateJsonObjectResponseDto = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestDto)
                .put("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200)
                .extract()
                .as(PutUpdateJsonObjectResponseDto.class);

        softly.assertThat(updateJsonObjectResponseDto.getTitle()).isEqualTo("Updated Title");
        softly.assertThat(updateJsonObjectResponseDto.getBody()).isEqualTo("Updated Content");
    }

    @After
    public void softAssertTearDown() {
        softly.assertAll();
    }
}
