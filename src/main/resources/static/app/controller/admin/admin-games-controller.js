/**
 * Created by Stefan on 19.8.2016.
 */
gameItAngularApp.controller('AdminGamesController', ['$scope', '$rootScope', '$http','$translate', '$translatePartialLoader',
    function ($scope, $rootScope, $http, $translate, $translatePartialLoader) {
        // $scope.account = AccountService.query();
        $translatePartialLoader.addPart('games');
        $translatePartialLoader.addPart('admin');
        $translate.refresh();
        
        
        $scope.addNewGame = function () {
            $http({
                method: 'POST',
                url: '/api/games',
                data: $scope.game
            }).then(function success(response){
                $scope.success = 'SUCCESS';
                $scope.error = undefined;
            }, function error(response) {
                $scope.error = 'ERROR';
                $scope.success = undefined;
            });
        }
    }]);