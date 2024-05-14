document.addEventListener('DOMContentLoaded', function() {
    const dropdownToggle2 = document.getElementById('dropdown-toggle2');
    const dropdownMenu2 = document.getElementById('dropdownMenu2');

    dropdownToggle2.addEventListener('click', function(event) {
        dropdownMenu2.style.display = dropdownMenu2.style.display === 'block' ? 'none' : 'block';
        event.preventDefault();
    });

    dropdownMenu2.addEventListener('click', function(event) {
        event.stopPropagation();
    });

    document.addEventListener('click', function(event) {
        if (!dropdownToggle2.contains(event.target) && !dropdownMenu2.contains(event.target)) {
            dropdownMenu2.style.display = 'none';
        }
    });

    function updateProfileInfo() {
        var photoProfilDis = /*[[${user != null ? user.photoProfil : ''}]]*/ '';

        document.getElementById('profilePicMenu').src = "imageUtilisateur/" + photoProfilDis;
    }

    /* ---------------------------------- Notification Button ----------------------------------- */

    const dropdownToggle1 = document.getElementById('dropdown-toggle1');
    const dropdownMenu1 = document.getElementById('dropdownMenu1');
    const notification = document.getElementById('notification');

    dropdownToggle1.addEventListener('click', function(event) {
        dropdownMenu1.style.display = dropdownMenu1.style.display === 'block' ? 'none' : 'block';
        event.preventDefault();
    });

    dropdownMenu1.addEventListener('click', function(event) {
        event.stopPropagation();
    });

    document.addEventListener('click', function(event) {
        if (!dropdownToggle1.contains(event.target) && !dropdownMenu1.contains(event.target)) {
            dropdownMenu1.style.display = 'none';
        }
    });

});