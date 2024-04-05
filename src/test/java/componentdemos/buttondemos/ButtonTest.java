package componentdemos.buttondemos;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ButtonTest {
  static Playwright playwright;
  static Browser browser;
  BrowserContext context;
  Page page;

  @BeforeAll
  static void launchBrowser() {
    playwright = Playwright.create();
    browser = playwright.chromium()
      .launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100));
  }

  @AfterAll
  static void closeBrowser() {
    playwright.close();
  }

  @BeforeEach
  void setUp() {
    context = browser.newContext();
    page = context.newPage();
    page.navigate("https://documentation.webforj.com/docs/components/button");
  }

  @AfterEach
  void breakDown() {
    context.close();
  }

  @Test
  void shouldOpenMessageBox() {
    page.frameLocator("iframe >> nth=0")
      .getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Submit")).click();
    assertThat(page.frameLocator("iframe").first().getByText("Welcome to the application"))
      .isVisible();
  }

  @Test
  void shouldEmptyTextFields() {
    page.frameLocator("iframe >> nth=0")
      .getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Clear")).click();
    assertThat(page.frameLocator("iframe").first().getByLabel("First Name"))
      .isEmpty();
    assertThat(page.frameLocator("iframe").first().getByLabel("Last Name"))
      .isEmpty();
    assertThat(page.frameLocator("iframe").first().getByLabel("E-mail:"))
      .isEmpty();
  }

  @Test
  void shouldShowWarningForMalformedEmail() {
    page.navigate("https://documentation.webforj.com/docs/components/button");
    page.frameLocator("iframe >> nth=0").getByLabel("E-mail:").click();
    page.frameLocator("iframe >> nth=0")
      .getByLabel("E-mail:").fill("turner.jasonemail.com");
    assertThat(page.frameLocator("iframe").first().getByText("Please include an '@' in the"))
      .isVisible();
  }

  @Test
  void shouldEnableButton() {
    page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Disabling a Button").setExact(true)).click(
      new Locator.ClickOptions()
        .setClickCount(2));
    page.frameLocator("div >> internal:has-text=/^Show CodeButtonDisable\\.javabutton\\.setEnabled\\(false\\);$/ >> iframe").getByLabel("Enter an email").click();
    page.frameLocator("div >> internal:has-text=/^Show CodeButtonDisable\\.javabutton\\.setEnabled\\(false\\);$/ >> iframe").getByLabel("Enter an email").fill("jaseon.turner@email.com");
    page.frameLocator("div >> internal:has-text=/^Show CodeButtonDisable\\.javabutton\\.setEnabled\\(false\\);$/ >> iframe").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Submit")).click();
    assertThat(page.locator("div")
      .filter(new Locator.FilterOptions()
        .setHasText(
          Pattern.compile("^Show CodeButtonDisable\\.javabutton\\.setEnabled\\(false\\);$")))
      .frameLocator("iframe")
      .getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Submit")))
      .isEnabled();
  }

  @Test
  void buttonShouldBeDisabled() {
    page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Disabling a Button").setExact(true)).click(
      new Locator.ClickOptions()
        .setForce(true)
        .setClickCount(2));
    assertThat(page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Show CodeButtonDisable\\.javabutton\\.setEnabled\\(false\\);$"))).frameLocator("iframe").getByLabel("Enter an email"))
      .isEmpty();
    assertThat(page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Show CodeButtonDisable\\.javabutton\\.setEnabled\\(false\\);$"))).frameLocator("iframe").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Submit")))
      .isDisabled();
  }

  @Test
  void buttonShouldBeDisabledForMalformedEmail() {
    page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Disabling a Button").setExact(true)).click(
      new Locator.ClickOptions()
        .setForce(true)
        .setClickCount(2));
    page.frameLocator("div >> internal:has-text=/^Show CodeButtonDisable\\.javabutton\\.setEnabled\\(false\\);$/ >> iframe").getByLabel("Enter an email").click();
    page.frameLocator("div >> internal:has-text=/^Show CodeButtonDisable\\.javabutton\\.setEnabled\\(false\\);$/ >> iframe").getByLabel("Enter an email").fill("jason");
    assertThat(page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Show CodeButtonDisable\\.javabutton\\.setEnabled\\(false\\);$"))).frameLocator("iframe").getByText("Please include an '@' in the"))
      .isVisible();
    assertThat(page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Show CodeButtonDisable\\.javabutton\\.setEnabled\\(false\\);$"))).frameLocator("iframe").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Submit")))
      .isDisabled();
  }

}
