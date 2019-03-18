let user = {
    id: "",
    journal: null,
};
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

class AirportName{
    constructor(from, to){
        this.from = from;
        this.to = to;
    }
}

//Functions
const signIn = () => {
    let signInUser = {
        email: $('#signInEmail').val(),
        password: $('#signUpPassword').val()
    };
    let data = JSON.stringify(signInUser);
    console.log(data);
    $.ajax({
        url: '/user/signin',
        type: 'post',
        contentType:"application/json;charset=UTF-8",
        data: data,
        success: (data) => {
            console.log("success");
            console.log(data);
        },
        error: (err) => {
            console.log(err);
            alert("Sign in failed");
        }
    });
    return false;
};
const signUp = () => {
    let signUpUser = {
        email: $('#signUpEmail').val(),
        password: $('#signUpPassword').val()
    };
    let data = JSON.stringify(signUpUser);
    console.log(data);
    $.ajax({
        url: '/user/signup',
        type: 'post',
        contentType:"application/json;charset=UTF-8",
        data: data,
        success: (data) => {
            console.log("success");
            console.log(data);
        },
        error: (err) => {
            console.log(err);
            alert("Sign up failed");
        }
    });
    return false;
};
const signOut = () => {
    let data = "";
    $.ajax({
        url: '/logout',
        type: 'post',
        contentType:"application/json;charset=UTF-8",
        data: data,
        success: (data) => {
            console.log("success");
            console.log(data);
        },
        error: (err) => {
            console.log(err);
            alert("Sign out failed");
        }
    });
};
const ticketOCR = (img) => {
    let result = {
        from: "",
        to: ""
    };
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
        url: "https://vision.googleapis.com/v1/images:annotate?key=[GOOGLE_VISION_API_KEY]",
        type: 'post',
        data: JSON.stringify(data),
        contentType: "application/json",
    });
};
const findAirportName = (fullText) => {
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
    return new AirportName(fromStr,toStr);
};

const findAirportLocation = (address) => {
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

const addJournalInGoogleMap = (location) => {
    let fromMarker = new google.maps.Marker({
        position: location.from,
        map: map
    });
    let toMarker = new google.maps.Marker({
        position: location.to,
        map: map
    });
    let flightPath = new google.maps.Polyline({
        path: [location.from,location.to],
        geodesic: true,
        strokeColor: '#FF0000',
        strokeOpacity: 1.0,
        strokeWeight: 2,
        map: map
    });
};

const newPost = async () => {
    let airportName = null;
    let location = {
        from: null,
        to: null
    };
    if(ticketImage != null){
        try{
            let data = await ticketOCR(ticketImage);
            airportName = findAirportName(data.responses[0].fullTextAnnotation.text);
            location.from = await findAirportLocation(airportName.from);
            location.to = await findAirportLocation(airportName.to);
            console.log(location);
            addJournalInGoogleMap(location);
        }
        catch (err){
            console.log(err);
            alert("failed");
        }
    }
    // let data = JSON.stringify();
    // $.ajax({
    //     url: '/journal',
    //     type: 'post',
    //     data: data,
    //     success: (data) => {

    //     },
    //     error: (err) => {
    //         console.log(err);
    //         alert("New post failed");
    //     }
    // });
    // ticketImage = null;
    // return false;
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
    xobj.open('GET', 'public/airport.json', true);
    xobj.onreadystatechange = function () {
        if (xobj.readyState === 4 && xobj.status === "200") {
            callback(xobj.responseText);
        }
    };
    xobj.send(null);
};

function initMap() {
    let mapCenterLocation = new GoogleMapCoordinate(-34.397, 150.644);
    map = new google.maps.Map(
        document.getElementById('map'),
        {
            zoom: 4,
            center: mapCenterLocation
        });
    geocoder = new google.maps.Geocoder();
}
//Events
$('#signInForm').submit(signIn);
$('#signUpForm').submit(signUp);
$('#signOutButton').click(signOut);
$('#newPostForm').submit(newPost);
$('#newPostButton').click(newPost);
$(document).ready(() => {
    loadJSON((response) => {
    airports = JSON.parse(response);
    });
});
//Validate
const validatePassword = (input) => {
    if(input.value !== $('#signUpPassword').val()){
        input.setCustomValidity("Please enter the same password again.");
    }else{
        input.setCustomValidity("");
    }
};