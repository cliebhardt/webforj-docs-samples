package layout_demos.helper;

import java.util.HashMap;
import java.util.Map;

import org.dwcj.App;
import org.dwcj.annotation.InlineStyleSheet;
import org.dwcj.component.layout.flexlayout.FlexLayout;
import org.dwcj.component.text.Label;
import org.dwcj.component.window.Window;
import org.dwcj.concern.HasStyle;
import org.dwcj.concern.legacy.LegacyHasStyle;

@InlineStyleSheet("context://css/flexstyles/box_styles.css")
public class Box extends FlexLayout {

  FlexLayout box;
  int num;
  String title = "";
  boolean vis = true;
  Label display = new Label();

  private Map<String, String> styles;

  @Override
  public void onCreate(Window p) {
    box = FlexLayout.create()
        .horizontal()
        .align().center()
        .justify().center()
        .build();
    p.add(box);

    if (title.isEmpty()) {
      title = "Box " + this.num;
    }
    display.setText(title);
    box.add(display).addClassName("demo__box");

    for (Map.Entry<String, String> entry : styles.entrySet()) {
      box.setStyle(entry.getKey(), entry.getValue());
    }
    if (!vis) {
      box.setVisible(false);
    }

  }

  @Override
  public FlexLayout setItemOrder(int order, LegacyHasStyle control) {
    App.consoleLog(String.valueOf(order) + " " + control.toString());
    super.setItemOrder(order, control);
    return this;
  }

  public Box(int num) {
    this.num = num;
  }

  public Box(String title) {
    this.title = title;
  }

  @Override
  public Box setStyle(String property, String value) {
    if (styles == null) {
      styles = new HashMap<>();
    }
    styles.put(property, value);
    return this;
  }

  @Override
  public Box setVisible(Boolean visible) {
    if (Boolean.TRUE.equals(this.isAttached())) {
    } else {
      box.setVisible(visible);
      this.vis = visible;
    }
    return this;
  }

  public void setDisplay(String text) {
    this.title = text;
  }

  public void boxDestroy() {
    box.destroy();
  }
}
