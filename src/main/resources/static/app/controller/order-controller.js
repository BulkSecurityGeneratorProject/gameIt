/**
 * Created by Stefan on 03.9.2016.
 */
/**
 * Created by TOMMY on 03-Apr-16.
 */
gameItAngularApp.controller('OrderController', ['$rootScope', '$uibModal','toastr', '$scope', '$http', '$translate', '$translatePartialLoader', 'GamesService', '$state',
    function ($rootScope,$uibModal, toastr, $scope, $http, $translate, $translatePartialLoader, GamesService, $state) {
        $translatePartialLoader.addPart('games');
        $translate.refresh();

        $scope.orders = {};
        $http({
            method: 'GET',
            url: 'api/orders'
        }).then(function success(response) {
            $scope.orders = response.data;
            console.log($scope.orders);
        });

    }]);