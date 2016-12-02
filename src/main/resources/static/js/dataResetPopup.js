angular.module('app.ui').controller('DataResetPopupController', function ($uibModalInstance) {
    this.ok = function () {
        $uibModalInstance.close(true);
    };

    this.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});