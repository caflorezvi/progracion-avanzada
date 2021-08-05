let map;
let markers;
let bounds;

window.onload = function (){
    if( "geolocation" in navigator) {
        navigator.geolocation.getCurrentPosition(position => {
            obtenerPosicion( [{name:"lng",value:position.coords.longitude},{name:"lat",value:position.coords.latitude}]) ;
        });
    }else{
        obtenerPosicion( [{name:"lng",value:-1},{name:"lat",value:-1}]) ;
    }
}

function crearMapa(lugares){
    mapboxgl.accessToken = 'pk.eyJ1IjoiY2FmbG9yZXp2aSIsImEiOiJja2EwZWF0YnEwMm82M2ZtazBvZWV3dHM1In0.YpYaeS5Y1RSvwBgVFvDMyA';

    markers = []
    bounds = new mapboxgl.LngLatBounds();
    inicializarMarkers(lugares);

    if(bounds.isEmpty()) {
        map = new mapboxgl.Map({
            container: 'map',
            style: 'mapbox://styles/mapbox/streets-v11?optimize=true',
            center: [-72.309, 4.473],
            zoom: 4.5
        });
    }else{
        map = new mapboxgl.Map({
            container: 'map',
            style: 'mapbox://styles/mapbox/streets-v11?optimize=true',
            center: [-72.309, 4.473],
            zoom: 11,
            bounds: bounds
        });
        map.fitBounds( bounds, { padding: 100 } );
    }

    map.on("load", function (e){
        markers.forEach(m => {
            m.addTo(map).togglePopup();
        });
    });

}

function inicializarMarkers(lugares){
    for(let l of lugares){
        markers.push(new mapboxgl.Marker().setLngLat([l.lng, l.lat]).setPopup( new mapboxgl.Popup().setHTML( "<strong>"+l.nombre+"</strong><br>"+l.descripcion+"<br> <a href='/detalleLugar.xhtml?lugar="+l.id+"'>Ir a detalle</a>" ) ));
        bounds.extend([l.lng, l.lat]);
    }
}

function actualizarMapa(lugares){
    console.log(lugares)
    for(let m of markers){
        m.remove();
    }
    markers = []
    bounds = new mapboxgl.LngLatBounds()
    inicializarMarkers(lugares);

    markers.forEach(m => {
        m.addTo(map).togglePopup();
    });
    map.fitBounds(bounds, { padding: 100 } );
}