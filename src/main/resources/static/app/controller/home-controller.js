/**
 * Created by Stefan on 30.03.2016.
 */
gameItAngularApp.controller('HomeController', ['$scope', '$rootScope', '$http','$translate', '$translatePartialLoader',
    function ($scope, $rootScope, $http, $translate, $translatePartialLoader) {
        $scope.hello = "hellossssssz";
        // $scope.account = AccountService.query();
        $translatePartialLoader.addPart('home');
        $translate.refresh();

     
    }]);