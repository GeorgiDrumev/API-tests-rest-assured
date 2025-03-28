import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.assertj.core.api.SoftAssertions;
import org.example.dtos.ergast.GetDriversDataResponseDto;
import org.junit.*;


public class FormulaOneDriversTests {

    private SoftAssertions softly;

    @Before
    public void softAssertSetup() {
        softly = new SoftAssertions();
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

        GetDriversDataResponseDto getDriversDataResponseDto = jsonPath.getObject("MRData.DriverTable.Drivers[0]", GetDriversDataResponseDto.class);

        softly.assertThat(getDriversDataResponseDto.getGivenName()).isEqualTo("Fernando");
        softly.assertThat(getDriversDataResponseDto.getFamilyName()).isEqualTo("Alonso");
        softly.assertThat(getDriversDataResponseDto.getNationality()).isEqualTo("Spanish");
        softly.assertThat(getDriversDataResponseDto.getPermanentNumber()).isEqualTo(14);
    }

    @After
    public void softAssertTearDown() {
        softly.assertAll();
    }
}
