package components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

public class CalendarForm {

    public void setDate(String day, String month, String year){
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        SelenideElement dayLocator = $(format(".react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)", day));
        dayLocator.click();
    }
}
