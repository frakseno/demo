angular.module('app.ui').controller('DeleteRestaurantPopupController', function ($uibModalInstance, restaurant) {
    this.restaurant = restaurant;

    this.ok = function () {
        $uibModalInstance.close(this.restaurant);
    };

    this.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});