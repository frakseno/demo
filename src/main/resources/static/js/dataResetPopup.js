angular.module('app.ui').controller('DataResetPopupController', function ($uibModalInstance) {
    var $dataResetPopupController = this;

    this.showProgress = false;

    this.resetData = function() {
        this.showProgress = true;

        $http.post(CONFIG.URL.INIT_DATA).success(function () {
            $uibModalInstance.close(true);
        });
    };

    this.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});