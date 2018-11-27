package com.github.appreciated.ripple;

import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class PaperRippleHorizontalLayout extends HorizontalLayout implements ClickNotifier {
    public PaperRippleHorizontalLayout() {
        getStyle().set("position", "relative");
        add(getRipple());
    }

    private Component getRipple() {
        PaperRipple ripple = new PaperRipple();
        ripple.getElement().getStyle().set("margin", "0");
        return ripple;
    }

    public PaperRippleHorizontalLayout(Component... children) {
        this();
        this.add(children);
    }


    @Override
    public void removeAll() {
        super.removeAll();
        add(getRipple());
    }
}
