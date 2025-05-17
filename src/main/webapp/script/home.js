// src/main/webapp/js/homepage.js
document.addEventListener('DOMContentLoaded', function () {
    const cards = document.querySelectorAll('.card');
    cards.forEach(card => {
        card.addEventListener('click', function () {
            this.style.boxShadow = '0 12px 24px rgba(0, 0, 0, 0.3)';
        });
    });
});