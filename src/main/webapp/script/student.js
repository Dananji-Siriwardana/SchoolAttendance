document.querySelectorAll('.mark-attendance-btn').forEach(btn => {
    btn.addEventListener('click', () => {
        document.getElementById('attendanceModal').style.display = 'block';
    });
});
document.querySelector('.close-btn').addEventListener('click', () => {
    document.getElementById('attendanceModal').style.display = 'none';
});