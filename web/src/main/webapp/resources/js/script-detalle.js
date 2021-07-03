window.onload = function () {
    mapboxgl.accessToken = 'pk.eyJ1IjoiY2FmbG9yZXp2aSIsImEiOiJja2EwZWF0YnEwMm82M2ZtazBvZWV3dHM1In0.YpYaeS5Y1RSvwBgVFvDMyA';

    let lat = document.getElementById("pos-lat-d").value
    let lng = document.getElementById("pos-lng-d").value

    let map = new mapboxgl.Map({
        container: 'mapa',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: [lng, lat],
        zoom: 14
    });

    map.addControl(new mapboxgl.GeolocateControl({
        positionOptions : {
            enableHighAccuracy : true
        },
        trackUserLocation : true
    }));

    map.addControl(new mapboxgl.NavigationControl());

    new mapboxgl.Marker().setLngLat([lng, lat]).setPopup( new mapboxgl.Popup().setHTML("Aquí está el lugar") ).addTo(map).togglePopup();
}