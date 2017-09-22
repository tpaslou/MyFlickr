var counter=0;var num = 0;
var TIV3335=function(){
    var Array_of_Images = [];
    return {

        loadImages:function(){
            var files = document.getElementById("images").files;

            for (var i = 0; i < files.length; i++) {

                var file = files[i];

                if (file.name.match(/\.(jpg|jpeg|png|gif)$/)){

                    num++;
                    Array_of_Images.push(file);

                }
            }},
        getLoadedImages:function(){
            return Array_of_Images;
        },
        showLoadedImages:function(elem){
            if (num>=1){

                for (i = 0; i < Array_of_Images.length; i++) {

                    var reader = new FileReader();
                    counter=i;
                    // Closure to capture the file information.
                    reader.onload = (function(file,counter){

                        return function(e) {

                            // Render thumbnail
                            var div = document.createElement('div');
                            div.setAttribute('class', 'box');
                            var id=counter;
                            div.innerHTML = ['<img id="',id,'"src="', e.target.result,
                                             '" title="', escape(file.name), '" height="144" width="103" onClick="TIV3335.showImageDetailedExifWithMap(id,container); getID("',id,'");"> '].join('');
                            //parseInt(this.getattribute("id"))
                            document.getElementById(elem).insertBefore(div, null);

                        };         })(Array_of_Images[i],counter);
                    // Read in the image file as a data URL.
                    reader.readAsDataURL(Array_of_Images[i],i);

                }
            }else{

                var div = document.createElement('div');
                div.style.fontSize = "25px";
                div.innerHTML = "No Images";
                document.getElementById(elem).appendChild(div);

            }
        },
        showImage:function(index,elem){
            var modal = document.getElementById('myModal');
            var container = document.getElementById("big");
            modal.style.display = "block";
            var tmpArr=TIV3335.getLoadedImages()[index];
            
            container.innerHTML = ['<img class="bimg" src="' + document.getElementById(index).src +
                '" title="ela">'].join('');
            
            
            
        },
        showImageDetailedExifInfo:function(index, elem){
            
            var img2 = document.getElementById(index);
            EXIF.getData(img2, function() {
                var allMetaData = EXIF.getAllTags(this);
                var allMetaDataSpan = document.getElementById("allMetaDataSpan");
                allMetaDataSpan.innerHTML = JSON.stringify(allMetaData, null, "\t");
            });},
        
        
        
        showImageDetailedExifWithMap:function(index, elem){
            function ConvertDMSToDD(degrees, minutes, seconds, direction) {
                var dd = degrees + minutes/60 + seconds/(60*60);
                
            if (direction == "S" || direction == "W") {
                   dd = dd * -1;
                } // Don't do anything for N or E
                
                return dd;
            }
            TIV3335.showImage(index,elem);
            var img2 = document.getElementById(index);
            EXIF.getData(img2, function() {
                var lat=EXIF.getTag(this, "GPSLatitude")+'';
                var latdir=EXIF.getTag(this, "GPSLatitudeRef");
                var parts1=lat.split(/[^\d\w]+/);
                var lng= EXIF.getTag(this, "GPSLongitude")+'';   
                var lngdir=EXIF.getTag(this, "GPSLongitudeRef");
                var parts2 = lng.split(/[^\d\w]+/);
                var lat = ConvertDMSToDD(parseInt(parts1[0]), parseInt(parts1[1]), parseInt(parts1[2]), latdir);
                var lng = ConvertDMSToDD(parseInt(parts2[0]), parseInt(parts2[1]), parseInt(parts2[2]), lngdir); 
                console.log(lat);
                console.log(lng);
                var lat1=parseFloat(lat);
                var lng1=parseFloat(lng);
                var mapOptions = {
                    zoom: 8,
                    center: {lat:  lat1, lng: lng1},
                    scrollwheel: true,
                    zoomControl: true,
                    scaleControl: true
                };
                var map = new google.maps.Map(document.getElementById("map"), mapOptions);
                var marker = new google.maps.Marker({
                    position: {lat: lat1, lng: lng1},
                    map: map
                });
            });


TIV3335.showImageDetailedExifInfo(index,elem);
        },
        start:function(){
            TIV3335.loadImages();
            TIV3335.getLoadedImages();
            TIV3335.showLoadedImages('container');

        }
    };

}();
