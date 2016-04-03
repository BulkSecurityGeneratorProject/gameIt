/**
 * Created by TOMMY on 03-Apr-16.
 */
gameItAngularApp.controller('HardwareController',['$rootScope','$scope','$translate', '$translatePartialLoader',
    function($rootScope,$scope, $translate, $translatePartialLoader){
        $translatePartialLoader.addPart('hardware');
        $translate.refresh();



        $scope.textz="ADJE TOMMY BABA";


}]);