/*
// Get a reference to the Log In button by its ID
const loginButton = document.getElementById("LogIn");

// Add a click event listener to the Log In button
loginButton.addEventListener("click", function() {
    // Create or modify HTML elements for the login form
    const loginForm = document.createElement("form");
    loginForm.innerHTML = `
        <h2>Login to Spotify</h2>
        <input type="text" placeholder="Username">
        <input type="password" placeholder="Password">
        <button type="submit">Submit</button>
    `;

 // Find the container element where you want to display the login form
 const container = document.getElementById("container");

 // Append the login form to the container
 container.appendChild(loginForm);

    // You can add more actions here, like handling the form submission
});
*/


        
   // /Users/andrew/Desktop/Spotify/SpotifyMusicStorage/IOTO - Map Of The Universe.mp3
    
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
