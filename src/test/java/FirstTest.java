import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class FirstTest {

    @Test
    public void getAllPostsTest() {
        given()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(200)
                .body("[0].userId", equalTo(1))
                .body("[0].id", equalTo(1))
                .body("[0].title", containsString("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"))
                .body("[0].body", containsString("quia et suscipit\nsuscipit recusandae"))
                .body("size()", greaterThan(20));
    }

    @Test
    public void createPostTest() {
        String body = "{\n"
                + "    \"userId\": \"1\",\n"
                + "    \"id: \"101\"\n"
                + "    \"title\": \"Steven Lewis\",\n"
                + "    \"body\": \"Lawyer\"\n"
                + "}";

        given()
                .body(body)
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201)
                .body("id", greaterThan(100));
    }

    @Test
    public void updatePostUsingPutTest() {
        String updateBody = """
            {
                "userId": 1,
                "title": "Updated Title",
                "body": "Updated Content"
            }
        """;

        given()
                .header("Content-Type", "application/json")
                .body(updateBody)
                .put("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("Updated Title"))
                .body("body", equalTo("Updated Content"));
    }

    @Test
    public void updatePostUsingPatchTest() {
        String updateBody = """
            {
                "title": "Patched Title"
            }
        """;

        given()
                .header("Content-Type", "application/json")
                .body(updateBody)
                .patch("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("Patched Title"));
    }

    @Test
    public void deletePostTest() {
        given()
                .delete("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200);
    }

}