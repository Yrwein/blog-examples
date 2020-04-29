import './mydialog'

document.addEventListener("DOMContentLoaded", function () {
    const showDialogButton = document.getElementById('show-custom-dialog-button');
    showDialogButton.addEventListener('click', function () {
        document.getElementById('custom-dialog').showModal();
    });
});
