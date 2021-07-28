function crearMapa(lugares){
    mapboxgl.accessToken = 'pk.eyJ1IjoiY2FmbG9yZXp2aSIsImEiOiJja2EwZWF0YnEwMm82M2ZtazBvZWV3dHM1In0.YpYaeS5Y1RSvwBgVFvDMyA';

    let bounds = new mapboxgl.LngLatBounds()
    let markers = []

    for(let l of lugares){
        markers.push(new mapboxgl.Marker().setLngLat([l.lng, l.lat]).setPopup( new mapboxgl.Popup().setHTML( "<strong>"+l.nombre+"</strong><br>"+l.descripcion+"<br> <a href='/detalleLugar.xhtml?lugar="+l.id+"'>Ir a detalle</a>" ) ));
        bounds.extend([l.lng, l.lat]);
    }

    if(bounds.isEmpty()) {
        var map = new mapboxgl.Map({
            container: 'map',
            style: 'mapbox://styles/mapbox/streets-v11?optimize=true',
            center: [-72.309, 4.473],
            zoom: 4.5
        });
    }else{
        var map = new mapboxgl.Map({
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