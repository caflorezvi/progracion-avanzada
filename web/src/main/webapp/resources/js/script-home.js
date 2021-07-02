window.onload = function () {

    if( "geolocation" in navigator) {
        navigator.geolocation.getCurrentPosition(position => {
            createMap(position.coords.latitude, position.coords.longitude);
        });
    }else{
        createMap(4.473, -72.309);
    }
}

function createMap(lat, lng){
    mapboxgl.accessToken = 'pk.eyJ1IjoiY2FmbG9yZXp2aSIsImEiOiJja2EwZWF0YnEwMm82M2ZtazBvZWV3dHM1In0.YpYaeS5Y1RSvwBgVFvDMyA';

    var map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: [lng, lat],
        zoom: 12
    });

    map.addControl(new mapboxgl.GeolocateControl({
        positionOptions: {
            enableHighAccuracy: true
        },
        trackUserLocation: true
    }));

    map.addControl(new mapboxgl.NavigationControl());
}