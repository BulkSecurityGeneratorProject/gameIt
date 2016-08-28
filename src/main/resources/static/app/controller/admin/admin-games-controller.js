/**
 * Created by Stefan on 19.8.2016.
 */
gameItAngularApp.controller('AdminGamesController', ['$scope', 'toastr', '$rootScope', '$http', '$translate', '$translatePartialLoader', '$uibModal', 'GamesService',
    function ($scope, toastr, $rootScope, $http, $translate, $translatePartialLoader, $uibModal, GamesService) {
        // $scope.account = AccountService.query();
        $translatePartialLoader.addPart('games');
        $translatePartialLoader.addPart('admin');
        $translate.refresh();

        $scope.game = {};
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
                $scope.optPerf = {};
                $scope.minPerf = {};
            }, function error(response) {
                $scope.error = 'ERROR';
                $scope.success = undefined;
            });
        };

        $scope.itemsByPage = 5;
        $scope.gameList = GamesService.query();
        $scope.displayedGames = [].concat($scope.gameList);

        $scope.viewGame = function (size, game) {
            $scope.selectedGameId = game.gameId;

            $scope.open = function (size) {
                var modalInstance = $uibModal.open({
                    animation: true,
                    ariaLabelledBy: 'modal-title',
                    ariaDescribedBy: 'modal-body',
                    templateUrl: 'views/admin/modals/admin-game-modal.html',
                    controller: 'AdminGameModalController',
                    size: size,
                    resolve: {
                        param: function () {
                            return {'data': $scope.selectedGameId};
                        }
                    }
                });

                modalInstance.result.then(function () {
                    $scope.gameList = GamesService.query();
                    $translate('games.game.editSave').then(function (translatedMessage) {
                        toastr.success(translatedMessage, {
                            closeButton: true,
                            allowHtml: true
                        });
                    });
                }, function () {
                    console.log('modalClosed');
                });
            };
            $scope.open(size);
        }
    }]);