package control_demos.dialogdemos;

import org.dwcj.App;
import org.dwcj.component.dialog.Dialog;
import org.dwcj.component.label.Label;
import org.dwcj.component.window.Frame;
import org.dwcj.exceptions.DwcjException;

public class DialogSections extends App {
  @Override
  public void run() throws DwcjException {
    Frame p = new Frame();
    Dialog dialog = new Dialog();
    p.add(dialog);

    dialog.getHeader().add(new Label("Header"));
    dialog.getContent().add(new Label("Content"));
    dialog.getFooter().add(new Label("Footer"));

    dialog.setCloseable(false);
    dialog.show();
  }
}