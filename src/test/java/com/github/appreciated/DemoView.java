package com.github.appreciated;

import com.github.appreciated.calc.color.helper.CalculatedColorHelper;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.Command;

import static com.github.appreciated.calc.color.helper.LumoVariables.LUMO_PRIMARY_TEXT_COLOR;

@Route("")
@Push
public class DemoView extends VerticalLayout {

    public DemoView() {
        VaadinIcon.CLOSE_SMALL.create();
        CalculatedColorHelper helper = new CalculatedColorHelper();
        TextField field = new TextField();
        add(
                helper,
                field,
                new Button("Update", (ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
                    helper.getCalculatedColor(LUMO_PRIMARY_TEXT_COLOR, result -> {
                        DemoView.this.getUI().ifPresent(ui -> ui.access((Command) () -> {
                            System.out.println("getCalculatedColor: " + result);
                            field.setValue(result.getValue());
                        }));
                    });
                }),
                new Button("SetValue", buttonClickEvent -> helper.setCalculatedColor(LUMO_PRIMARY_TEXT_COLOR, field.getValue()))
        );
    }

}
