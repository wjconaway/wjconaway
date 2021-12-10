
window.addEventListener('load', async () => {
    console.log('EXECUTED');

    let res = await fetch('http://localhost:8080/checklogin', {
        method: 'GET',
        credentials: 'include'
    });


    if (res.status === 200){
        let userObj = await res.json();

        if (userObj.userroll === 'worker') {
            window.location.href = 'employee-home.html';
         } else if (userObj.userroll === 'manager') {
             window.location.href = 'manager-home.html';
        }
    }
});


let loginButton = document.querySelector('#login-btn');

loginButton.addEventListener('click', loginButtonClicked);


function loginButtonClicked(){
    login();
}

async function login(){

    let usernameInput = document.querySelector('#username');
    let passwordInput = document.querySelector('#password');

    try{
        let res = await fetch('http://localhost:8080/login', {
            method: 'POST',
            credentials: 'include',
            body: JSON.stringify({
              username: usernameInput.value,
             password: passwordInput.value
            })
        });

        let data = await res.json();

        if (res.status === 400){
           let loginErrorMessage = document.createElement('p');
           let loginDiv = document.querySelector('#login-info');

           loginErrorMessage.innerHTML = data.message;

           loginErrorMessage.style.color = 'red';
            
           loginDiv.appendChild(loginErrorMessage);

        }

        function myFunction() {
            var x = document.getElementById("password");
            if (x.type === "password") {
              x.type = "text";
            } else {
              x.type = "password";
            }
          }

        if (res.status === 200){
            console.log(data.userroll);
           

            if (data.userroll === 'worker') {
                window.location.href = 'employee-home.html';
            } else if (data.userroll === 'manager'){
                window.location.href = 'manager-home.html';
            }
        }

       
    } catch(err){
        console.log(err);
    }

}