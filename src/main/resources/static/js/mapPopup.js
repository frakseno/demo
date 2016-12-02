angular.module('app.ui').controller('MapPopupController', function ($uibModalInstance, museum, restaurants) {
    var $mapPopupController = this;

    this.museum = museum;
    this.restaurant = restaurants;

    this.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };

    $uibModalInstance.rendered.then(function() {
        this.museumImage = "https://maxcdn.icons8.com/iOS7/PNG/25/Travel/museum_filled-25.png";
        this.restaurantImage = "https://maxcdn.icons8.com/iOS7/PNG/25/City/restaurant_filled-25.png";

        drawMap();

        addMarkers();
    });

    function drawMap() {
        var options = {
            center: new google.maps.LatLng(museum.latitude, museum.longitude),
            zoom: 1,
            disableDefaultUI: false
        }

        this.map = new google.maps.Map(document.getElementById("map"), options);

        this.places = new google.maps.places.PlacesService(this.map);

        this.bounds = new google.maps.LatLngBounds();
    }

    function addMarkers() {

        addMarker(museum);

        restaurants.forEach(addMarker);
    }

    function addMarker(building) {
        var buildingLocation = new google.maps.LatLng(building.latitude, building.longitude);

        this.bounds.extend(buildingLocation);


        var marker = new google.maps.Marker({
            map: this.map,
            // title: building.name,
            // label: building.name,
            position: buildingLocation,
            icon: building.buildingType == 'MUSEUM' ? this.museumImage : this.restaurantImage,
            animation: google.maps.Animation.DROP
        });


        // var infowindow = new google.maps.InfoWindow();
        // infowindow.setContent(building.name);
        // infowindow.open(this.map, marker);

        this.map.fitBounds(this.bounds);
        this.map.panToBounds(this.bounds);
    }
});