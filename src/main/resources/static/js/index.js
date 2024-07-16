// Capturando datos del formulario de inicio de sesión
const formulario = document.getElementById("inicioDeSesion");

formulario.addEventListener("submit", function(event) {
    event.preventDefault(); // Evita que el formulario se envíe de manera predeterminada

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    // Preparando los datos para enviar al backend
    const data = {
        email: email,
        password: password
    };

    // Realizando la solicitud POST al backend
    fetch("/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        if (data.redirectUrl) {
            window.location.href = data.redirectUrl; // Redirige al usuario a la página de inicio si el inicio de sesión es exitoso
        } else {
            alert("Email y/o contraseña incorrectos"); // Muestra un mensaje de error si las credenciales son incorrectas
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert("Error al intentar iniciar sesión. Por favor, inténtalo nuevamente.");
    });
});
er("submit", login)