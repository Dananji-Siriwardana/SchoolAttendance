// src/main/webapp/js/attendance.js
document.addEventListener('DOMContentLoaded', function () {
    const deleteButtons = document.querySelectorAll('.btn-danger');
    deleteButtons.forEach(button => {
        button.addEventListener('click', function (e) {
            if (!confirm('Are you sure?')) {
                e.preventDefault();
            }
        });
    });
});