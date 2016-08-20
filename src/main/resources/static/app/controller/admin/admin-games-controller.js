/**
 * Created by Stefan on 19.8.2016.
 */
gameItAngularApp.controller('AdminGamesController', ['$scope', '$rootScope', '$http','$translate', '$translatePartialLoader',
    function ($scope, $rootScope, $http, $translate, $translatePartialLoader) {
        // $scope.account = AccountService.query();
        $translatePartialLoader.addPart('admin');
        $translate.refresh();
    }]);