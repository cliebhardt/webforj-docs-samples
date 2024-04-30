package componentdemos.drawerdemos;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import fixtures.ChromiumFixtures;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class DrawerTest extends ChromiumFixtures {

  @Test
  void shouldHideDrawer() {
    page.navigate("https://documentation.webforj.com/docs/components/drawer");
    page.locator("body").press("PageDown");
    assertThat(page.frameLocator("iframe >> nth=0").getByRole(AriaRole.DIALOG, new FrameLocator.GetByRoleOptions().setName("Drawer"))).isVisible();
    page.frameLocator("iframe >> nth=0").getByRole(AriaRole.DIALOG, new FrameLocator.GetByRoleOptions().setName("Drawer")).locator("div").nth(1).click();
    assertThat(page.frameLocator("iframe >> nth=0").getByRole(AriaRole.DIALOG, new FrameLocator.GetByRoleOptions().setName("Drawer"))).isHidden();
  }

  @Test
  void shouldShowDrawer() {
    page.navigate("https://documentation.webforj.com/docs/components/drawer");
    page.locator("body").press("PageDown");
    assertThat(page.frameLocator("iframe >> nth=0").getByRole(AriaRole.DIALOG, new FrameLocator.GetByRoleOptions().setName("Drawer"))).isVisible();
    page.frameLocator("iframe >> nth=0").getByRole(AriaRole.DIALOG, new FrameLocator.GetByRoleOptions().setName("Drawer")).locator("div").nth(1).click();
    assertThat(page.frameLocator("iframe >> nth=0").getByRole(AriaRole.DIALOG, new FrameLocator.GetByRoleOptions().setName("Drawer"))).isHidden();
    page.frameLocator("iframe >> nth=0").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Open Welcome Page")).click();
    assertThat(page.frameLocator("iframe >> nth=0").getByRole(AriaRole.DIALOG, new FrameLocator.GetByRoleOptions().setName("Drawer"))).isVisible();
  }

  @Test
  void shouldSideCloseDrawer() {
    page.navigate("https://documentation.webforj.com/docs/components/drawer#constructors");
    assertThat(page.frameLocator("iframe >> nth=1").getByLabel("Drawer")).isVisible();
    page.frameLocator("iframe >> nth=1").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("icon x")).click();
    assertThat(page.frameLocator("iframe >> nth=1").getByLabel("Drawer")).isVisible();
  }

}
