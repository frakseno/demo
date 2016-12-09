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

angular.module('app.ui').constant(
    'CONFIG', {
        'ICON': {
            'MUSEUM': '/icons/museum_filled-25.png',
            'RESTAURNT': '/icons/restaurant_filled-25.png'
        },
        'URL': {
            'MUSEUM': '/data/buildings/search/findByBuildingTypeOrderByName?buildingType=MUSEUM',
            'RESTAURNT': '/data/buildings/search/findByNeighborhoodNameAndBuildingTypeOrderByName?buildingType=RESTAURANT&neighborhoodName=',
            'INIT_DATA': 'http://localhost:8080/init'
        }
    });