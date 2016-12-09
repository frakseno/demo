angular.module('app.ui').controller('NavigationBarController', function ($uibModal, $log, $rootScope) {
    this.showMenu = true;

    this.openResetDataPopup = function () {
        $log.info('opening pop up');
        var modalInstance = $uibModal.open({
            templateUrl: 'partials/dataResetPopup.html',
            controller: 'DataResetPopupController',
            controllerAs: '$dataResetPopupController'
        });

        modalInstance.result.then(function () {
            $rootScope.$broadcast('dataResetComplete');
        }, function () {
            $log.info('Modal dismissed at: ' + new Date());
        });
    };
});