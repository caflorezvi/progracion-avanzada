window.onload = function () {

    let params = obtenerParams();
    const lat = params[0]
    const lng = params[1]
    let myModal = document.getElementById('liveToast')

    if( "geolocation" in navigator) {
        navigator.geolocation.getCurrentPosition(position => {

            mapboxgl.accessToken = 'pk.eyJ1IjoiY2FmbG9yZXp2aSIsImEiOiJja2EwZWF0YnEwMm82M2ZtazBvZWV3dHM1In0.YpYaeS5Y1RSvwBgVFvDMyA';

            let map = new mapboxgl.Map({
                container: 'map',
                style: 'mapbox://styles/mapbox/streets-v11?optimize=true',
                center: [position.coords.longitude, position.coords.latitude],
                zoom: 14
            });

            map.addControl(new mapboxgl.GeolocateControl({
                positionOptions : {
                    enableHighAccuracy : true
                },
                trackUserLocation : true
            }));

            map.addControl(new mapboxgl.NavigationControl());

            let directions = new MapboxDirections({
                accessToken: mapboxgl.accessToken,
                unit: "metric"
            })

            map.addControl(directions, 'top-left');

            map.on("load", function (){
                directions.on("route", function (e) {
                    myModal.classList.add("show");
                    myModal.style.display = "block";
                    document.getElementById('distancia').innerHTML = (e.route[0].distance/1000).toFixed(2)+" Km"
                    document.getElementById('tiempo').innerHTML = (e.route[0].duration/60).toFixed(2)+ " minutos"
                });

                directions.setOrigin([position.coords.longitude, position.coords.latitude]);
                directions.setDestination([lng, lat]);
            })

        })
    }

}

function obtenerParams(){
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);

    const lat = urlParams.get('lat')
    const lng = urlParams.get('lng')

    return [lat, lng];
}