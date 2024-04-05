package componentdemos.buttondemos;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.BoundingBox;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class DialogTest extends ChromiumFixtures {

  @Test
  void dialogShouldBeHidden() {
    page.navigate("https://documentation.webforj.com/docs/components/dialog#closing-the-dialog");
    page.getByRole(AriaRole.LINK,
      new Page.GetByRoleOptions()
        .setName("Closing the Dialog")
        .setExact(true)
      ).click(new Locator.ClickOptions().setClickCount(2));
    page.frameLocator(".css-trexur >> nth=0")
      .getByRole(AriaRole.BUTTON,
        new FrameLocator.GetByRoleOptions()
          .setName("Close Dialog")
      ).click();
    assertThat(page.frameLocator(".css-trexur >> nth=0")
      .getByRole(AriaRole.DIALOG)
    ).isHidden();
  }

  @Test
  void dialogShouldBeVisible() {
    page.navigate("https://documentation.webforj.com/docs/components/dialog#closing-the-dialog");
    page.getByRole(AriaRole.LINK,
      new Page.GetByRoleOptions()
        .setName("Closing the Dialog")
        .setExact(true)
    ).click(new Locator.ClickOptions().setClickCount(2));
    page.frameLocator(".css-trexur >> nth=0")
      .getByRole(AriaRole.BUTTON,
        new FrameLocator.GetByRoleOptions()
          .setName("Close Dialog")
      ).click();
    assertThat(page.frameLocator(".css-trexur >> nth=0")
      .getByRole(AriaRole.DIALOG)
    ).isHidden();
    page.frameLocator(".css-trexur >> nth=0").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Show Dialog")).click();
    assertThat(page.frameLocator(".css-trexur >> nth=0")
      .getByRole(AriaRole.DIALOG)
    ).isVisible();
  }

  @Test
  void textFieldShouldBeAutoFocused() {
    page.navigate("https://documentation.webforj.com/docs/components/dialog#auto-focus");
    page.getByRole(AriaRole.LINK,
      new Page.GetByRoleOptions()
        .setName("Auto-Focus")
        .setExact(true)
    ).click(new Locator.ClickOptions().setClickCount(2));
    page.frameLocator("div:nth-child(23) > .css-1n1u9ag > .css-trexur")
      .locator("dwc-field")
      .click();
    assertThat(page.frameLocator("div:nth-child(23) > .css-1n1u9ag > .css-trexur")
      .getByLabel("This Box is Auto Focused")
    ).isFocused();
  }

  @Test
  void shouldWriteIntoTextField() {
    page.navigate("https://documentation.webforj.com/docs/components/dialog#auto-focus");
    page.getByRole(AriaRole.LINK,
      new Page.GetByRoleOptions()
        .setName("Auto-Focus")
        .setExact(true)
    ).click(new Locator.ClickOptions().setClickCount(2));
    page.frameLocator("div:nth-child(23) > .css-1n1u9ag > .css-trexur")
      .getByText("This Box is Auto Focused")
      .click();
    page.frameLocator("div:nth-child(23) > .css-1n1u9ag > .css-trexur")
      .getByLabel("This Box is Auto Focused")
      .fill("test");
    assertThat(page.frameLocator("div:nth-child(23) > .css-1n1u9ag > .css-trexur")
      .getByLabel("This Box is Auto Focused")
    ).not().isEmpty();

  }

  @Test
  void dialogueShouldHaveChangedPosition() {
    page.navigate("https://documentation.webforj.com/docs/components/dialog#positioning");
    BoundingBox before = page.frameLocator("div:nth-child(31) > .css-1n1u9ag > .css-trexur")
      .getByRole(AriaRole.DIALOG)
      .boundingBox();
    page.getByRole(AriaRole.LINK,
      new Page.GetByRoleOptions()
        .setName("Positioning")
        .setExact(true)
    ).click(new Locator.ClickOptions().setClickCount(2));
    page.frameLocator("div:nth-child(31) > .css-1n1u9ag > .css-trexur")
      .locator("#field-1")
      .click();
    page.frameLocator("div:nth-child(31) > .css-1n1u9ag > .css-trexur")
      .locator("#field-1")
      .fill("10");
    page.frameLocator("div:nth-child(31) > .css-1n1u9ag > .css-trexur")
      .locator("#field-2")
      .click();
    page.frameLocator("div:nth-child(31) > .css-1n1u9ag > .css-trexur")
      .locator("#field-2")
      .fill("10");
    page.frameLocator("div:nth-child(31) > .css-1n1u9ag > .css-trexur")
      .getByRole(AriaRole.BUTTON,
        new FrameLocator.GetByRoleOptions().setName("Set Dialog Position")
      ).click();
    BoundingBox after = page.frameLocator("div:nth-child(31) > .css-1n1u9ag > .css-trexur")
      .getByRole(AriaRole.DIALOG)
      .boundingBox();
    assertNotEquals(before, after);
  }

  @Test
  void dialogShouldBeTopAligned() {
    page.navigate("https://documentation.webforj.com/docs/components/dialog#vertical-alignment");
    BoundingBox before = page.frameLocator(".css-1c6hira")
      .getByRole(AriaRole.DIALOG)
      .boundingBox();
    page.getByRole(AriaRole.LINK,
      new Page.GetByRoleOptions()
        .setName("Vertical Alignment")
        .setExact(true)
    ).click(new Locator.ClickOptions().setClickCount(2));
    page.frameLocator(".css-1c6hira")
      .getByLabel("chevron down")
      .locator("svg")
      .click();
    page.frameLocator(".css-1c6hira")
      .getByText("Top")
      .click();
    BoundingBox after = page.frameLocator(".css-1c6hira")
      .getByRole(AriaRole.DIALOG)
      .boundingBox();
    assertTrue(Double.compare(before.y, after.y) > 0);
  }

  @Test
  void dialogShouldBeBottomAligned() {
    page.navigate("https://documentation.webforj.com/docs/components/dialog#vertical-alignment");
    BoundingBox before = page.frameLocator(".css-1c6hira")
      .getByRole(AriaRole.DIALOG)
      .boundingBox();
    page.getByRole(AriaRole.LINK,
      new Page.GetByRoleOptions()
        .setName("Vertical Alignment")
        .setExact(true)
    ).click(new Locator.ClickOptions().setClickCount(2));
    page.frameLocator(".css-1c6hira")
      .getByLabel("chevron down")
      .locator("svg")
      .click();
    page.frameLocator(".css-1c6hira")
      .getByText("Bottom")
      .click();
    BoundingBox after = page.frameLocator(".css-1c6hira")
      .getByRole(AriaRole.DIALOG)
      .boundingBox();
    assertTrue(Double.compare(before.y, after.y) < 0);
  }

  @Test
  void dialogShouldBeCenterAfterBottomAligned() {
    page.navigate("https://documentation.webforj.com/docs/components/dialog#vertical-alignment");
    BoundingBox before = page.frameLocator(".css-1c6hira")
      .getByRole(AriaRole.DIALOG)
      .boundingBox();
    page.getByRole(AriaRole.LINK,
      new Page.GetByRoleOptions()
        .setName("Vertical Alignment")
        .setExact(true)
    ).click(new Locator.ClickOptions().setClickCount(2));
    page.frameLocator(".css-1c6hira")
      .getByLabel("chevron down")
      .locator("svg")
      .click();
    page.frameLocator(".css-1c6hira").getByRole(AriaRole.OPTION, new FrameLocator.GetByRoleOptions().setName("Bottom")).click();
    page.frameLocator(".css-1c6hira")
      .getByLabel("chevron down")
      .locator("svg")
      .click();
    page.frameLocator(".css-1c6hira").getByText("Center").click();
    BoundingBox after = page.frameLocator(".css-1c6hira")
      .getByRole(AriaRole.DIALOG)
      .boundingBox();
    assertEquals(before.y, after.y);
  }

  @Test
  void dialogShouldBeCenterAfterTop() {
    page.navigate("https://documentation.webforj.com/docs/components/dialog#vertical-alignment");
    BoundingBox before = page.frameLocator(".css-1c6hira")
      .getByRole(AriaRole.DIALOG)
      .boundingBox();
    page.frameLocator(".css-1c6hira").getByLabel("chevron down").locator("svg").click();
    page.frameLocator(".css-1c6hira").getByRole(AriaRole.OPTION, new FrameLocator.GetByRoleOptions().setName("Top")).click();
    page.frameLocator(".css-1c6hira").getByLabel("chevron down").locator("svg").click();
    page.frameLocator(".css-1c6hira").getByText("Center").click();
    BoundingBox after = page.frameLocator(".css-1c6hira")
      .getByRole(AriaRole.DIALOG)
      .boundingBox();
    assertEquals(before.y, after.y);
  }

}
