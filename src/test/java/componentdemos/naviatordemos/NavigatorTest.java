package componentdemos.naviatordemos;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import fixtures.ChromiumFixtures;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class NavigatorTest extends ChromiumFixtures {

  @Test
  void shouldJumpToLast() {
    page.navigate("https://documentation.webforj.com/docs/components/navigator#maximum-pages");
    assertThat(page.frameLocator("iframe >> nth=1").getByLabel("Goto page 1")).isVisible();
    page.frameLocator("iframe >> nth=1").getByLabel("Goto last page").click();
    assertThat(page.frameLocator("iframe >> nth=1").getByLabel("Goto page 10")).isVisible();
    assertThat(page.frameLocator("iframe >> nth=1").getByRole(AriaRole.PARAGRAPH)).containsText("Showing 91 to 100 of 100");
  }

  @Test
  void shouldJumpToFirst() {
    page.navigate("https://documentation.webforj.com/docs/components/navigator#maximum-pages");
    page.frameLocator("iframe >> nth=1").getByLabel("Goto page 1").click();
    assertThat(page.frameLocator("iframe >> nth=1").getByLabel("Goto page 1")).isVisible();
    assertThat(page.frameLocator("iframe >> nth=1").getByText("Navigate with the buttons")).isVisible();
    page.frameLocator("iframe >> nth=1").getByLabel("Goto last page").click();
    assertThat(page.frameLocator("iframe >> nth=1").getByLabel("Goto page 10")).isVisible();
    assertThat(page.frameLocator("iframe >> nth=1").getByText("Showing 91 to 100 of 100")).isVisible();
    page.frameLocator("iframe >> nth=1").getByLabel("Goto first page").click();
    assertThat(page.frameLocator("iframe >> nth=1").getByLabel("Goto page 1")).isVisible();
    assertThat(page.frameLocator("iframe >> nth=1").getByText("Showing 1 to 10 of 100")).isVisible();
  }

  @Test
  void shouldShowPreview() {
    page.navigate("https://documentation.webforj.com/docs/components/navigator#layouts");
    assertThat(page.frameLocator("iframe >> nth=3").locator("dwc-button")).containsText("PREVIEW");
    assertThat(page.frameLocator("iframe >> nth=3").getByLabel("navigation")).containsText("1 of 10");
  }

  @Test
  void shouldShowPages() {
    page.navigate("https://documentation.webforj.com/docs/components/navigator#layouts");
    page.frameLocator("iframe >> nth=3").getByLabel("chevron down").locator("svg").click();
    page.frameLocator("iframe >> nth=3").getByRole(AriaRole.OPTION, new FrameLocator.GetByRoleOptions().setName("PAGES")).click();
    assertThat(page.frameLocator("iframe >> nth=3").locator("dwc-button")).containsText("PAGES");
    assertThat(page.frameLocator("iframe >> nth=3").getByLabel("navigation")).containsText("12345...");
  }

  @Test
  void shouldShowQuickJump() {
    page.navigate("https://documentation.webforj.com/docs/components/navigator#layouts");
    page.frameLocator("iframe >> nth=3").getByLabel("chevron down").locator("svg").click();
    page.frameLocator("iframe >> nth=3").getByText("QUICK_JUMP").click();
    assertThat(page.frameLocator("iframe >> nth=3").locator("#field-1")).hasValue("1");
  }

}
