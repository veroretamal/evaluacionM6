//Primer usuario valido
const userValido1 = "admin@alkewallet.com";
const passValida1 = "12345";

//Segundo usuario valido
const userValido2 = "admin2@alkewallet.com";
const passValida2 = "56789";

//Rescatando datos del form para inicio de sesion
const formulario = document.getElementById("inicioDeSesion");
const email = document.getElementById("email");
const password = document.getElementById("password");


function login(e){
    //previene el evento submit
    e.preventDefault();
    console.log(email.value);
    console.log(password.value);
        //valida si los datos capturados coinciden con el primer usuario valido
    if(email.value == userValido1 && password.value == passValida1){
        //para así dirigirlo al menu principal y settear nuestro item de inicioDeSesion
        alert("Login exitoso!");
        location.href = '../html/menu.html';
        sessionStorage.setItem("inicioDeSesion", 1);
    } else{
        //Si el primer usuario no es correcto prueba con el segundo y realiza los mismos pasos que el anterior
        if(email.value == userValido2 && password.value == passValida2){
            location.href = '../html/menu.html';
            sessionStorage.setItem("inicioDeSesion", 2);
        }else{
            //si ninguno de lo usuarios coincidio muestra el mensaje de error.
            alert("Email y/o contraseña erroneos");
        }
    }

}

//ejecuta la funcion login al detectar el submit del formulario inicioDeSesion
formulario.addEventListener("submit", login)