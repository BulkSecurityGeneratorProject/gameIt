/**
 * Created by Stefan on 02.04.2016.
 */
gameItAngularApp.controller('AdminController', ['$scope', '$rootScope', '$http','$translate', '$translatePartialLoader',
    function ($scope, $rootScope, $http, $translate, $translatePartialLoader) {
        $scope.hello = "hellossssssz";
        // $scope.account = AccountService.query();
        $translatePartialLoader.addPart('admin');
        $translate.refresh();
    }]);