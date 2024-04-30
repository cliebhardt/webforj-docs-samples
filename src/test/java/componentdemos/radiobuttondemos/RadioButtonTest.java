package componentdemos.radiobuttondemos;

import com.microsoft.playwright.*;
import fixtures.ChromiumFixtures;
import org.junit.jupiter.api.*;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class RadioButtonTest extends ChromiumFixtures {

  @Test
  void shouldCheckRadioButton() {
    page.navigate("https://documentation.webforj.com/docs/components/radiobutton");
    assertThat(page.frameLocator("iframe >> nth=0").locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Strongly Disagree$"))).locator("div")).not().isChecked();
    assertThat(page.frameLocator("iframe >> nth=0").locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Disagree$"))).locator("div")).not().isChecked();
    assertThat(page.frameLocator("iframe >> nth=0").locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Neutral$"))).locator("div")).not().isChecked();
    assertThat(page.frameLocator("iframe >> nth=0").locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Agree$"))).locator("div")).not().isChecked();
    assertThat(page.frameLocator("iframe >> nth=0").locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Strongly Agree$"))).locator("div")).not().isChecked();
    page.frameLocator("iframe >> nth=0").locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Strongly Disagree$"))).locator("div").click();
    assertThat(page.frameLocator("iframe >> nth=0").locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Strongly Disagree$"))).locator("div")).isChecked();
    page.frameLocator("iframe >> nth=0").locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Disagree$"))).locator("div").click();
    assertThat(page.frameLocator("iframe >> nth=0").locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Disagree$"))).locator("div")).isChecked();
    page.frameLocator("iframe >> nth=0").locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Neutral$"))).locator("div").click();
    assertThat(page.frameLocator("iframe >> nth=0").locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Neutral$"))).locator("div")).isChecked();
    page.frameLocator("iframe >> nth=0").locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Agree$"))).locator("div").click();
    assertThat(page.frameLocator("iframe >> nth=0").locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Agree$"))).locator("div")).isChecked();
    page.frameLocator("iframe >> nth=0").locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Strongly Agree$"))).locator("div").click();
    assertThat(page.frameLocator("iframe >> nth=0").locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Strongly Agree$"))).locator("div")).isChecked();
  }

  @Test
  void shouldAutoActivateRadioButton() {
    page.navigate("https://documentation.webforj.com/docs/components/radiobutton#activation");
    assertThat(page.frameLocator("iframe >> nth=2").locator("div > div").first()).isChecked();
    assertThat(page.frameLocator("iframe >> nth=2").locator("dwc-radio:nth-child(2) > div > div")).not().isChecked();
    page.frameLocator("iframe >> nth=2").getByText("Auto Activated").first().click();
    page.frameLocator("iframe >> nth=2").locator("#radio-3").press("Tab");
    assertThat(page.frameLocator("iframe >> nth=2").locator("dwc-radio:nth-child(2) > div > div")).isChecked();
  }

}
