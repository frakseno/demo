angular.module('app.ui').controller('MapPopupController', function ($uibModalInstance, museum, restaurants, CONFIG, $q) {
    var $mapPopupController = this;

    $mapPopupController.geolocationError = false;
    $mapPopupController.errors = [];

    this.museum = museum;
    this.restaurant = restaurants;

    this.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };

    $uibModalInstance.rendered.then(function() {
        this.museumImage = CONFIG.ICON.MUSEUM;
        this.restaurantImage = CONFIG.ICON.RESTAURNT;

        initializeMap();

        addMarkers();
    });

    function initializeMap() {
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
        var address = building.address + ", " + building.city + ", " + building.state + " " + building.zipCode;

        search(address).then(
            function(res) { // success
                this.bounds.extend(res.geometry.location);


                var marker = new google.maps.Marker({
                    map: this.map,
                    position: res.geometry.location,
                    icon: building.buildingType == 'MUSEUM' ? this.museumImage : this.restaurantImage,
                    animation: google.maps.Animation.DROP
                });

                var infowindow = new google.maps.InfoWindow();
                infowindow.setContent(building.name);
                infowindow.open(this.map, marker);

                marker.addListener('click', function() {
                    infowindow.open(this.map, marker);
                });

                this.map.fitBounds(this.bounds);
                this.map.panToBounds(this.bounds);
            },
            function(status) { // error
                $mapPopupController.geolocationError = true;
                $mapPopupController.errors.push(address);
            }
        );
    }

    function search(str) {
        var d = $q.defer();
        this.places.textSearch({query: str}, function(results, status) {
            if (status == 'OK') {
                d.resolve(results[0]);
            }
            else d.reject(status);
        });
        return d.promise;
    }
});