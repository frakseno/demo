angular.module('app.ui', ['ngAnimate', 'ngSanitize', 'ui.bootstrap', 'ngResource', 'ngRoute', 'spring-data-rest',
        'angularUtils.directives.dirPagination']);

angular.module('app.ui').filter('captureItems', function() {
    return function(array, property, target) {
        if (target && property) {
            target[property] = array;
        }
        return array;
    }
});
//
// angular.module('app.ui').service('Map', function($q) {
//     this.init = function(latitude, longitude) {
//         var options = {
//             center: new google.maps.LatLng(latitude, longitude),
//             zoom: 11,
//             disableDefaultUI: true
//         }
//
//         this.map = new google.maps.Map(
//             document.getElementById("map"), options
//         );
//
//         this.places = new google.maps.places.PlacesService(this.map);
//     }
//
//     this.search = function(str) {
//         var d = $q.defer();
//         this.places.textSearch({query: str}, function(results, status) {
//             if (status == 'OK') {
//                 d.resolve(results[0]);
//             }
//             else d.reject(status);
//         });
//         return d.promise;
//     }
//
//     this.addMarker = function(res) {
//         if(this.marker) this.marker.setMap(null);
//         this.marker = new google.maps.Marker({
//             map: this.map,
//             position: res.geometry.location,
//             animation: google.maps.Animation.DROP
//         });
//         this.map.setCenter(res.geometry.location);
//     }
//
// });
