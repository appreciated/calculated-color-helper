import {PolymerElement} from '@polymer/polymer/polymer-element.js';

class CalcColorHelper extends PolymerElement {
    static get is() {
        return 'calculated-color-helper'
    }

    static get properties() {
        return {
            names: String,
            values: String
        }
    }

    getValuesForCssAttributes() {
        if (this.names != null) {
            console.log(this.names);
            var variables = Object.keys(JSON.parse(this.names));
            var keys = Object.keys(JSON.parse(this.names));
            console.log(keys);
            for (var i = 0; i < keys.length; i++) {
                variables[i] = getComputedStyle(this).getPropertyValue(variables[i]).trim();
            }
            this.values = JSON.stringify(variables)
            console.log(this.values);
            this.dispatchEvent(new CustomEvent('values-changed'));
        }
    }
}

customElements.define(CalcColorHelper.is, CalcColorHelper);