package componentdemos.navigatordemos;

import org.dwcj.App;
import org.dwcj.component.navigator.Navigator;
import org.dwcj.component.navigator.event.NavigatorChangeEvent;
import org.dwcj.component.window.Frame;
import org.dwcj.exceptions.DwcjException;

public class NavigatorBasic extends App {
  private int count = 0;

  @Override
  public void run() throws DwcjException {
    Navigator nav = new Navigator("Value: " + String.valueOf(count));
    nav.onChange(e -> {
      NavigatorChangeEvent.Direction direction = e.getDirection();
      switch (direction) {
        case NEXT:
          count++;
          break;
        case PREVIOUS:
          count--;
          break;
        case FIRST:
          count = 0;
          break;
        case LAST:
          count = 10;
          break;
        default:
          break;
      }

      if (count < 0) {
        count = 0;
      } else if (count > 10) {
        count = 10;
      }

      nav.setText("Value: " + String.valueOf(count));
    });

    Frame mainFrame = new Frame();
    mainFrame.add(nav);
  }
}

