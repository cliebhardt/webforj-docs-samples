package componentdemos.textcomboboxdemos;

import org.dwcj.App;
import org.dwcj.annotation.InlineStyleSheet;
import org.dwcj.component.button.Button;
import org.dwcj.component.list.ComboBox;
import org.dwcj.component.window.Frame;
import org.dwcj.exceptions.DwcjException;

@InlineStyleSheet("context://css/textcomboboxstyles/demo_styles.css")
public class ComboBoxCustomValue extends App {
  @Override
  public void run() throws DwcjException {
    Frame window = new Frame();
    window.addClassName("frame");

    ComboBox customValue = new ComboBox();
    Button toggle = new Button("Toggle Custom Value");

    window.add(customValue, toggle);
    customValue.setAllowCustomValue(false);

    toggle.onClick( e -> {
      customValue.setAllowCustomValue(!customValue.isAllowCustomValue());
      consoleLog(customValue.isAllowCustomValue()+"");
    });

  }
}
