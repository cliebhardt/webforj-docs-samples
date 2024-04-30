package fixtures;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DebugFixtures {
  // shared by all instances
  Playwright playwright;
  Browser browser;
  // unique to each instance
  protected BrowserContext context;
  protected Page page;

  @BeforeAll
  void launchBrowser() {
    playwright = Playwright.create();
    browser = playwright.chromium().launch(
      new BrowserType.LaunchOptions()
        .setHeadless(false)
        .setSlowMo(1000)
    );
  }

  @AfterAll
  void closeBrowser() {
    playwright.close();
  }

  @BeforeEach
  void createContextAndPage() {
    context = browser.newContext();
    page = context.newPage();
  }

  @AfterEach
  void closeContext() {
    context.close();
  }
}
