package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.junit.SoftAsserts;
import com.github.javafaker.Faker;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.PracticeFormObject;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$$;

public class PracticeFormPageObjectTests {
    PracticeFormObject object = new PracticeFormObject();
    Faker faker = new Faker();
    Map<String, String> testData= new HashMap<>(){
        {
            put("First Name", faker.name().firstName());
            put("Last Name", faker.name().lastName());
            put("Email", faker.internet().emailAddress());
            put("Gender", "Male");
            put("Phone", faker.phoneNumber().subscriberNumber(10));
            put("Day", "30");
            put("Month", "November");
            put("Year", "1960");
            put("Subject", "Maths");
            put("Hobby", "Sports");
            put("Picture", "4040");
            put("Street", "");
            put("State", "Haryana");
            put("City", "Panipat");
        }
    };

    Map<String, String> expectedData= new HashMap<>(){
        {
            put("Student Name", testData.get("First Name")+" "+testData.get("Last Name"));
            put("Student Email", testData.get("Email"));
            put("Gender", testData.get("Gender"));
            put("Mobile", testData.get("Phone"));
            put("Date of Birth", testData.get("Day")+" "+testData.get("Month")+","+testData.get("Year"));
            put("Subjects", testData.get("Subject"));
            put("Hobbies", testData.get("Hobby"));
            put("Picture", testData.get("Picture")+".jpg");
            put("Address", "");
            put("State and City", testData.get("State")+" "+testData.get("City"));
        }
    };

    @BeforeAll
    static void setup(){
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.startMaximized = true;
    }

    @Test
    public void successfulFormTestWithPOJO(){
        object.openPage();
        object.typeFirstName(testData.get("First Name"));
        object.typeLastName(testData.get("Last Name"));
        object.typeEmail(testData.get("Email"));
        object.selectGender(testData.get("Gender"));
        object.typephone(testData.get("Phone"));
        object.typeDateOfBirth(testData.get("Day"), testData.get("Month"), testData.get("Year"));
        object.setSubject(testData.get("Subject"));
        object.selectHobby(testData.get("Hobby"));
        object.selectPicture(testData.get("Picture"));
        object.typeAddress(testData.get("Address"));
        object.selectState(testData.get("State"));
        object.selectCity(testData.get("City"));
        object.clickSubmit();

        //Asserts
        object.checkResultTitleForm();
        SoftAssertions softly = new SoftAssertions();
        for (SelenideElement element : $$("tbody tr").snapshot()){
            String key = element.$("td").getText();
            String actualResult = element.$("td", 1).getText();
            String expectedResult = expectedData.get(key);

            softly.assertThat(actualResult).isEqualTo(expectedResult);
        }

        softly.assertAll();

    }
}
