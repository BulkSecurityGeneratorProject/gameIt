/**
 * Created by Stefan on 29.8.2016.
 */
// AdminEditConroller
gameItAngularApp.controller('AdminEditConroller', ['$scope', 'toastr', '$rootScope', '$http', '$translate', '$translatePartialLoader', '$uibModal', 'GamesService','NewsPostService',
    function ($scope, toastr, $rootScope, $http, $translate, $translatePartialLoader, $uibModal, GamesService, NewsPostService) {
        // $scope.account = AccountService.query();
        $translatePartialLoader.addPart('games');
        $translatePartialLoader.addPart('admin');
        $translatePartialLoader.addPart('news');
        $translate.refresh();


        $scope.itemsByPage = 5;
        $scope.gameList = GamesService.query();
        $scope.displayedGames = [].concat($scope.gameList);

        $scope.newsList = NewsPostService.query();
        $scope.displayedNews = [].concat($scope.newsList);

        $scope.viewPost = function (size, newsPost) {
            $scope.selectedNewsPostId = newsPost.postId;

            $scope.open = function (size) {
                var modalInstance = $uibModal.open({
                    animation: true,
                    ariaLabelledBy: 'modal-title',
                    ariaDescribedBy: 'modal-body',
                    templateUrl: 'views/admin/modals/admin-news-modal.html',
                    controller: 'AdminNewsModalController',
                    size: size,
                    resolve: {
                        param: function () {
                            return {'data': $scope.selectedNewsPostId};
                        }
                    }
                });

                modalInstance.result.then(function () {
                    $scope.newsList = NewsPostService.query();
                    $translate('news.editSave').then(function (translatedMessage) {
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