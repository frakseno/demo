angular.module('app.ui').controller('MainController',
        function ($http, SpringDataRestAdapter, $uibModal, $location, $anchorScroll, CONFIG, $log, $rootScope) {
    var $mainController = this;

    loadMuseums();

    $rootScope.$on('dataResetComplete', function(event, data) {
        loadMuseums();
    });

    function loadMuseums() {
        this.restaurantPanelHidden = true;

        SpringDataRestAdapter.process($http.get(CONFIG.URL.MUSEUM), 'neighborhood', false).then(function (processedResponse) {
            $mainController.museums = processedResponse._embeddedItems;
        });
    }


    this.loadRestaurantForNeighborhood = function() {
        $log.info("Selected neighborhood: " + $mainController.restaurantNeighborhood.name);

        $http.get(CONFIG.URL.RESTAURNT + $mainController.restaurantNeighborhood.name).
        success(function (data) {
            $mainController.restaurants = data._embedded.buildings;
        });
    }

    this.loadRestaurants = function(museum) {
        $mainController.selectedMuseum = museum;
        $mainController.restaurantNeighborhood = museum.neighborhood;
        $mainController.loadRestaurantForNeighborhood();

        $mainController.restaurantPanelHidden = false;

        $mainController.currentPage = 1;

        $mainController.goToRestaurants();
    }

    this.museumSort = function(keyname){
        $mainController.museumSortKey = keyname;   //set the sortKey to the param passed
        $mainController.museumReverse = !$mainController.museumReverse; //if true make it false and vice versa
    }

    this.restaurantSort = function(keyname){
        $mainController.restaurantSortKey = keyname;   //set the sortKey to the param passed
        $mainController.restaurantReverse = !$mainController.restaurantReverse; //if true make it false and vice versa
    }

    this.selectMuseum = function(museum) {
        $mainController.selectedMuseum = museum;

    }

    this.openDeleteRestaurantPopup = function(restaurant) {
        var modalInstance = $uibModal.open({
            templateUrl: 'partials/deleteRestaurantPopup.html',
            controller: 'DeleteRestaurantPopupController',
            controllerAs: '$deleteRestaurantPopupController',
            resolve: {
                restaurant: function () {
                    return restaurant;
                }
            }
        });

        modalInstance.result.then(function (restaurant) {
            $log.info('go ahead and delete this one: ' + restaurant._links.self.href);

            $http.delete(restaurant._links.self.href).
                success(function (data) {
                    $mainController.loadRestaurantForNeighborhood();
                });
        }, function () {
            $log.info('Modal dismissed at: ' + new Date());
        });
    }

    this.openAddNewRestaurantPopup = function() {
        var modalInstance = $uibModal.open({
            templateUrl: 'partials/restaurantPopup.html',
            controller: 'AddNewRestaurantPopupController',
            controllerAs: '$restaurantPopupController',
            resolve: {
                neighborhoodName: function () {
                    return $mainController.restaurantNeighborhood.name;
                }
            }
        });

        modalInstance.result.then(function (restaurant) {
            restaurant.buildingType = 'RESTAURANT';
            restaurant.neighborhood = $mainController.restaurantNeighborhood._links.self.href;

            $http.post('/data/buildings', restaurant).
                success(function (data) {
                    $mainController.loadRestaurantForNeighborhood();
                });
        }, function () {
            $log.info('Modal dismissed at: ' + new Date());
        });
    }

    this.openUpdateRestaurantPopup = function(restaurant) {
        var modalInstance = $uibModal.open({
            templateUrl: 'partials/restaurantPopup.html',
            controller: 'UpdateRestaurantPopupController',
            controllerAs: '$restaurantPopupController',
            resolve: {
                restaurant: function () {
                    return restaurant;
                }
            }
        });

        modalInstance.result.then(function (restaurant) {
            restaurant.buildingType = 'RESTAURANT';
            restaurant.neighborhood = $mainController.restaurantNeighborhood._links.self.href;

            $http.put(restaurant._links.self.href, restaurant).
                success(function (data) {
                    $mainController.loadRestaurantForNeighborhood();
                });
        }, function () {
            $log.info('Modal dismissed at: ' + new Date());
        });
    }

    this.goToRestaurants = function() {
        $location.hash('restaurantPanel');

        $anchorScroll();
    };

    this.openMapPopup = function() {
        var modalInstance = $uibModal.open({
            templateUrl: 'partials/mapPopup.html',
            controller: 'MapPopupController',
            controllerAs: '$mapPopupController',
            size: 'lg',
            resolve: {
                museum: function () {
                    return $mainController.selectedMuseum;
                },
                restaurants: function () {
                    return $mainController.currentPageRestaurants;
                }
            }
        });

        modalInstance.result.finally(function () {
            $log.info('Modal dismissed at: ' + new Date());
        });
    }
});