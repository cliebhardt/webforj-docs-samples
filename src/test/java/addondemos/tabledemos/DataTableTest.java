package addondemos.tabledemos;

import com.microsoft.playwright.*;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import com.webforj.component.html.elements.Strong;
import com.webforj.component.table.Column;
import fixtures.ChromiumFixtures;
import org.junit.jupiter.api.*;

import java.util.Random;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


@UsePlaywright
public class DataTableTest extends ChromiumFixtures {
  static Random rng = new Random();


  @BeforeEach
  void setUp() {
    page.navigate("https://documentation.webforj.com/docs/components/table");
  }

  @Test
  void shouldChangeNumberOfEntriesTo25() {
    page.frameLocator("iframe >> nth=0").locator("dwc-button svg").click();
    page.frameLocator("iframe >> nth=0").getByRole(AriaRole.OPTION, new FrameLocator.GetByRoleOptions().setName("25")).locator("span").click();
    assertThat(page.frameLocator("iframe >> nth=0").locator("dwc-button")).containsText("25");
  }

  @Test
  void shouldChangeNumberOfEntriesTo50() {
    page.frameLocator("iframe >> nth=0").locator("dwc-button svg").click();
    page.frameLocator("iframe >> nth=0").getByRole(AriaRole.OPTION, new FrameLocator.GetByRoleOptions().setName("50")).locator("span").click();
    assertThat(page.frameLocator("iframe >> nth=0").locator("dwc-button")).containsText("50");
  }

  @Test
  void shouldChangeNumberOfEntriesTo100() {
    page.frameLocator("iframe >> nth=0").locator("dwc-button svg").click();
    page.frameLocator("iframe >> nth=0").getByRole(AriaRole.OPTION, new FrameLocator.GetByRoleOptions().setName("100")).locator("span").click();
    assertThat(page.frameLocator("iframe >> nth=0").locator("dwc-button")).containsText("100");
  }

  @Test
  void shouldChangeNumberOfEntriesTo10() {
    page.frameLocator("iframe >> nth=0").locator("dwc-button svg").click();
    page.frameLocator("iframe >> nth=0").getByRole(AriaRole.OPTION, new FrameLocator.GetByRoleOptions().setName("10").setExact(true)).locator("span").click();
    assertThat(page.frameLocator("iframe >> nth=0").locator("dwc-button")).containsText("10");
  }

  @Test
  void shouldSearchAthlete() {
    page.frameLocator("iframe >> nth=0").getByPlaceholder("Search by athlete").click();
    page.frameLocator("iframe >> nth=0").getByPlaceholder("Search by athlete").fill("Missy");
    assertThat(page.frameLocator("iframe >> nth=0").locator("tbody")).containsText("Missy Franklin");
    assertThat(page.frameLocator("iframe >> nth=0").locator("tbody")).containsText("Missy Schwen-Ryan");
  }

  @Test
  void shouldChangePage() {
    int pageNumber = rng.nextInt(1, 6);
    page.frameLocator("iframe >> nth=0").getByLabel("Goto page " + pageNumber).click();
    assertThat(page.frameLocator("iframe >> nth=0").getByLabel("Goto page " + pageNumber)).containsText(String.valueOf(pageNumber));
  }

  @Test
  void shouldJumpToLastPage() {
    page.frameLocator("iframe >> nth=0").getByLabel("Goto last page").click();
    assertThat(page.frameLocator("iframe >> nth=0").getByLabel("Goto page 862")).containsText("862");
  }

  @Test
  void shouldJumpToFirstPage() {
    page.frameLocator("iframe >> nth=0").getByLabel("Goto page 5").click();
    page.frameLocator("iframe >> nth=0").getByLabel("Goto first page").click();
    assertThat(page.frameLocator("iframe >> nth=0").getByLabel("Goto page 1")).containsText("1");
  }

}
