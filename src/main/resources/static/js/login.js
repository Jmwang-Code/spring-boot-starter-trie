const form = document.querySelector('form');
const usernameInput = document.getElementById('username');
const passwordInput = document.getElementById('password');

form.addEventListener('submit', e => {
    e.preventDefault();
    if (usernameInput.value === '' || passwordInput.value === '') {
        alert('Please enter both username and password!');
    } else if (usernameInput.value !== 'username' || passwordInput.value !== 'password') {
        alert('Invalid username or password!');
    } else {
        alert('Login successful!');
    }
});
