import {PolymerElement} from '@polymer/polymer/polymer-element.js';

class CalcColorHelper extends PolymerElement {
    static get is() {
        return 'calculated-color-helper'
    }

    getValuesForCssAttributes(keys) {
        if (keys != null) {
            const values = [];
            for (var i = 0; i < keys.length; i++) {
                values.push({"key": keys[i], "value": getComputedStyle(this).getPropertyValue(keys[i]).trim()});
            }
            return values;
        }
    }
}

customElements.define(CalcColorHelper.is, CalcColorHelper);