function handleKeyPress(event) {
    if (event.key === 'Enter') {
        var inputElement = document.getElementById('searchQuery').value.trim();

        if (inputElement === '') {
            alert("We can't search for nothing unfortunately..");
        } else {
            alert("You pressed Enter with the value: " + inputElement);
        }

        event.preventDefault();
    }
}
