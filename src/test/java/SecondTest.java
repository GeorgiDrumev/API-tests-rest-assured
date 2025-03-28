import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.assertj.core.api.SoftAssertions;
import org.example.dtos.ergast.GetDriversDataResponseDto;
import org.example.dtos.json.placeholder.PostJsonObjectResponseDto;
import org.example.dtos.json.placeholder.PostJsonPlaceholderRequestDto;
import org.example.dtos.json.placeholder.UpdateJsonPlaceholderRequestDto;
import org.example.factories.PostJsonPlaceHolderObjectFactory;
import org.example.factories.UpdateJsonPlaceHolderObjectFactory;
import org.junit.*;

import static io.restassured.RestAssured.given;

public class SecondTest {

    private SoftAssertions softly;

    @Before
    public void softAssertSetup() {
        softly = new SoftAssertions();
    }

    @Test
    public void getAllPostsTest() {
        var response = given()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(200)
                .extract().jsonPath();

        softly.assertThat(response.getInt("[0].userId")).isEqualTo(1);
        softly.assertThat(response.getInt("[0].id")).isEqualTo(1);
        softly.assertThat(response.getString("[0].title")).contains("sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        softly.assertThat(response.getString("[0].body")).contains("quia et suscipit\nsuscipit recusandae");
        softly.assertThat(response.getList("$").size()).isGreaterThan(20);
    }


//
//
//    @Test
//    public void updatePostUsingPatchTest() {
//        String updateBody = """
//            {
//                "title": "Patched Title"
//            }
//        """;
//
//        var response = given()
//                .header("Content-Type", "application/json")
//                .body(updateBody)
//                .patch("https://jsonplaceholder.typicode.com/posts/1")
//                .then()
//                .statusCode(200)
//                .extract().jsonPath();
//
//        softly.assertThat(response.getString("title")).isEqualTo("Patched Title");
//    }
//
//    @Test
//    public void deletePostTest() {
//        given()
//                .delete("https://jsonplaceholder.typicode.com/posts/1")
//                .then()
//                .statusCode(200);
//    }
//
//    @Test
//    public void gPathTest() {
//        JsonPath jsonPath = given()
//                .get("https://jsonplaceholder.typicode.com/users")
//                .then()
//                .statusCode(200)
//                .extract().jsonPath()
//                ;
//
//        String dobAlonso = jsonPath.setRootPath("MRData.DriverTable.Drivers")
//                .get("find { d -> d.driverId == 'alonso' }.dateOfBirth");
//
//    }
//
//    @Test
//    public void testJsonPlaceholder() {
//        JsonPath jsonPath = RestAssured.given()
//                .baseUri("https://jsonplaceholder.typicode.com")
//                .basePath("/users")
//                .when()
//                .get()
//                .then()
//                .statusCode(200)
//                .extract()
//                .jsonPath();
//
//        String name = jsonPath.getString("[0].name");
//        softly.assertThat(name).isEqualTo("Leanne Graham");
//
//        String email = jsonPath.getString("[0].email");
//        softly.assertThat(email).isEqualTo("Sincere@april.biz");
//    }

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
        UpdateJsonPlaceHolderObjectFactory updateJsonPlaceHolderObjectFactory = new UpdateJsonPlaceHolderObjectFactory();
        UpdateJsonPlaceholderRequestDto requestDto = updateJsonPlaceHolderObjectFactory.
                UpdateJsonPlaceholderRequestDto("1", "Updated Content", "Updated Title");

        JsonPath jsonPath = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestDto)
                .put("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();

        System.out.println(jsonPath.prettify());
        softly.assertThat(jsonPath.getString("title")).isEqualTo("Updated Title");
        softly.assertThat(jsonPath.getString("body")).isEqualTo("Updated Content");
    }

    @Test
    public void getDriverDataTest() {
        JsonPath jsonPath = RestAssured.given()
                .baseUri("https://ergast.com")
                .basePath("/api/f1/2016/drivers.json")
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();
        System.out.println(jsonPath.prettify());

        GetDriversDataResponseDto driverData = jsonPath.getObject("MRData.DriverTable.Drivers[0]", GetDriversDataResponseDto.class);

        softly.assertThat(driverData.getGivenName()).isEqualTo("Fernando");
        softly.assertThat(driverData.getFamilyName()).isEqualTo("Alonso");
        softly.assertThat(driverData.getNationality()).isEqualTo("Spanish");
        softly.assertThat(driverData.getPermanentNumber()).isEqualTo(14);
    }

    @After
    public void softAssertTearDown() {
        softly.assertAll();
    }
}
