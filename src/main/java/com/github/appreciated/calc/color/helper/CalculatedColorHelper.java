package com.github.appreciated.calc.color.helper;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import org.json.JSONObject;

import java.util.*;

@Tag("calculated-color-helper")
@JsModule("./com/github/appreciated/calculated-color-helper/helper.js")
public class CalculatedColorHelper extends Component {

    List<ResultListener<Map<String, String>>> waits = new ArrayList<>();

    public CalculatedColorHelper() {
        getElement().addPropertyChangeListener("values", "values-changed", domEvent -> {
            if (!waits.isEmpty()) {
                String value = getElement().getProperty("values");
                System.out.println(value);
                waits.get(0).onResult(toStringArray(value));
                waits.remove(waits.get(0));
            }
        });
    }

    private Map<String, String> toStringArray(String cssAttribute) {
        Map<String, String> values = new HashMap<>();

        return values;
    }

    /**
     * Convenience method for {@link CalculatedColorHelper#getCalculatedColor(ResultListener, String...)}. Will be slower when accessing multiple variables.
     *
     * @param listener
     * @param cssAttribute
     */
    public void getCalculatedColor(ResultListener<String> listener, String cssAttribute) {
        getCalculatedColor((ResultListener<Map<String, String>>) result -> result.keySet().stream().findFirst().ifPresent(s -> listener.onResult(s)), cssAttribute);
    }

    /**
     * Since updating the property on the server side is not synchronously possible, it is necessary to wait for an
     * update to be triggered by the client side and the updated values to be sent to the server.
     *
     * @param cssAttribute
     * @param listener
     */
    public void getCalculatedColor(ResultListener<Map<String, String>> listener, String... cssAttribute) {
        waits.add(listener);
        getElement().setProperty("names", toJsonArray(cssAttribute));
        getElement().callJsFunction("getValuesForCssAttributes");
    }

    private static String toJsonArray(String... cssAttribute) {
        JSONObject array = new JSONObject();
        Arrays.stream(cssAttribute).forEach(s -> array.append(s, null));
        return array.toString();
    }

    public void setCalculatedColor(String cssAttribute, String cssAttributeValues) {
        getParent().ifPresent(component -> component.getElement().getStyle().set(cssAttribute, cssAttributeValues));
    }

    @FunctionalInterface
    public interface ResultListener<T> {
        void onResult(T result);
    }
}
