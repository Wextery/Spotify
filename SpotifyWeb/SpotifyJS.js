
    
console.log('==========')

document.addEventListener('DOMContentLoaded', function () {
    const playButtons = document.querySelectorAll('.play-button');
    const audioPlayer = document.getElementById('audioPlayer');
    const audioSource = document.getElementById('audioSource');

    playButtons.forEach((playButton) => {
        console.log('Listener for ' + playButton);
        playButton.addEventListener('click', async function () {

            const musicId = playButton.dataset.id;
            const songUrl = `http://localhost:9090/spotify/song/stream/${musicId}`
            
            audioSource.src = songUrl
            audioPlayer.load()
            audioPlayer.play()
        });
    })

});

    document.addEventListener('DOMContentLoaded', function () {
        const registrationForm = document.getElementById('registrationForm');

        registrationForm.addEventListener('submit', function (event) {
            event.preventDefault();

            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;
            const username = document.getElementById('username').value;

            const data = {
                email: email,
                password: password,
                username: username
            };

            fetch('/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
            .then(response => response.json())
            .then(data => {
                if (data.message === 'Registration successful') {
                    window.location.href = 'MainSpotify.html';
                } else {
                    alert(data.message); 
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('An error occurred while registering.');
            });
        });
    });


    document.addEventListener('DOMContentLoaded', function () {
        const loginButton = document.getElementById('loginButton');
        const emailInput = document.getElementById('email');
        const passwordInput = document.getElementById('password');
    
        loginButton.addEventListener('click', function () {
            const email = emailInput.value;
            const password = passwordInput.value;
    
            if (!email || !password) {
                alert('Please enter both email and password.');
                return;
            }
    
            authenticateUser(email, password);
        });
    
        function authenticateUser(email, password) {
        
            fetch('/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ email, password }),
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert('Login successful. Redirecting to dashboard...');
                    window.location.href = '/MainSpotify.html'; 
                } else {
                    alert('Login failed. Please check your email and password.');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('An error occurred during login. Please try again later.');
            });
        }
    });
    
    
