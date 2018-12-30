package com.github.appreciated;

import com.github.appreciated.calc.color.helper.CalculatedColorHelper;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import static com.github.appreciated.calc.color.helper.LumoVariables.LUMO_PRIMARY_TEXT_COLOR;

@Route("")
@Push
public class DemoView extends VerticalLayout {

    public DemoView() {
        CalculatedColorHelper helper = new CalculatedColorHelper();
        TextField field = new TextField();
        add(
                helper,
                field,
                new Button("Update", buttonClickEvent -> helper.getCalculatedColor(LUMO_PRIMARY_TEXT_COLOR, result -> field.setValue(result))),
                new Button("SetValue", buttonClickEvent -> helper.setCalculatedColor(LUMO_PRIMARY_TEXT_COLOR, field.getValue()))
        );
    }

}
