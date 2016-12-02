angular.module('app.ui').controller('UpdateRestaurantPopupController', function ($uibModalInstance, restaurant) {
    this.restaurant = angular.copy(restaurant);

    this.save = function () {
        $uibModalInstance.close(this.restaurant);
    };

    this.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});