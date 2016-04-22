/**
 * Created by TOMMY on 03-Apr-16.
 */
gameItAngularApp.controller('HardwareController',['$rootScope','$scope','$translate', '$translatePartialLoader', 'HardwareService', '$state',
    function($rootScope,$scope, $translate, $translatePartialLoader, HardwareService, $state ){
        $translatePartialLoader.addPart('hardware');
        $translate.refresh();

        $scope.hardwareList = HardwareService.query();
        $scope.viewHardware = function(hardware_id) {
            $state.go('hardware.product', {id:hardware_id});
        }

        $scope.textz="ADJE TOMMY BABA";


}]);