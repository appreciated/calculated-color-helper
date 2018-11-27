package com.github.appreciated.ripple;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.dom.Style;

@Tag("paper-ripple")
@HtmlImport("bower_components/paper-ripple/paper-ripple.html")
public class PaperRipple extends Component {

    public PaperRipple() {
        this(false, false, false);
    }

    public PaperRipple(boolean circle, boolean recenters, boolean noink) {
        setCircle(circle);
        setRecenters(recenters);
        setNoInk(noink);
        setColor("var(--lumo-primary-color)");
    }

    public void setNoInk(boolean noink) {
        getElement().setAttribute("noink", noink);
    }

    public void setRecenters(boolean recenters) {
        getElement().setAttribute("recenters", recenters);
    }

    public void setCircle(boolean circle) {
        getElement().getClassList().set("circle", circle);
    }

    public void setColor(String color) {
        getElement().getStyle().set("color", color);
    }

}
