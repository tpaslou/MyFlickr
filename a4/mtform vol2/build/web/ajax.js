function register() {

    "use strict";

    var gender, i, gender_radio = document.getElementsByName('gdr'), username = document.getElementById('uname').value, mail = document.getElementById('email').value, password = document.getElementById('pass').value, password2 = document.getElementById('ver').value, fName = document.getElementById('fn').value, lName = document.getElementById('sn').value, date = document.getElementById('dt').value, country = document.getElementById('cntr').value, city = document.getElementById('ct').value, info = document.getElementById('inf').value;

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

                document.getElementById('registerContent').innerHTML = xhr.responseText;
            } else {
                document.getElementById('registerContent').innerHTML = xhr.responseText;
            }
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };

    xhr.send('Username=' + username + '&Email=' + mail + '&Password=' + password + '&Verify=' + password2 +
            '&First Name=' + fName + '&Surname=' + lName + '&Date=' + date + '&Gender=' + gender + '&Country=' + country + '&City=' + city + '&More Info=' + info);



}


function login() {

    "use strict";

    var username = document.getElementById('uname').value, password = document.getElementById('pass').value;


    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'Echo6');
    xhr.onload = function () {
        if (xhr.status === 200) {

            if (xhr.responseText.indexOf("Logged In") !== -1) {

                alert("Logged In");
                window.location.assign("user.html");

                document.getElementById('loginContent').innerHTML = xhr.responseText;
            } else {
                document.getElementById('loginContent').innerHTML = xhr.responseText;
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

function getlist() {

    "use strict";

    var button = document.getElementById('bl');

    button.onclick = function () {
        var div = document.getElementById('list');
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
            document.getElementById('list').innerHTML = xhr.responseText;
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.send();

}


function edit() {

    "use strict";

    var gender, i, gender_radio = document.getElementsByName('gdr'), password = document.getElementById('pass').value, password2 = document.getElementById('ver').value, fName = document.getElementById('fn').value, lName = document.getElementById('sn').value, date = document.getElementById('dt').value, country = document.getElementById('cntr').value, city = document.getElementById('ct').value, info = document.getElementById('inf').value;


    for (i = 0; i < gender_radio.length; i = i + 1) {
        if (gender_radio[i].checked) {
            gender = gender_radio[i].value;

        }
    }


    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'Echo5');
    xhr.onload = function () {
        if (xhr.status === 200) {

            if (xhr.responseText.indexOf("Successful edit.") !== -1) {


                window.location.assign("user.html");

                document.getElementById('editdiv').innerHTML = xhr.responseText;
            } else {
                document.getElementById('editdiv').innerHTML = xhr.responseText;
            }


        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };


    xhr.send('Password=' + password + '&Verify=' + password2 +
            '&First Name=' + fName + '&Surname=' + lName + '&Date=' + date + '&Gender=' + gender + '&Country=' + country + '&City=' + city + '&More Info=' + info);

}

function logout() {

    "use strict";

    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'Echo7');
    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('container2').innerHTML = xhr.responseText;
            document.getElementById("tiv").style.visibility = "hidden";
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.send();

}





var Counter = 0;
var Limit = 0;
var GPSLati;
var GPSLong;
var GPSLatiRef;
var GPSLongRef;
var North = [];
var North1;
var South = [];
var South1;
var metaDataJSON;
var infoShown = true;

var TIVcsd3240 = (function () {

    "use strict";

    function innergetLoadedImages() {
        function innerloadImages() {
            var Array_of_Images = [], i, files = document.getElementById("images").files, file;
            for (i = 0; i < files.length; i = i + 1) {
                file = files[i];
                if (file.name.match(/\.(jpg|jpeg|png|gif)$/)) {
                    Counter = Counter + 1;
                    Array_of_Images.push(file);
                }
            }
            return Array_of_Images;
        }
        return innerloadImages();
    }
    ;

    function innershowLoadedImages(elem) {

        var Array_of_Images = innergetLoadedImages(), container = document.getElementById(elem), id, i, reader, div, m;
        container.innerHTML = [];

        m = document.getElementById("map");
        m.innerHTML = [];

        if (Counter >= 1) {
            id = 0;

            for (i = 0; i < Array_of_Images.length; i = i + 1) {
                reader = new FileReader();
                /* Closure to capture the file information.*/
                reader.onload = (function (file) {

                    return function (e) {
                        /* Render thumbnail.*/
                        var div = document.createElement("div");

                        div.innerHTML = ['<img class="custom_img" id="' + id + '" src="' + e.target.result + '" title="' + escape(file.name) + '">'].join('');

                        div.setAttribute("onclick", 'showImage (\'' + id + '\',"container")');

                        document.getElementById(elem).insertBefore(div, null);

                        id = id + 1;
                    };
                }(Array_of_Images[i]));

                /* Read in the image file as a data URL.*/
                reader.readAsDataURL(Array_of_Images[i]);
            }
        } else {

            div = document.createElement("div");
            div.style.fontSize = "25px";
            div.innerHTML = "No Images";
            document.getElementById(elem).appendChild(div);
        }

    }
    ;
    return {
        loadImages: function () {
            innergetLoadedImages();
        },
        getLoadedImages: function () {
            return innergetLoadedImages();
        },
        showLoadedImages: function () {
            innershowLoadedImages("list");
        }
    };
}());


function start() {
    "use strict";

    var parent = document.getElementById('container'), child;
    child = document.getElementById('delp');

    if (child !== null) {

        parent.removeChild(child);
    }

    Counter = 0;
    TIVcsd3240.loadImages();
    TIVcsd3240.getLoadedImages();
    TIVcsd3240.showLoadedImages("list");

}



function showImage(index, elem) {

    "use strict";
    var Array_of_Images = TIVcsd3240.getLoadedImages(), child2, div, parent;

    parent = document.getElementById('container');
    child2 = document.getElementById('delp');

    if (child2 !== null) {
        parent.removeChild(child2);
    }

    div = document.createElement("div");

    div.innerHTML = ['<img id="' + index + '" src="' + document.getElementById(index).src +
                '" title="' + Array_of_Images[index].name + '">'].join('');


    div.setAttribute("id", "delp");

    div.innerHTML = div.innerHTML + ["<div id=\"metadata\"> <header class=\"headers\" ><h3>Info</h3> </header> <button onclick = \"showInfo()\" id=\"infoButton\">Hide</button><pre id=\"allMetaDataDiv\"> </pre></div>"].join('');


    document.getElementById(elem).insertBefore(div, null);

    Limit = 1;

    showImageDetailedExifInfo(index, elem);

}

function initMap(index, elem) {
    "use strict";

    var uluru = {lat: North1, lng: South1}, map, marker;
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 4,
        center: uluru
    });
    marker = new google.maps.Marker({
        position: uluru,
        map: map
    });
}


function showImageDetailedExifInfo(index, elem) {

    "use strict";
    getPicInfo(index);
    initMap();
    showImageDetailedExifWithMap(index, elem);
}

function showImageDetailedExifWithMap(index, elem) {

    "use strict";
    initMap(index, elem);
}


function getPicInfo(index) {
    "use strict";

    var elem = index, descr, allData, allDataDiv, longt, latit;
    descr = document.getElementById(elem);
    EXIF.getData(descr, function () {
        GPSLati = EXIF.getTag(this, "GPSLatitude");
        GPSLong = EXIF.getTag(this, "GPSLongitude");
        GPSLatiRef = EXIF.getTag(this, "GPSLatitudeRef");
        GPSLongRef = EXIF.getTag(this, "GPSLongitudeRef");
        allData = EXIF.getAllTags(this);
        allDataDiv = document.getElementById("allMetaDataDiv");

        metaDataJSON = JSON.stringify(allData, null, "\t");
        allMetaDataDiv.innerHTML = metaDataJSON;

    });


    if (GPSLatiRef === "W" || GPSLatiRef === "S") {
        GPSLati = "-" + GPSLati;
    }
    if (GPSLongRef === "W" || GPSLongRef === "S") {
        GPSLong = "-" + GPSLong;
    }

    North = GPSLati.toString().split(",");
    South = GPSLong.toString().split(",");
    North1 = +North[0] + +North[1] / 60 + +North[2] / 3600;
    South1 = +South[0] + +South[1] / 60 + +South[2] / 3600;
}

function showInfo() {
    "use strict";
    if (infoShown) {

        document.getElementById("allMetaDataDiv").innerHTML = "";
        document.getElementById("infoButton").innerHTML = "Show";

        infoShown = false;

    } else {

        document.getElementById("allMetaDataDiv").innerHTML = metaDataJSON;
        document.getElementById("infoButton").innerHTML = "Hide";

        infoShown = true;
    }


}