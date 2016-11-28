angular.module('app.ui', ['ngAnimate', 'ngSanitize', 'ui.bootstrap', 'ngResource', 'ngRoute', 'spring-data-rest',
    'angularUtils.directives.dirPagination']);

angular.module('app.ui').controller('navController', function ($scope, $uibModal, $log) {
    $scope.openResetDataConfirm = function () {
        $log.info('opening pop up');
        var modalInstance = $uibModal.open({
            templateUrl: 'resetDataConfirm.html',
            controller: 'popupController',
        });

        modalInstance.result.then(function (resetData) {
            $log.info("Reset the Data");
        });
    }
});

angular.module('app.ui').controller('popupController', function ($scope, $uibModalInstance) {
    $scope.ok = function () {
        $uibModalInstance.close(true);
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});


angular.module('app.ui').controller('dataController', function ($scope, $http, SpringDataRestAdapter, $log) {
    $scope.loadButtonHidden = true;
    $scope.restaurantPanelHidden = true;
    $scope.selectedNeighborhood = '';

    function loadMuseums() {
        var httpPromise = $http.get('/data/buildings/search/findByBuildingTypeOrderByName?buildingType=MUSEUM').success(function (response) {
            // $scope.response = angular.toJson(response, true);
        });

        SpringDataRestAdapter.process(httpPromise, 'neighborhood', true).then(function (processedResponse) {
            $scope.museums = processedResponse._embeddedItems;
            // $scope.processedResponse = angular.toJson(processedResponse, true);
        });
    }

    loadMuseums();

    function loadRestaurantForNeighborhood(neighborhood) {
        $log.info("Selected neighborhood: " + neighborhood);

        $http.get('/data/buildings/search/findByNeighborhoodNameAndBuildingTypeOrderByName?buildingType=RESTAURANT&neighborhoodName=' + neighborhood).
            success(function (data) {
            $scope.restaurants = data._embedded.buildings;

            $log.info("restaurants: " + data);
        });
    }

    $scope.loadRestaurants = function() {
        loadRestaurantForNeighborhood($scope.selectedNeighborhood);

        $scope.restaurantPanelHidden = false;
    }

    $scope.museumSort = function(keyname){
        $scope.museumSortKey = keyname;   //set the sortKey to the param passed
        $scope.museumReverse = !$scope.museumReverse; //if true make it false and vice versa
    }

    $scope.restaurantSort = function(keyname){
        $scope.restaurantSortKey = keyname;   //set the sortKey to the param passed
        $scope.restaurantReverse = !$scope.restaurantReverse; //if true make it false and vice versa
    }

    $scope.selectMuseum = function(museum) {
        $scope.selectedNeighborhood = museum.neighborhood.name;
        $scope.loadButtonHidden = false;

    }
});
