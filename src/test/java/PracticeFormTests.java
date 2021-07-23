import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {

    Logger logger = LoggerFactory.getLogger(PracticeFormTests.class);

    @BeforeEach
    public void startBrowser(){
        Configuration.startMaximized=true;
    }
    @Test
    public void successFOrmSubmit(){

        String firstName = "Santa",
                lastName = "Clause",
                email = "newyear@soon.com",
                gender = "Male",
                phone = "1234567890",
                dayOfBirth = "30",
                monthOfBirth = "November",
                yearOfBirth = "1960",
                subject = "Maths",
                hobby = "Music",
                picture = "4040.jpg",
                address = "1234 Main Street",
                state = "Haryana",
                city = "Panipat";

        Map<String, String> expectedData= new HashMap<>(){
            {
                put("Student Name", firstName+" "+lastName);
                put("Student Email", email);
                put("Gender", gender);
                put("Mobile", phone);
                put("Date of Birth", dayOfBirth+" "+monthOfBirth+","+yearOfBirth);
                put("Subjects", subject);
                put("Hobbies", hobby);
                put("Picture", picture);
                put("Address", address);
                put("State and City", state+" "+city);
            }
        };
        open("https://demoqa.com/automation-practice-form");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(byText("Male")).click();
        $("#userNumber").setValue(phone);

        //fill out Date of Birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("November");
        $(".react-datepicker__year-select").selectOption("1960");
        $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();

        $("#subjectsInput").setValue(subject).pressEnter();
        $(byText("Music")).click();

        //upload photo
        String path = "src/test/java/resources/4040.jpg";
        File file = new File(path);
        $("#uploadPicture").uploadFile(file);
        $("#currentAddress").setValue(address);
        $("#react-select-3-input").setValue(state).pressTab();
        $("#react-select-4-input").setValue(city).pressEnter();
        $("#submit").click();

        //Asserts
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        SoftAssertions softly = new SoftAssertions();
        for (SelenideElement element: $$("tbody tr").snapshot()){
           String key = element.$("td").getText();
           String actualResult = element.$("td", 1).getText();
           String expectedResult = expectedData.get(key);

           softly.assertThat(actualResult).isEqualTo(expectedResult);
        }
        softly.assertAll();
    //    logger.info(collection.toString());
    }
}
