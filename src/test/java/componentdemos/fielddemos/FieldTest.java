package componentdemos.fielddemos;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.KeyboardModifier;
import fixtures.ChromiumFixtures;
import fixtures.DebugFixtures;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FieldTest extends DebugFixtures {

  @Test
  void shouldChangeDate() {
    page.navigate("https://documentation.webforj.com/docs/components/fields/datefield");
    LocalDate date = LocalDate.now();
    assertThat(page.frameLocator("iframe").getByLabel("Departure Date:")).hasValue(LocalDate.now().toString());
    page.frameLocator("iframe").getByLabel("Departure Date:").fill("2024-04-30");
    assertThat(page.frameLocator("iframe").getByLabel("Departure Date:")).hasValue("2024-04-30");
  }

  @Test
  void shouldChangeDateTime() {
    page.navigate("https://documentation.webforj.com/docs/components/fields/datetimefield");
    assertThat(page.frameLocator("iframe").getByLabel("Departure Date and Time:")).isEmpty();
    page.frameLocator("iframe").getByLabel("Departure Date and Time:").click();
    page.frameLocator("iframe").getByLabel("Departure Date and Time:").fill("2990-05-10T00:30");
    assertThat(page.frameLocator("iframe").getByLabel("Departure Date and Time:")).hasValue("2990-05-10T00:30");
  }

  @Test
  void shouldChangeNumberField() {
    page.navigate("https://documentation.webforj.com/docs/components/fields/numberfield");
    assertThat(page.frameLocator("iframe").getByLabel("Quantity:")).isEmpty();
    page.frameLocator("iframe").getByLabel("Quantity:").click();
    page.frameLocator("iframe").getByLabel("Quantity:").fill("3");
    assertThat(page.frameLocator("iframe").getByLabel("Quantity:")).hasValue("3");
  }

  // Eye Icon
  @Test
  void revealingPasswordShouldStayEmpty() {
    page.navigate("https://documentation.webforj.com/docs/components/fields/passwordfield");
    assertThat(page.frameLocator("iframe").getByPlaceholder("Password")).isEmpty();
    page.frameLocator("iframe").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("eye")).click();
    assertThat(page.frameLocator("iframe").getByPlaceholder("Password")).isEmpty();
    page.frameLocator("iframe").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("eye off")).click();
  }

  @Test
  void passwordShouldBeHidden() {
    page.navigate("https://documentation.webforj.com/docs/components/fields/passwordfield");
    assertThat(page.frameLocator("iframe").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("eye"))).isVisible();
    assertThat(page.frameLocator("iframe").getByPlaceholder("Password")).isEmpty();
    page.frameLocator("iframe").getByPlaceholder("Password").click();
    page.frameLocator("iframe").getByPlaceholder("Password").fill("dafafaf");
    assertThat(page.frameLocator("iframe").getByPlaceholder("Password")).not().hasAttribute("hidden", "null");
  }

  @Test
  void passwordShouldBeShown() {
    page.navigate("https://documentation.webforj.com/docs/components/fields/passwordfield");
    assertThat(page.frameLocator("iframe").getByPlaceholder("Password")).isEmpty();
    page.frameLocator("iframe").getByPlaceholder("Password").click();
    page.frameLocator("iframe").getByPlaceholder("Password").fill("afadafa");
    assertThat(page.frameLocator("iframe").getByPlaceholder("Password")).not().hasAttribute("hidden", "null");
    page.frameLocator("iframe").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("eye")).click();
    assertThat(page.frameLocator("iframe").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("eye off"))).isVisible();
    assertThat(page.frameLocator("iframe").getByPlaceholder("Password")).hasValue("afadafa");
  }

  @Test
  void shouldSwitchSelectionInChoiceBox() {
    page.navigate("https://documentation.webforj.com/docs/components/list-components/choicebox");
    page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Dropdown Type").setExact(true)).click();
    assertThat(page.frameLocator("iframe >> nth=0").locator("dwc-button")).containsText("Electronics");
    page.frameLocator("iframe >> nth=0").getByLabel("chevron down").locator("svg").click();
    page.frameLocator("iframe >> nth=0").getByText("Fashion").click();
    assertThat(page.frameLocator("iframe >> nth=0").locator("dwc-button")).containsText("Fashion");
  }

  @Test
  void shouldOnlyLimitRows() {
    page.navigate("https://documentation.webforj.com/docs/components/list-components/choicebox#max-row-count");
    page.frameLocator("iframe >> nth=1").getByLabel("chevron down").locator("svg").click();
    assertThat(page.frameLocator("iframe >> nth=1").getByText("Arizona")).isVisible();
    page.frameLocator("iframe >> nth=1").getByLabel("chevron down").locator("svg").click();
    page.frameLocator("iframe >> nth=1").getByLabel("Number of Rows").click();
    page.frameLocator("iframe >> nth=1").getByLabel("Number of Rows").fill("2");
    page.frameLocator("iframe >> nth=1").getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Apply")).click();
    assertThat(page.frameLocator("iframe >> nth=1").getByText("Arizona")).isHidden();
  }

  @Test
  void shouldAllowCostumeInput() {
    page.navigate("https://documentation.webforj.com/docs/components/list-components/combobox#custom-value");
    page.frameLocator("iframe >> nth=0").getByLabel("Toggle Custom Value").check();
    assertThat(page.frameLocator("iframe >> nth=0").getByLabel("Toggle Custom Value")).isChecked();
    page.frameLocator("iframe >> nth=0").locator("#field-1").click();
    page.frameLocator("iframe >> nth=0").locator("#field-1").fill("Costume");
    assertThat(page.frameLocator("iframe >> nth=0").locator("#field-1")).hasValue("Costume");
  }

  @Test
  void shouldNotAllowCostumeInput() {
    page.navigate("https://documentation.webforj.com/docs/components/list-components/combobox#custom-value");
    page.frameLocator("iframe >> nth=0").locator("#field-1").click();
    page.frameLocator("iframe >> nth=0").locator("#field-1").press("CapsLock");
    page.frameLocator("iframe >> nth=0").locator("#field-1").press("CapsLock");
    assertThat(page.frameLocator("iframe >> nth=0").locator("#field-1")).isEmpty();
    assertThat(page.frameLocator("iframe >> nth=0").getByLabel("Toggle Custom Value")).not().isChecked();
  }

  @Test
  void shouldShowPlaceHolder() {
    page.navigate("https://documentation.webforj.com/docs/components/list-components/combobox#placeholder");
    assertThat(page.frameLocator("div >> internal:has-text=/^Show CodeComboBoxPlaceholder\\.javacomboBox\\.setPlaceholder\\(\"Example Placeholder\"\\);$/ >> iframe").getByPlaceholder("Example Placeholder")).isEmpty();
  }

  // How to assert selection
  @Test
  void shouldOnlyAllowOneSelection() {
    page.navigate("https://documentation.webforj.com/docs/components/list-components/listbox#selection-options");
    assertThat(page.frameLocator("iframe").getByLabel("Multiple Selection")).not().isChecked();
    page.frameLocator("iframe").getByText("IT Support").click();
    assertThat(page.frameLocator("iframe").getByText("IT Support")).isChecked();
    page.frameLocator("iframe").getByText("Management and Admin").click(new Locator.ClickOptions()
      .setModifiers(List.of(KeyboardModifier.CONTROL)));
    assertThat(page.frameLocator("iframe").getByText("IT Support")).isChecked();
  }

  @Test
  void shouldAllowMultipleSelection() {
    page.navigate("https://documentation.webforj.com/docs/components/list-components/listbox#selection-options");
    page.frameLocator("iframe").getByLabel("Multiple Selection").check();
    assertThat(page.frameLocator("iframe").getByLabel("Multiple Selection")).isChecked();
    page.frameLocator("iframe").getByText("IT Support").click();
    page.frameLocator("iframe").getByRole(AriaRole.OPTION, new FrameLocator.GetByRoleOptions().setName("Management and Admin")).click(new Locator.ClickOptions()
      .setModifiers(List.of(KeyboardModifier.CONTROL)));
    assertThat(page.frameLocator("iframe").getByText("IT Support")).isChecked();
    assertThat(page.frameLocator("iframe").getByText("Management and Admin")).isChecked();
  }

}
