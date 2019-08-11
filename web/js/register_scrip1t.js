//VAR MACHING REGEX
var login_regex = /^([A-Za-z])[\w+]{5,}/;
var password_regex = /(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])[A-Za-z0-9]{6,}/;  
var email_regex = /^.+@.+\..+$/;
var date_regex = /^[0-9]{4}-[0-9]{2}-[0-9]{2}/;
// SELECTING ALL TEXT ELEMENTS
var username = document.forms['vform']['login'];
var email = document.getElementById("mailID");
var password = document.forms['vform']['password'];
var repeat_password = document.forms['vform']['repeat_password'];
var age = document.forms['vform']['age'];

var form = document.getElementsByName("vform");

// SETTING ALL EVENT LISTENERS
username.addEventListener('keyup', nameVerify.bind(this));
username.addEventListener('blur', nameVerify.bind(this));
var usernameOk = false;


email.addEventListener('keyup', emailVerify.bind(this));
var allEmailsOk = false;


password.addEventListener('blur', passwordVerify.bind(this));
password.addEventListener('keyup', passwordVerify.bind(this));
var passwordOk = false;

repeat_password.addEventListener('blur', repeatPasswordVerify.bind(this));
repeat_password.addEventListener('keyup', repeatPasswordVerify.bind(this));
var repeatPasswordOk = false;

age.addEventListener('blur', ageVerify.bind(this));
age.addEventListener('keyup', ageVerify.bind(this));
var ageOk = false;

// validation function
function public_static_void_main() {
  form.reset();
}
function allIsOk(){
    return usernameOk && allEmailsOk && repeatPasswordOk && passwordOk && ageOk;
}
// event handler functions
function nameVerify(event) {
  if (event.target.value.match(login_regex)) {
   event.target.style.borderColor = "green";
   usernameOk = true;
   username.setCustomValidity("");
   allIsOk();
  } else {
    username.setCustomValidity("Логин должен иметь не менее 6 символов");
    event.target.style.borderColor = "red";
    usernameOk = false;
  }
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
function emailVerify(event) {
  if(event.target.value.match(email_regex)){
    event.target.style.borderColor = "green";
    allEmailsOk = true;
    email.setCustomValidity("");
    allIsOk();
  } else {
      email.setCustomValidity("Email не имеет не верный формат");
     event.target.style.borderColor = "red";
     allEmailsOk = false;
  }
}

function ageVerify(event){	
	if(new Date(age.value).valueOf() < new Date(2014, 07, 31).valueOf() && new Date(age.value).valueOf()
        > new Date(1900, 01, 01).valueOf() && age.value.length==10 && event.target.value.match(date_regex)){
        event.target.style.borderColor = "green";
        ageOk = true;
        age.setCustomValidity("");
        allIsOk();
  } else {

        age.setCustomValidity("Вам должно быть не менее 8 и не более 120 лет. Формат: yyyy-mm-dd");
        event.target.style.borderColor = "red";
        ageOk = false;
  }
} 