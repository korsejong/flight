let ticketImage = null;
let airports = [];
let map;
let geocoder;
class GoogleMapCoordinate{
    constructor(lat, lng){
        this.lat = lat;
        this.lng = lng;
    }
}
class Path{
    constructor(options){
        this.fromCityName = options.fromCityName || null;
        this.toCityName = options.toCityName || null;
        this.fromCoordinate = options.fromCoordinate || null;
        this.toCoordinate = options.toCoordinate || null;
    }
}
const ticketOCR = (img) => {
    img = img.replace('data:image/jpeg;base64,', '');
    let data = {
        requests: [{
            image: {
                content: img
            },
            features: [{
                type: "TEXT_DETECTION",
                maxResults: 1
            }]
        }]
    };
    return $.ajax({
        url: "https://vision.googleapis.com/v1/images:annotate?key=[APIKEY]",
        type: 'post',
        data: JSON.stringify(data),
        contentType: "application/json",
    });
};
const findCityName = (fullText) => {
    fullText = fullText.replace(/ /gi, "");
    let set = new Set();
    for(let airport of airports){
        if(fullText.indexOf(airport) > -1){
            set.add(airport);
        }
    }
    let fromStr = "";
    let toStr = "";
    for(let e of set){
        if(fromStr.length === 0 || e.indexOf(fromStr) > -1){
            fromStr = e;
        }else{
            if(toStr.length === 0 || e.indexOf(toStr) > -1){
                toStr = e;
            }
        }
    }
    return new Path({fromCityName: fromStr,toCityName: toStr});
};
const findCoordinate = (address) => {
    return new Promise(function(resolve, reject) {
        geocoder.geocode({'address': address}, function(results, status) {
            if (status === 'OK') {
                resolve(new GoogleMapCoordinate(results[0].geometry.location.lat(), results[0].geometry.location.lng()));
            } else {
                reject(new Error('Couldnt\'t find the location ' + address));
            }
        });
    });
};
const addPathInGoogleMap = (path) => {
    let fromMarker = new google.maps.Marker({
        position: path.fromCoordinate,
        map: map
    });
    let toMarker = new google.maps.Marker({
        position: path.toCoordinate,
        map: map
    });
    let flightPath = new google.maps.Polyline({
        path: [path.fromCoordinate, path.toCoordinate],
        geodesic: true,
        strokeColor: '#FF0000',
        strokeOpacity: 1.0,
        strokeWeight: 2,
        map: map
    });
};
const readURL = (input) => {
    if (input.files && input.files[0]) {
        let reader = new FileReader();
        reader.onload = (e) => {
            ticketImage = e.target.result;
            $('#ticketImage').attr('src', ticketImage);
        };
        reader.readAsDataURL(input.files[0]);
    }
};
const loadJSON = (callback) => {
    let xobj = new XMLHttpRequest();
    xobj.overrideMimeType("application/json");
    xobj.open('GET', '/public/airport.json', true);
    xobj.onreadystatechange = function () {
        if (xobj.readyState === 4 && xobj.status === 200) {
            callback(xobj.responseText);
        }
    };
    xobj.send(null);
};
//Validate
const validatePassword = (input) => {
    if(input.value !== $('#signUpPassword').val()){
        input.setCustomValidity("Please enter the same password again.");
    }else{
        input.setCustomValidity("");
    }
};
function initMap() {
    let mapCenterLocation = new GoogleMapCoordinate(37.642445, 127.07222);
    map = new google.maps.Map(
        document.getElementById('map'),
        {
            zoom: 4,
            center: mapCenterLocation
        });
    geocoder = new google.maps.Geocoder();
}

$(document).ready(() => {
    loadJSON((response) => {
        airports = JSON.parse(response);
    });
});