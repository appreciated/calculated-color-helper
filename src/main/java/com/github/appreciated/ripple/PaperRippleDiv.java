package com.github.appreciated.ripple;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;

public class PaperRippleDiv extends Div {

    public PaperRippleDiv() {
        getStyle().set("position", "relative");
        add(new PaperRipple());
    }

    public PaperRippleDiv(Component... children) {
        this();
        this.add(children);
    }

    @Override
    public void removeAll() {
        super.removeAll();
        add(new PaperRipple());
    }
}
