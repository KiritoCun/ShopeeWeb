function login(){
    var process = document.getElementsByClassName("modal");
    var login = document.getElementsByClassName("form-login");
    var register = document.getElementsByClassName("form-register");
    process[0].className = "modal active";
    if(register[0].className.includes("active")) {
        register[0].className = "auth-form form-register";
    }    
    login[0].className = "auth-form form-login active";  
}

function register(){
    var process = document.getElementsByClassName("modal");
    var login = document.getElementsByClassName("form-login");
    var register = document.getElementsByClassName("form-register");
    process[0].className = "modal active";       
    if(login[0].className.includes("active")) {
        login[0].className = "auth-form form-login";      
    } 
    register[0].className = "auth-form form-register active";   
}

