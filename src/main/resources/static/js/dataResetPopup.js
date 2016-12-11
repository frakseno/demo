angular.module('app.ui').controller('DataResetPopupController', function ($uibModalInstance, $http, CONFIG) {
    var $dataResetPopupController = this;

    this.showResetProgress = false;

    this.resetData = function() {
        $dataResetPopupController.showResetProgress = true;

        $http.post(CONFIG.URL.INIT_DATA).success(function () {
            $uibModalInstance.close(true);
        });
    };

    this.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});