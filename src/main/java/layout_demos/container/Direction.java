package layout_demos.container;

import org.dwcj.App;
import org.dwcj.annotation.InlineStyleSheet;
import org.dwcj.component.choicebox.ChoiceBox;
import org.dwcj.component.choicebox.event.ChoiceBoxSelectEvent;
import org.dwcj.component.flexlayout.FlexDirection;
import org.dwcj.component.flexlayout.FlexLayout;
import org.dwcj.component.window.Frame;
import org.dwcj.exceptions.DwcjException;

import layout_demos.helper.Box;

@InlineStyleSheet("context://css/flexstyles/container_styles.css")
public class Direction extends App{

  FlexLayout boxLayout;

  @Override
  public void run() throws DwcjException{
    
    Frame page = new Frame();
    
    FlexLayout mainLayout = FlexLayout.create()
    .horizontal()
    .build();
    
    this.boxLayout = FlexLayout.create()
    .horizontal()
    .build()
    .addClassName("button__container--single-row");
    
    for(int i = 1; i <= 7; i++){
      String hue = String.valueOf((360/10) * i);
      Box newBox = new Box(i);
      newBox.setStyle("background", "hsla(" + String.valueOf(hue) + ", 50%, 75%, 0.25)");
      newBox.setStyle("border", "2px solid " + "hsl(" + String.valueOf(hue) + ", 50%, 35%)");
      newBox.setStyle("color", "hsl(" + String.valueOf(hue) + ", 50%, 25%)");
      boxLayout.add(newBox);
    }

    ChoiceBox directions = new ChoiceBox().onSelect(this::selectDirection)
    .addClassName("flex__options");
    directions.setAttribute("label", "Direction Options");
    for(FlexDirection justify : FlexDirection.values()){
      String label = justify.getValue();
      directions.addItem(
        "." + justify.toString()
          .toLowerCase() + "()", 
        label.substring(0, 1)
          .toUpperCase() + label
          .substring(1)
          );
      }
    directions.selectIndex(0);
    page.add(mainLayout);
    mainLayout.add(directions, boxLayout);    
  }

  private void selectDirection(ChoiceBoxSelectEvent ev){
    boxLayout.setDirection(FlexDirection.fromValue(ev.getControl().getSelectedItem().getValue()));
  }
}