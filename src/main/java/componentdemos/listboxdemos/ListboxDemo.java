package componentdemos.listboxdemos;

import com.webforj.App;
import com.webforj.annotation.InlineStyleSheet;
import com.webforj.component.list.ListBox;
import com.webforj.component.list.MultipleSelectableList.SelectionMode;
import com.webforj.component.window.Frame;
import com.webforj.exceptions.WebforjException;

@InlineStyleSheet("context://css/listboxstyles/listbox_demo.css")
public class ListboxDemo extends App {

    @Override
    public void run() throws WebforjException {

        Frame window = new Frame();
        window.addClassName("frame");
        ListBox listBox = new ListBox();
        window.add(listBox);

        listBox.add("Financial Reports", "Financial Reports");
        listBox.add("Sales and Marketing Reports", "Sales and Marketing Reports");
        listBox.add("Operational Reports", "Operational Reports");
        listBox.add("Human Resources Reports", "Human Resources Reports");
        listBox.add("Compliance Reports", "Compliance Reports");

        listBox.setLabel("Select Desired Report Type(s)");
        listBox.setSelectionMode(SelectionMode.MULTIPLE);
    }
}
