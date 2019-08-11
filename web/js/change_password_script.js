
var password_regex = /(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])[A-Za-z0-9]{6,}/;

var password = document.forms['vform']['password'];
var repeat_password = document.forms['vform']['repeat_password'];


var form = document.getElementsByName("vform");

password.addEventListener('blur', passwordVerify.bind(this));
password.addEventListener('keyup', passwordVerify.bind(this));
var passwordOk = false;

repeat_password.addEventListener('blur', repeatPasswordVerify.bind(this));
repeat_password.addEventListener('keyup', repeatPasswordVerify.bind(this));
var repeatPasswordOk = false;

function allIsOk(){
    return repeatPasswordOk && passwordOk;
}
function passwordVerify(event) {
    if (event.target.value.match(password_regex)) {
        event.target.style.borderColor = "green";
        passwordOk = true;
        password.setCustomValidity("");
        allIsOk();
    } else {
        password.setCustomValidity("Пароль должен содржать хотя бы 1 заглавную букву, 1 цифру и быть длиной не менее 6 символов");
        event.target.style.borderColor = "red";
        passwordOk = false;
    }
    if(repeat_password.value){
        var repeat = { target: repeat_password}
        repeatPasswordVerify(repeat);
    }
}
function repeatPasswordVerify(event) {
    if (event.target.value.match(password_regex) && event.target.value === password.value) {
        event.target.style.borderColor = "green";
        repeatPasswordOk = true;
        repeat_password.setCustomValidity("");
        allIsOk();
    } else {
        repeat_password.setCustomValidity("Пароли не совпадают");
        event.target.style.borderColor = "red";
        repeatPasswordOk = false;
    }
}
