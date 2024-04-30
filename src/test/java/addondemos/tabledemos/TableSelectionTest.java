package addondemos.tabledemos;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.AriaRole;
import fixtures.ChromiumFixtures;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TableSelectionTest extends ChromiumFixtures {

  @BeforeEach
  void setUp() {
    page.navigate("https://documentation.webforj.com/docs/components/table/selection");
  }

  @Test
  void shouldSelectOneCheckbox() {
    page.frameLocator("iframe >> nth=1").getByRole(AriaRole.ROW, new FrameLocator.GetByRoleOptions().setName("000001 Mississippi Blues John")).locator("dwc-checkbox div").nth(1).click();
    page.frameLocator("iframe >> nth=1").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("OK")).click();
    //assertThat(page.frameLocator("iframe").nth(1).locator("td > div").first().getByRole(AriaRole.BUTTON)).isChecked();
    assertThat(page.locator("iframe").nth(1).getByRole(AriaRole.ROW, new Locator.GetByRoleOptions().setName("000001 Mississippi Blues John")).locator("dwc-checkbox div").nth(1)).isChecked();
  }

  @Test
  void shouldOpenMessageBox() {
    page.frameLocator("iframe >> nth=1").getByRole(AriaRole.ROW, new FrameLocator.GetByRoleOptions().setName("000001 Mississippi Blues John")).locator("dwc-checkbox div").nth(1).click();
    assertThat(page.frameLocator("iframe").nth(1).getByText("Record Selection")).isVisible();
  }

  @Test
  void shouldUnselectOneCheckbox() {
    page.frameLocator("iframe >> nth=1").getByRole(AriaRole.ROW, new FrameLocator.GetByRoleOptions().setName("000001 Mississippi Blues John")).locator("dwc-checkbox div").nth(1).click();
    page.frameLocator("iframe >> nth=1").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("OK")).click();
    page.frameLocator("iframe >> nth=1").getByRole(AriaRole.ROW, new FrameLocator.GetByRoleOptions().setName("000001 Mississippi Blues John")).locator("dwc-checkbox div").nth(1).click();
    page.frameLocator("iframe >> nth=1").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("OK")).click();
    assertThat(page.locator("iframe").nth(1).getByRole(AriaRole.ROW, new Locator.GetByRoleOptions().setName("000001 Mississippi Blues John")).locator("dwc-checkbox div").nth(1))
      .isChecked(new LocatorAssertions.IsCheckedOptions().setChecked(false));
  }

}
