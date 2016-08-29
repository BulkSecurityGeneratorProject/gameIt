/**
 * Created by Stefan on 28.8.2016.
 */
gameItAngularApp.controller('AdminGameModalController', ['$scope', '$rootScope', '$http', '$translate', '$translatePartialLoader', 'GamesService', '$uibModalInstance', 'param',
    function ($scope, $rootScope, $http, $translate, $translatePartialLoader, GamesService, $uibModalInstance, param) {

        $translatePartialLoader.addPart('admin');
        $translate.refresh();

        $scope.game = {};
        $scope.data = param.data;
        $http({
            method: 'GET',
            url: 'api/games/' + $scope.data
        }).then(function success(response) {
            console.log(response);
            $scope.game = response.data;
            var minimalPerf = response.data.gameMinimalPerformance.split("@AND@");
            var optimalPerf = response.data.gameOptimalPerformance.split("@AND@");
            $scope.minPerf = {};
            $scope.optPerf = {};

            $scope.minPerf.proc = minimalPerf[0];
            $scope.minPerf.graph = minimalPerf[1];
            $scope.minPerf.ram = minimalPerf[2];
            $scope.minPerf.hdd = minimalPerf[3];

            $scope.optPerf.proc = optimalPerf[0];
            $scope.optPerf.graph = optimalPerf[1];
            $scope.optPerf.ram = optimalPerf[2];
            $scope.optPerf.hdd = optimalPerf[3];
        });

        $scope.ok = function () {
            $scope.game.gameMinimalPerformance = $scope.minPerf.proc + "@AND@" + $scope.minPerf.graph + "@AND@" + $scope.minPerf.ram + "@AND@" + $scope.minPerf.hdd;
            $scope.game.gameOptimalPerformance = $scope.optPerf.proc + "@AND@" + $scope.optPerf.graph + "@AND@" + $scope.optPerf.ram + "@AND@" + $scope.optPerf.hdd;            $http({
                method: 'PUT',
                url: 'api/games/' + $scope.game.gameId,
                data: $scope.game
            }).then(function success(response) {
                $scope.game = response.data;
                $uibModalInstance.close();
            });
        };

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };

    }]);