package com.github.appreciated.calc.color.helper;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.page.PendingJavaScriptResult;
import com.vaadin.flow.internal.JsonSerializer;
import elemental.json.impl.JreJsonArray;
import elemental.json.impl.JreJsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Tag("calculated-color-helper")
@JsModule("./com/github/appreciated/calculated-color-helper/helper.js")
public class CalculatedColorHelper extends Component {

    List<ResultListener<Map<String, String>>> waits = new ArrayList<>();

    public CalculatedColorHelper() {

    }

    /**
     * Convenience method for {@link CalculatedColorHelper#getCalculatedColors(String[], ResultListener)} )}. Will be slower when accessing multiple variables.
     *
     * @param cssAttribute string representing the name of the requested css-variable
     */
    public void getCalculatedColor(String cssAttribute, ResultListener<CssValuePair> listener) {
        this.getCalculatedColors(new String[]{cssAttribute}, result -> listener.onResult(result.size() == 1 ? result.get(0) : null));
    }

    /**
     * Since updating the property on the server side is not synchronously possible, it is necessary to wait for an
     * update to be triggered by the client side and the updated values to be sent to the server.
     *
     * @param cssAttribute strings representing the names of the requested css-variables
     */
    public void getCalculatedColors(String[] cssAttribute, ResultListener<ArrayList<CssValuePair>> listener) {
        PendingJavaScriptResult result = getElement().callJsFunction("getValuesForCssAttributes", JsonSerializer.toJson(cssAttribute));
        result.toCompletableFuture().whenCompleteAsync((jsonValue, throwable) -> {
                    ArrayList<CssValuePair> values = new ArrayList<>();
                    JreJsonArray array = (JreJsonArray) jsonValue;
                    for (int i = 0; i < array.length(); i++) {
                        JreJsonObject object = (JreJsonObject) array.getObject(i);
                        values.add(new CssValuePair(object.get("key").asString(), object.get("value").asString()));
                    }
                    listener.onResult(values);
                }
        );
    }

    /**
     * Sets the css-variable of the parent {{@link com.vaadin.flow.dom.Element}} of the {{@link CalculatedColorHelper}}
     *
     * @param cssVariableName  string representing the name of the css variable to be set
     * @param cssVariableValue string representing the value of the css variable to be set
     */
    public void setCalculatedColor(String cssVariableName, String cssVariableValue) {
        setCalculatedColors(new String[]{cssVariableName}, new String[]{cssVariableValue});
    }

    /**
     * Sets multiple css-variables of the parent {{@link com.vaadin.flow.dom.Element}} of the {{@link CalculatedColorHelper}}
     *
     * @param cssVariableNames  strings representing the names of the css variables to be set
     * @param cssVariableValues strings representing the values of the css variables to be set
     */
    public void setCalculatedColors(String[] cssVariableNames, String[] cssVariableValues) {
        getParent().ifPresent(component -> {
            for (int i = 0; i < cssVariableNames.length; i++) {
                component.getElement().getStyle().set(cssVariableNames[i], cssVariableValues[i]);
            }
        });
    }

    @FunctionalInterface
    public interface ResultListener<T> {
        void onResult(T result);
    }
}
