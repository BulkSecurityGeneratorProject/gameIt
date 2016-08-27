/**
 * Created by Stefan on 19.8.2016.
 */
gameItAngularApp.controller('AdminGamesController', ['$scope', '$rootScope', '$http','$translate', '$translatePartialLoader',
    function ($scope, $rootScope, $http, $translate, $translatePartialLoader) {
        // $scope.account = AccountService.query();
        $translatePartialLoader.addPart('games');
        $translatePartialLoader.addPart('admin');
        $translate.refresh();
        
        $scope.game = {};
        $scope.addNewGame = function () {
            $scope.game.gameMinimalPerformance = $scope.minPerf.proc+"@AND@"+$scope.minPerf.graph+"@AND@"+$scope.minPerf.ram+"@AND@"+$scope.minPerf.hdd;
            $scope.game.gameOptimalPerformance = $scope.optPerf.proc+"@AND@"+$scope.optPerf.graph+"@AND@"+$scope.optPerf.ram+"@AND@"+$scope.optPerf.hdd;
            $http({
                method: 'POST',
                url: '/api/games',
                data: $scope.game
            }).then(function success(response){
                $scope.success = 'SUCCESS';
                $scope.error = undefined;
                $scope.game = {};
                $scope.optPerf = {};
                $scope.minPerf = {};
            }, function error(response) {
                $scope.error = 'ERROR';
                $scope.success = undefined;
            });
        }
    }]);