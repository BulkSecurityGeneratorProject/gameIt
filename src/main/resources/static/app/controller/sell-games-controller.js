/**
 * Created by Stefan on 19.8.2016.
 */
gameItAngularApp.controller('SellGamesController', ['$scope', 'toastr','$state', '$rootScope', '$http', '$translate', '$translatePartialLoader', '$uibModal', 'GamesService',
    function ($scope, toastr,$state, $rootScope, $http, $translate, $translatePartialLoader, $uibModal, GamesService) {
        // $scope.account = AccountService.query();
        $translatePartialLoader.addPart('games');
        $translatePartialLoader.addPart('admin');
        $translate.refresh();

        $scope.game = {};
        $scope.minPerf = {
            proc: "",
            graph: "",
            ram: "",
            hdd: ""
        };
        $scope.optPerf = {
            proc: "",
            graph: "",
            ram: "",
            hdd: ""
        };
        $scope.addNewGame = function () {
            $scope.game.gameMinimalPerformance = $scope.minPerf.proc + "@AND@" + $scope.minPerf.graph + "@AND@" + $scope.minPerf.ram + "@AND@" + $scope.minPerf.hdd;
            $scope.game.gameOptimalPerformance = $scope.optPerf.proc + "@AND@" + $scope.optPerf.graph + "@AND@" + $scope.optPerf.ram + "@AND@" + $scope.optPerf.hdd;
            $http({
                method: 'POST',
                url: '/api/games',
                data: $scope.game
            }).then(function success(response) {
                $scope.success = 'SUCCESS';
                $scope.error = undefined;
                $scope.game = {};
                $scope.game.gameReleaseYear = null;
                $scope.optPerf = {};
                $scope.minPerf = {};
            }, function error(response) {
                $scope.error = 'ERROR';
                $scope.success = undefined;
            });
        };


    }]);