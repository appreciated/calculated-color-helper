package com.github.appreciated;

import com.github.appreciated.ripple.PaperRippleDiv;
import com.github.appreciated.ripple.PaperRippleHorizontalLayout;
import com.github.appreciated.ripple.PaperRippleVerticalLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class DemoView extends VerticalLayout {

    public DemoView() {
        add(getLayout(new PaperRippleDiv()));
        add(getLayout(new PaperRippleHorizontalLayout()));
        add(getLayout(new PaperRippleVerticalLayout()));
        setAlignItems(Alignment.CENTER);
    }

    Component getLayout(Component component) {
        if (component instanceof HasComponents){
            ((HasComponents) component).add(new Label("Test"));
        }
        component.getElement().getStyle()
                .set("border", "1px solid black")
                .set("width", "300px")
                .set("height", "50px");
        return component;
    }
}
