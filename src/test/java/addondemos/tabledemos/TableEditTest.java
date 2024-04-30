package addondemos.tabledemos;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import fixtures.ChromiumFixtures;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TableEditTest extends ChromiumFixtures {

  @BeforeEach
  void setUp() {
    page.navigate("https://documentation.webforj.com/docs/components/table/refreshing");
  }

  @Test
  void shouldOpenDialogue() {
    page.frameLocator("iframe").getByRole(AriaRole.ROW,
      new FrameLocator.GetByRoleOptions().setName("000001 Mississippi Blues John"))
      .getByRole(AriaRole.BUTTON).click();
    assertThat(page.frameLocator("iframe").getByRole(AriaRole.DIALOG)).isVisible();
  }

  @Test
  void shouldEditTitle() {
    page.frameLocator("iframe").getByRole(AriaRole.ROW, new FrameLocator.GetByRoleOptions().setName("000001 Mississippi Blues John")).getByRole(AriaRole.BUTTON).click();
    page.frameLocator("iframe").getByLabel("New Title").click();
    page.frameLocator("iframe").getByLabel("New Title").fill("Mississippi Blues2");
    page.frameLocator("iframe").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Save")).click();
    assertThat(page.frameLocator("iframe").getByText("Mississippi Blues2")).containsText("Mississippi Blues2");
  }

  @Test
  void shouldEditTitleWithEnter() {
    page.frameLocator("iframe").getByRole(AriaRole.ROW, new FrameLocator.GetByRoleOptions().setName("000001 Mississippi Blues John")).getByRole(AriaRole.BUTTON).click();
    page.frameLocator("iframe").getByLabel("New Title").click();
    page.frameLocator("iframe").getByLabel("New Title").fill("Mississippi Blues2");
    page.frameLocator("iframe").getByLabel("New Title").press("Enter");
    assertThat(page.frameLocator("iframe").getByText("Mississippi Blues2")).containsText("Mississippi Blues2");
  }

  @Test
  void shouldNotEditTitle() {
    page.frameLocator("iframe").getByRole(AriaRole.ROW, new FrameLocator.GetByRoleOptions().setName("000001 Mississippi Blues John")).getByRole(AriaRole.BUTTON).click();
    page.frameLocator("iframe").getByLabel("New Title").click();
    page.frameLocator("iframe").getByLabel("New Title").fill("Mississippi Blues2");
    page.frameLocator("iframe").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Cancel")).click();
    assertThat(page.frameLocator("iframe").getByText("Mississippi Blues")).containsText("Mississippi Blues");
  }

  @Test
  void shouldNotEditTitleWithEscapte() {
    page.frameLocator("iframe").getByRole(AriaRole.ROW, new FrameLocator.GetByRoleOptions().setName("000001 Mississippi Blues John")).getByRole(AriaRole.BUTTON).click();
    page.frameLocator("iframe").getByLabel("New Title").click();
    page.frameLocator("iframe").getByLabel("New Title").fill("Mississippi Blues2");
    page.frameLocator("iframe").getByLabel("New Title").press("Escape");
    assertThat(page.frameLocator("iframe").getByText("Mississippi Blues")).containsText("Mississippi Blues");
  }

}
