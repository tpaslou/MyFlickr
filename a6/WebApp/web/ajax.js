function deleteUser() {
    "use strict";
    var username, password;
    if (document.cookie.length !== 0) {
        var value = document.cookie.split(";");
        var nameArr1 = value[0].split("=");
        var passArr2 = value[1].split("=");
        username = nameArr1[1];
        password = passArr2[1];
    } else {
        username = "";
        password = "";
    }
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'DeleteServ');
    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('deleteUser')
                    .innerHTML = xhr.responseText;
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.setRequestHeader('Content-type',
            'application/x-www-form-urlencoded');
    xhr.send('username=' + username + '&password=' + password);
}

function deletePIC(i) {

    "use strict";
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'clickndelete?id=' + i);
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById('deleteb')
                    .innerHTML = xhr.responseText;

        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.send();


}


function getID(i) {

    "use strict";
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'DeletePicServ?id=' + i);
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById('deleteb')
                    .innerHTML = xhr.responseText;

        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.send();


}


function Getuser() {

    "use strict";
    var username;
    if (document.cookie.length !== 0) {
        var value = document.cookie.split(";");
        var nameArr1 = value[0].split("=");
        username = nameArr1[1];
    } else {
        username = "";
    }
    var s = document.getElementById("user");
    s.value = username;
}



function UploadImageGet() {//UploadImage?user=userName&title=titleName&contentType=”image/jpg”

    "use strict";
    var username;
    if (document.cookie.length !== 0) {
        var value = document.cookie.split(";");
        var nameArr1 = value[0].split("=");
        username = nameArr1[1];
    } else {
        username = "";
    }


    var title = document.getElementById('Title').value;

    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'UploadImage?user=' + username + '&title=' + title + '&contentType=image/jpeg');
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById('ajaxContent')
                    .innerHTML = xhr.responseText;

        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.send();
}

function rate(id) {

    "use strict";

    var id1 = id;
    var rating;
    var radios = document.getElementsByName('rating');
    for (var i = 0; i < radios.length; i++) {
        if (radios[i].checked) {
            rating = radios[i].value;
        }
        var username;
        if (document.cookie.length !== 0) {
            var value = document.cookie.split(";");
            var nameArr1 = value[0].split("=");
            username = nameArr1[1];
        } else {
            username = "";


        }
    }
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'Rating?rate=' + rating + '&username=' + username + '&id=' + id1);
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {


        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.send();
}



function ImagesCollection() {
    "use strict";
    var username, number;
    if (document.cookie.length !== 0) {
        var value = document.cookie.split(";");
        var nameArr1 = value[0].split("=");
        username = nameArr1[1];
    } else {
        username = "";

    }
    number = document.getElementById('number').value;
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'GetImageCollection?user=' + username + '&number=' + number);
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {


            var jsn2 = JSON.parse(xhr.responseText);

            for (var i = 0; i < jsn2.id.length; i++) {
                GetImage(jsn2.id[i], false);

            }
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.send();
}//GetImage?image=id&metadata=true
function GetImage(id, metadata) {

    "use strict";
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'GetImage?image=' + id + '&metadata=' + metadata);
    xhr.responseType = 'arraybuffer';
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var uInt8Array = new Uint8Array(this.response);
            var i = uInt8Array.length;
            var binaryString = new Array(i);
            while (i--)
            {
                binaryString[i] = String.fromCharCode(uInt8Array[i]);
            }
            var data = binaryString.join('');

            var base64 = window.btoa(data);

            var div = document.createElement('div');
            div.setAttribute('class', 'box');
            var img = "data:image/png;base64," + base64;
            div.innerHTML = "<img id='" + id + "'src='" + img +
                    "'' height='144' width='103'><br><fieldset class=\"rating\"><input name=\"rating\" onclick=\"rate(" + id + ");\" type=\"radio\" id=\"star5\" name=\"rating\" value=\"5\" />\n\
<label class = \"full\" for=\"star5\" title=\"Awesome - 5 stars\"></label></label><input name=\"rating\" onclick=\"rate(" + id + ");\" type=\"radio\" id=\"star4\" name=\"rating\" value=\"4\" />\n\
<label class = \"full\" for=\"star4\" title=\Pretty good - 4 stars\"></label> <input name=\"rating\" onclick=\"rate(" + id + ");\" type=\"radio\" id=\"star3\" name=\"rating\" value=\"3\" />\n\
<label class = \"full\" for=\"star3\" title=\"Meh - 3 stars\"></label><input name=\"rating\" onclick=\"rate(" + id + ");\"  type=\"radio\" id=\"star2\" name=\"rating\" value=\"2\" />\n\
<label class = \"full\" for=\"star2\" title=\"Kinda bad - 2 stars\"></label><input name=\"rating\" onclick=\"rate(" + id + ");\"  type=\"radio\" id=\"star1\" name=\"rating\" value=\"1\" />\n\
<label class = \"full\" for=\"star1\" title=\"Sucks big time - 1 star\"></label> </fieldset>";



            div.setAttribute("onclick", 'TIV3335.showImageDetailedExifWithMap(\'' + id + '\',"form3"); getID(\'' + id + '\');');

            document.getElementById("name").insertBefore(div, null);

        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.send();
}

function tiv() {
    "use strict";
    var username, password;
    if (document.cookie.length !== 0) {
        var value = document.cookie.split(";");
        var nameArr1 = value[0].split("=");
        var passArr2 = value[1].split("=");
        username = nameArr1[1];
        password = passArr2[1];
    } else {
        username = "";
        password = "";
    }
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'TivServlet');
    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('form')
                    .innerHTML = xhr.responseText;
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.setRequestHeader('Content-type',
            'application/x-www-form-urlencoded');
    xhr.send('username=' + username + '&password=' + password);
}

function delete_cookie(name) {
    "use strict";
    document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}


function info() {
    "use strict";
    var username, password;

    if (document.cookie.length !== 0) {
        var value = document.cookie.split(";");
        var nameArr1 = value[0].split("=");
        var passArr2 = value[1].split("=");
        username = nameArr1[1];
        password = passArr2[1];
    } else {
        username = "";
        password = "";
    }
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'infoServ');
    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('wall')
                    .innerHTML = xhr.responseText;
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.setRequestHeader('Content-type',
            'application/x-www-form-urlencoded');

    xhr.send('username=' + username + '&password=' + password);


}
function list() {
    "use strict";
    var username, password;
    if (document.cookie.length !== 0) {
        var value = document.cookie.split(";");
        var nameArr1 = value[0].split("=");
        var passArr2 = value[1].split("=");
        username = nameArr1[1];
        password = passArr2[1];
    } else {
        username = "";
        password = "";
    }
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'ListServ');
    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('wall')
                    .innerHTML = xhr.responseText;
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.setRequestHeader('Content-type',
            'application/x-www-form-urlencoded');
    xhr.send('username=' + username + '&password=' + password);
}


function sendAjaxLogin() {

    "use strict";
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'LoginServ');
    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('ajaxContentLogin')
                    .innerHTML = xhr.responseText;
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.setRequestHeader('Content-type',
            'application/x-www-form-urlencoded');

    xhr.send('username=' + username + '&password=' + password);
}

function sendAjaxPOST() {
    "use strict";
    var gender;
    var radios = document.getElementsByName('gender');
    for (var i = 0; i < radios.length; i++) {
        if (radios[i].checked) {
            gender = radios[i].value;

        }
    }
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;
    var cpassword = document.getElementById('cpassword').value;
    var email = document.getElementById('email').value;
    var fname = document.getElementById('fname').value;
    var lname = document.getElementById('lname').value;
    var date = document.getElementById('birthday').value;

    var country = document.getElementById('country').value;
    var city = document.getElementById('city').value;
    var comments = document.getElementById('comments').value;


    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'Echo');
    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('ajaxContent')
                    .innerHTML = xhr.responseText;
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };

    xhr.setRequestHeader('Content-type',
            'application/x-www-form-urlencoded');

    xhr.send('username=' + username + '&password=' + password +
            '&cpassword=' + cpassword + '&email=' + email + '&fname=' +
            fname + '&lname=' + lname + '&date=' + date + '&gender=' + gender +
            '&country=' + country + '&city=' + city + '&comments=' + comments);

}

function imageDB() {
    "use strict";
    var username, password;
    if (document.cookie.length !== 0) {
        var value = document.cookie.split(";");
        var nameArr1 = value[0].split("=");
        var passArr2 = value[1].split("=");
        username = nameArr1[1];
        password = passArr2[1];
    } else {
        username = "";
        password = "";
    }
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'GetImageCollection');
    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('wall')
                    .innerHTML = xhr.responseText;
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.setRequestHeader('Content-type',
            'application/x-www-form-urlencoded');
    xhr.send('username=' + username + '&password=' + password);
}

function UserDetails(i) {
    "use strict";
    var username, number;
    username = document.getElementById(i).value;
    var number = 10;
    // number=num.toString();
    var xhr = new XMLHttpRequest();

    xhr.open('GET', 'GetImageCollection?user=' + username + '&number=' + number);
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {


            var jsn2 = JSON.parse(xhr.responseText);

            for (var i = 0; i < jsn2.id.length; i++) {
                GetImage(jsn2.id[i], false);
            }
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.send();
}