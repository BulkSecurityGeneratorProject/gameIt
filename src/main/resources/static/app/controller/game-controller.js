/**
 * Created by TOMMY on 03-Apr-16.
 */
gameItAngularApp.controller('GameController', ['$rootScope', '$uibModal', 'toastr', '$scope', '$http', '$translate',
    '$translatePartialLoader', 'GamesService', '$state', 'ngCart',
    function ($rootScope, $uibModal, toastr, $scope, $http, $translate,
              $translatePartialLoader, GamesService, $state, ngCart) {
        $translatePartialLoader.addPart('games');
        $translate.refresh();

        ngCart.setTaxRate(10);
        ngCart.setShipping(2.99);

        var gameId = $state.params.id;
        $scope.comment = {};

        $scope.addComment = function () {
            $scope.comment.gameId = gameId;
            $http({
                method: 'POST',
                url: '/api/games/comment',
                data: $scope.comment
            }).then(function success(response) {
                $scope.game = response.data;
                $scope.comment = {};
                $translate('games.game.commentSuccess').then(function (translatedMessage) {
                    toastr.success(translatedMessage, {
                        closeButton: true,
                        allowHtml: true
                    });
                });
            });
        };
        $scope.deleteComment = function(gameId,commentGame) {
            console.log(commentGame);
            console.log(gameId);
            $http({
                method: 'POST',
                url: 'api/games/'+gameId+"/comment",
                data: commentGame
            }).then(function success(response) {
                $scope.game=response.data;
                $translate('games.game.commentSuccess').then(function (translatedMessage) {
                    toastr.success(translatedMessage, {
                        closeButton: true,
                        allowHtml: true
                    });
                });
            })
        };
        //$scope.gamesList = GamesService.query();
        $scope.userRating = 0;
        function getGame() {
            GamesService.get({id: gameId}, function (response) {
                $scope.game = response;
                $scope.minPerf = $scope.game.gameMinimalPerformance.split("@AND@");
                $scope.optPerf = $scope.game.gameOptimalPerformance.split("@AND@");
                if ($rootScope.loggedInUser != null) {
                    for (var i = 0; i < $scope.game.ratings.length; i++) {
                        if ($scope.game.ratings[i].userId.username = $rootScope.loggedInUser.username) {
                            $scope.userRating = $scope.game.ratings[i].rating;
                            $scope.rate = $scope.userRating;
                            break;
                        }
                    }
                }
            });
        }
        getGame();
        $scope.rate = 0;
        $scope.max = 5;
        $scope.isReadonly = $rootScope.loggedInUser == null;

        $scope.hoveringOver = function (value) {
            $scope.overStar = value;
            $scope.percent = 100 * (value / $scope.max);
        };

        $scope.ratingStates = [
            {stateOn: 'glyphicon-star', stateOff: 'glyphicon-star-empty'},
        ];

        $scope.updateRating = function (gameId) {
            $http({
                method: 'POST',
                url: 'api/games/' + gameId + '/rate',
                data: $scope.userRating
            }).then(function success(response) {
                console.log(response);
                $scope.game = response.data;
            }, function error(response) {

            });
        };

        $scope.buyGame = function (size, game) {
            $scope.selectedGame = game;

            $scope.open = function (size) {
                var modalInstance = $uibModal.open({
                    animation: true,
                    ariaLabelledBy: 'modal-title',
                    ariaDescribedBy: 'modal-body',
                    templateUrl: 'views/purchase-game-modal.html',
                    controller: 'PaymentController',
                    size: size,
                    resolve: {
                        param: function () {
                            return {'data': $scope.selectedGame};
                        }
                    }
                });

                modalInstance.result.then(function () {
                    $scope.gameList = GamesService.query();


                }, function () {
                    console.log('modalClosed');
                });
            };
            $scope.open(size);
        }



    }]);