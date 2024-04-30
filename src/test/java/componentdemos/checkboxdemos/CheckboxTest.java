package componentdemos.checkboxdemos;

import com.microsoft.playwright.*;
import fixtures.ChromiumFixtures;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CheckboxTest extends ChromiumFixtures {

  @Test
  void shouldCheckWeekly() {
    page.navigate("https://documentation.webforj.com/docs/components/checkbox#constructors");
    page.frameLocator("iframe >> nth=0")
      .locator("dwc-checkbox:nth-child(2) > div > div")
      .first()
      .click();
    assertThat(page.frameLocator("iframe")
      .first()
      .locator("dwc-checkbox:nth-child(2) > div > div")
      .first())
      .isChecked();
  }

  @Test
  void shouldDisableDaily() {
    page.navigate("https://documentation.webforj.com/docs/components/checkbox#constructors");
    page.frameLocator("iframe >> nth=0").locator("div > div").first().click();
    assertThat(page.frameLocator("iframe").first().locator("div > div").first())
      .not().isChecked();
  }

  @Test
  void shouldCheckParent() {
    page.navigate("https://documentation.webforj.com/docs/components/checkbox#indeterminism");
    page.frameLocator("iframe >> nth=1").locator("div").filter(new Locator.FilterOptions().setHasText("Child 1")).locator("div").click();
    assertThat(page.frameLocator("iframe").nth(1).locator("div").filter(new Locator.FilterOptions().setHasText("Parent")).locator("div"))
      .isChecked();
  }

  @Test
  void parentShouldBeIndeterminate() {
    page.navigate("https://documentation.webforj.com/docs/components/checkbox#indeterminism");
    assertThat(page.frameLocator("iframe").nth(1).locator("div").filter(new Locator.FilterOptions().setHasText("Parent")).locator("div"))
      .hasJSProperty("indeterminate", true);
  }

  @Test
  void shouldUncheckParent() {
    page.navigate("https://documentation.webforj.com/docs/components/checkbox#indeterminism");
    page.frameLocator("iframe >> nth=1").locator("div").filter(new Locator.FilterOptions().setHasText("Child 2")).locator("div").click();
    assertThat(page.frameLocator("iframe").nth(1).locator("div").filter(new Locator.FilterOptions().setHasText("Parent")).locator("div"))
      .not().isChecked();
  }

  @Test
  void shouldCheckAll() {
    page.navigate("https://documentation.webforj.com/docs/components/checkbox#indeterminism");
    page.frameLocator("iframe >> nth=1").locator("div").filter(new Locator.FilterOptions().setHasText("Child 2")).locator("div").click();
    page.frameLocator("iframe >> nth=1").locator("div").filter(new Locator.FilterOptions().setHasText("Parent")).locator("div").click();
    assertThat(page.frameLocator("iframe").nth(1).locator("div").filter(new Locator.FilterOptions().setHasText("Child 1")).locator("div"))
      .isChecked();
    assertThat(page.frameLocator("iframe").nth(1).locator("div").filter(new Locator.FilterOptions().setHasText("Child 2")).locator("div"))
      .isChecked();
  }

  @Test
  void shouldUncheckAll() {
    page.navigate("https://documentation.webforj.com/docs/components/checkbox#indeterminism");
    page.frameLocator("iframe >> nth=1").locator("div").filter(new Locator.FilterOptions().setHasText("Child 2")).locator("div").click();
    page.frameLocator("iframe >> nth=1").locator("div").filter(new Locator.FilterOptions().setHasText("Parent")).locator("div").click();
    page.frameLocator("iframe >> nth=1").locator("div").filter(new Locator.FilterOptions().setHasText("Parent")).locator("div").click();
    assertThat(page.frameLocator("iframe").nth(1).locator("div").filter(new Locator.FilterOptions().setHasText("Child 1")).locator("div"))
      .not().isChecked();
    assertThat(page.frameLocator("iframe").nth(1).locator("div").filter(new Locator.FilterOptions().setHasText("Child 2")).locator("div"))
      .not().isChecked();
  }

  @Test
  void shouldCheckRemaining() {
    page.navigate("https://documentation.webforj.com/docs/components/checkbox#indeterminism");
    assertThat(page.frameLocator("iframe").nth(1).locator("div").filter(new Locator.FilterOptions().setHasText("Child 1")).locator("div"))
      .not().isChecked();
    assertThat(page.frameLocator("iframe").nth(1).locator("div").filter(new Locator.FilterOptions().setHasText("Child 2")).locator("div"))
      .isChecked();
    page.frameLocator("iframe >> nth=1").locator("div").filter(new Locator.FilterOptions().setHasText("Parent")).locator("div").click();
    assertThat(page.frameLocator("iframe").nth(1).locator("div").filter(new Locator.FilterOptions().setHasText("Child 1")).locator("div"))
      .isChecked();
    assertThat(page.frameLocator("iframe").nth(1).locator("div").filter(new Locator.FilterOptions().setHasText("Child 2")).locator("div"))
      .isChecked();
  }

}
