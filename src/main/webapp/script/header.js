// src/main/webapp/js/header.js
document.addEventListener('DOMContentLoaded', function () {
    const dropdownToggles = document.querySelectorAll('.header-dropdown-toggle');
    
    dropdownToggles.forEach(toggle => {
        toggle.addEventListener('click', function (e) {
            e.preventDefault();
            const menu = this.nextElementSibling;
            menu.classList.toggle('show');
        });
    });

    document.addEventListener('click', function (e) {
        dropdownToggles.forEach(toggle => {
            const menu = toggle.nextElementSibling;
            if (!toggle.contains(e.target) && !menu.contains(e.target)) {
                menu.classList.remove('show');
            }
        });
    });
});