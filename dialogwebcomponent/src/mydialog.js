
import dialogPolyfill from 'dialog-polyfill'
import myHTMLDialogTemplateString from './mydialog.html'

const helperElement = document.createElement('div');
helperElement.innerHTML = myHTMLDialogTemplateString.trim();
const myHTMLDialogTemplate = helperElement.firstChild;

// without polyfill it might be good to extend HTMLDialogElement
class MyHTMLDialogElement extends HTMLElement {
    constructor() {
        super();
        console.log(myHTMLDialogTemplate);
        const clonedContent = myHTMLDialogTemplate.content.cloneNode(true);
        this.attachShadow({mode: 'open'}).appendChild(clonedContent);

        this.dialogElement = this.shadowRoot.querySelector('dialog');

        const closeDialogButton = this.shadowRoot.querySelector('.close-button');
        closeDialogButton.addEventListener('click', this.close.bind(this));

        // polyfill workaround
        dialogPolyfill.registerDialog(this.dialogElement);
    }

    get returnValue() {
        return this.dialogElement.returnValue;
    }

    set returnValue(newReturnValue) {
        this.dialogElement.returnValue = newReturnValue;
    }

    show() {
        this.dialogElement.show();
    }

    showModal() {
        this.dialogElement.showModal();
    }

    close() {
        this.dialogElement.close();
    }

    addEventListener(...args) {
        this.dialogElement.addEventListener(...args);
    }
}

customElements.define('my-dialog', MyHTMLDialogElement);
