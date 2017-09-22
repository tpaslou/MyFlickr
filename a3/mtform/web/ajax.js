function sendAjaxPOST() {

    "use strict";
    
    var gender, i, gender_radio = document.getElementsByName('gdr'), username = document.getElementById('uname').value,  mail = document.getElementById('email').value, password = document.getElementById('pass').value, password2 = document.getElementById('ver').value, fName = document.getElementById('fn').value, lName = document.getElementById('sn').value, date = document.getElementById('dt').value, country = document.getElementById('cntr').value, city = document.getElementById('ct').value, info = document.getElementById('inf').value;
   
    for (i = 0; i < gender_radio.length; i = i + 1) {
        if (gender_radio[i].checked) {
            gender = gender_radio[i].value;

        }
    }

    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'Echo');
    xhr.onload = function () {
        if (xhr.status === 200) {
            if (xhr.responseText.indexOf("Successful sign in.") !== -1) {

                alert("Successful sign in.");
                window.location.assign("user.html");

                document.getElementById('ajaxContent').innerHTML = xhr.responseText;
            } else {
                document.getElementById('ajaxContent').innerHTML = xhr.responseText;
            }
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.send('Username=' + username + '&Email=' + mail + '&Password=' + password + '&Verify=' + password2 +
            '&First Name=' + fName + '&Surname=' + lName + '&Date=' + date + '&Gender=' + gender + '&Country=' + country + '&City=' + city + '&More Info=' + info);
}


function sendNamePass() {
    
    "use strict";
    
    var username = document.getElementById('uname').value, password = document.getElementById('pass').value;

   
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'Echo6');
    xhr.onload = function () {
        if (xhr.status === 200) {

            if (xhr.responseText.indexOf("Logged In") !== -1) {

                alert("Logged In");
                window.location.assign("user.html");

                document.getElementById('ajaxContent1').innerHTML = xhr.responseText;
            } else {
                document.getElementById('ajaxContent1').innerHTML = xhr.responseText;
            }
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.send('Username=' + username + '&Password=' + password);

}

function getinformation() {

    "use strict";

    var button = document.getElementById('bi');

    button.onclick = function () {
        var div = document.getElementById('information');
        if (div.style.display !== 'none') {
            div.style.display = 'none';
        } else {
            div.style.display = 'block';
        }
    };

   var xhr = new XMLHttpRequest();
    xhr.open('POST', 'Echo3');
    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('information').innerHTML = xhr.responseText;

        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.send();

}

function getinformation1() {

    "use strict";

    var button = document.getElementById('bl');

    button.onclick = function () {
        var div = document.getElementById('information1');
        if (div.style.display !== 'none') {
            div.style.display = 'none';
        } else {
            div.style.display = 'block';
        }
    };


  var xhr = new XMLHttpRequest();
    xhr.open('POST', 'Echo4');
    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('information1').innerHTML = xhr.responseText;
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.send();

}


function edit() {

    "use strict";

    var gender, i, gender_radio = document.getElementsByName('gdr'),password = document.getElementById('pass').value, password2 = document.getElementById('ver').value, fName = document.getElementById('fn').value, lName = document.getElementById('sn').value, date = document.getElementById('dt').value, country = document.getElementById('cntr').value, city = document.getElementById('ct').value, info = document.getElementById('inf').value;
    
    for (i = 0; i < gender_radio.length; i = i + 1) {
        if (gender_radio[i].checked) {
            gender = gender_radio[i].value;

        }
    }


var xhr = new XMLHttpRequest();
    xhr.open('POST', 'Echo5');
    xhr.onload = function () {
        if (xhr.status === 200) {

            document.getElementById('information2').innerHTML = xhr.responseText;
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.send('Password=' + password + '&Verify=' + password2 +
            '&First Name=' + fName + '&Surname=' + lName + '&Date=' + date + '&Gender=' + gender + '&Country=' + country + '&City=' + city + '&More Info=' + info);

}
