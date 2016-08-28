/**
 * Created by Stefan on 28.8.2016.
 */
gameItAngularApp.controller('AdminGameModalController', ['$scope', '$rootScope', '$http','$translate', '$translatePartialLoader', 'GamesService','$uibModalInstance','param',
    function ($scope, $rootScope, $http, $translate, $translatePartialLoader, GamesService, $uibModalInstance, param) {

        $translatePartialLoader.addPart('admin');
        $translate.refresh();

        $scope.data = param.data;
        console.log("gameid:" + $scope.data);
        $http({
            method: 'GET',
            url: 'api/games/' + $scope.data
        }).then(function success(response) {
            console.log(response);
            $scope.game = response.data;
            $scope.minPerf = $scope.game.gameMinimalPerformance.split("@AND@");
            $scope.optPerf = $scope.game.gameOptimalPerformance.split("@AND@");
        });

        $scope.ok = function () {
            $http({
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