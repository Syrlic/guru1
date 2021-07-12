import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

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

        String firstName = "Santa";
        String lastName = "Clause";
        String email = "newyear@soon.com";
        String phone = "1234567890";
        String address = "1234 Main Street";
        String state = "Haryana";
        String city = "Panipat";

        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(byText("Male")).click();
        $("#userNumber").setValue(phone);
        //fill out Date of Birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(byText("November")).click();
        $(".react-datepicker__year-select").click();
        $(byText("1960")).click();
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__day--015").click();

        $(byText("Music")).click();
        //upload photo
     //   $(".form-file-label").click();
        String path = "src/test/java/resources/4040.jpg";
        File file = new File(path);
        $("#uploadPicture").uploadFile(file);
        $("#currentAddress").setValue(address);
        $("#react-select-3-input").setValue(state).pressTab();
    //    $(".css-2613qy-menu").$(byText("Haryana")).click();
        $("#react-select-4-input").setValue(city).pressEnter();
        $("#submit").click();

        //Asserts
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        ElementsCollection collection = $$("td");
        for (int i = 0; i< collection.size()-1; i++){
            SelenideElement element = collection.get(i);
            switch (i){
                case 0: collection.get(i).shouldHave(text("Student Name"));
                break;
                case 1: collection.get(i).shouldHave(text(firstName +" "+ lastName));
                    break;
                case 2: collection.get(i).shouldHave(text("Student Email"));
                    break;
                case 3: collection.get(i).shouldHave(text(email));
                    break;
                case 4: collection.get(i).shouldHave(text("Gender"));
                    break;
                case 5: collection.get(i).shouldHave(text("Male"));
                    break;
                case 6: collection.get(i).shouldHave(text("Mobile"));
                    break;
                case 7: collection.get(i).shouldHave(text(phone));
                    break;
                case 8: collection.get(i).shouldHave(text("Date of Birth"));
                    break;
                case 9: collection.get(i).shouldHave(text("15 November,1960"));
                    break;
                case 12: collection.get(i).shouldHave(text("Hobbies"));
                    break;
                case 13: collection.get(i).shouldHave(text("Music"));
                    break;
                case 14: collection.get(i).shouldHave(text("Picture"));
                    break;
                case 15: collection.get(i).shouldHave(text("4040.jpg"));
                    break;
                case 16: collection.get(i).shouldHave(text("Address"));
                    break;
                case 17: collection.get(i).shouldHave(text(address));
                    break;
                case 18: collection.get(i).shouldHave(text("State and City"));
                    break;
                case 19: collection.get(i).shouldHave(text(state+" "+city));
                    break;
                default: break;
            }
        }
    //    logger.info(collection.toString());


    }
}
