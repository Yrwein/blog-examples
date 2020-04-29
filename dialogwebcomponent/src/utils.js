export function convertTemplateStringToDomElement(templateString) {
    const helperElement = document.createElement('div');
    helperElement.innerHTML = templateString.trim();
    return helperElement.firstChild;
}
