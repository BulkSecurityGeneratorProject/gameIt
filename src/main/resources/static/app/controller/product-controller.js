/**
 * Created by TOMMY on 03-Apr-16.
 */
gameItAngularApp.controller('ProductController', ['$rootScope', '$scope', '$translate', '$translatePartialLoader', 'HardwareService', '$state',
    function ($rootScope, $scope, $translate, $translatePartialLoader, HardwareService, $state) {
        $translatePartialLoader.addPart('hardware');
        $translate.refresh();
        var hardwareId = $state.params.id;
        $scope.hardwareList = HardwareService.query();
        HardwareService.get({id: hardwareId}, function (response) {
            $scope.product = response;
            console.log(response);
        });

    }]);