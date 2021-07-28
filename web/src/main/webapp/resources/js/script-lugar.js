window.onload = function (){

    if( "geolocation" in navigator) {
        navigator.geolocation.getCurrentPosition(position => {
            cargarMapa(position.coords.latitude, position.coords.longitude, 14)
        })
    }else{
        cargarMapa(4.52892, -75.6775, 4.5)
    }
}

function cargarMapa(lat, lng, zoom){
    let enable = true;
    mapboxgl.accessToken = 'pk.eyJ1IjoiY2FmbG9yZXp2aSIsImEiOiJja2EwZWF0YnEwMm82M2ZtazBvZWV3dHM1In0.YpYaeS5Y1RSvwBgVFvDMyA';

    let map = new mapboxgl.Map({
        container: 'mapa',
        style: 'mapbox://styles/mapbox/streets-v11?optimize=true',
        center: [lng, lat],
        zoom: zoom
    });

    map.addControl(new mapboxgl.GeolocateControl({
        positionOptions: {
            enableHighAccuracy: true
        },
        trackUserLocation: true
    }));

    map.addControl(new mapboxgl.NavigationControl());

    map.on("click", function (e){
        if(enable) {
            setLtnLng(e.lngLat.lat, e.lngLat.lng)
            enable = false;

            let marker = new mapboxgl.Marker({
                draggable: true
            }).setLngLat([e.lngLat.lng, e.lngLat.lat]).addTo(map);

            marker.on("dragend", function () {
                var lngLat = marker.getLngLat();
                setLtnLng(lngLat.lat, lngLat.lng)
            })
        }
    });
}

function setLtnLng(lat, lng){
    document.getElementById("crear-lugar:lat-lugar").value = lat
    document.getElementById("crear-lugar:lng-lugar").value = lng
}
