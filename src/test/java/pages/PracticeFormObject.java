package pages;

import components.CalendarForm;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static java.lang.String.format;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.text;

public class PracticeFormObject {
    private final static String REG_FORM_NAME = "Student Registration Form";
    private final static String RESULT_FORM_NAME = "Thanks for submitting the form";

    private CalendarForm calendar = new CalendarForm();

    public void openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(REG_FORM_NAME));
    }

    public void typeFirstName(String firstName) {
        $("#firstName").setValue(firstName);
    }

    public void typeLastName(String lastName) {
        $("#lastName").setValue(lastName);
    }

    public void typeEmail(String email) {
        $("#userEmail").setValue(email);
    }

    public void selectGender(String gender) {
        $(format("[name=gender][value=%s]", gender)).parent().click();
    }

    public void typephone(String phone) {
        $("#userNumber").setValue(phone);
    }

    public void typeDateOfBirth(String day, String month, String year) {
       calendar.setDate(day, month, year);
    }

    public void setSubject(String subject) {
        $("#subjectsInput").setValue(subject).pressEnter();
    }

    public void selectHobby(String hobby) {
        $("#hobbiesWrapper").$(byText(hobby)).click();
    }

    public void selectPicture(String fileName) {
      //  $("#uploadPicture").uploadFromClasspath("src/test/java/resources/"+fileName);
        String path = "src/test/java/resources/"+fileName+".jpg";
        File file = new File(path);
        $("#uploadPicture").uploadFile(file);
    }

    public void typeAddress(String address) {
        $("#currentAddress").setValue(address);
      //  $("textarea").setValue(address);
    }

    public void selectState(String state) {
        $("#state").$("#react-select-3-input").setValue(state).pressTab();
    }

    public void selectCity(String city) {
        $("#city").$("#react-select-4-input").setValue(city).pressEnter();
    }

    public void clickSubmit() {
        $("#submit").click();
    }

    public void checkResultTitleForm(){
        $(".modal-content").shouldHave(text(RESULT_FORM_NAME));
    }

    public void checkResults(String value){
        $(".table-responsive").shouldHave(text(value));
    }

}
