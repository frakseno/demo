angular.module('app.ui').controller('AddNewRestaurantPopupController', function ($uibModalInstance, neighborhoodName) {
    this.restaurant = new Object();
    this.restaurant.city = 'Baltimore';
    this.restaurant.state = 'MD';
    this.headerText = 'Add New Restaurant in ' + neighborhoodName;

    this.save = function () {
        $uibModalInstance.close(this.restaurant);
    };

    this.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});