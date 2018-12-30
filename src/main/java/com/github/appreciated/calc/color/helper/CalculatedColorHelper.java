package com.github.appreciated.calc.color.helper;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;

import java.util.ArrayList;
import java.util.List;

@Tag("calculated-color-helper")
@HtmlImport("frontend://com/github/appreciated/calculated-color-helper/helper.html")
public class CalculatedColorHelper extends Component {

    List<ResultListener<String>> waits = new ArrayList<>();

    public CalculatedColorHelper() {
        getElement().synchronizeProperty("value", "value-changed");
        getElement().addEventListener("value-changed", domEvent -> {
            if (!waits.isEmpty()) {
                waits.get(0).onResult(getElement().getProperty("value"));
                waits.remove(waits.get(0));
            }
        });
    }

    /**
     * Since updating the property on the server side is not synchronously possible, it is necessary to wait for an
     * update to be triggered by the client side and the updated values to be sent to the server.
     *
     * @param cssAttribute
     * @param listener
     */
    public void getCalculatedColor(String cssAttribute, ResultListener<String> listener) {
        waits.add(listener);
        getElement().setProperty("name", cssAttribute);
        getElement().callFunction("getValueForCssAttribute");
    }

    public void setCalculatedColor(String cssAttribute, String cssAttributeValue) {
        getParent().ifPresent(component -> component.getElement().getStyle().set(cssAttribute, cssAttributeValue));
    }

    @FunctionalInterface
    public interface ResultListener<T> {
        void onResult(T result);
    }
}
