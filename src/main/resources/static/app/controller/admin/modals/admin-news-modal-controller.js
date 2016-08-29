/**
 * Created by Stefan on 28.8.2016.
 */
gameItAngularApp.controller('AdminNewsModalController', ['$scope', '$rootScope', '$http', '$translate', '$translatePartialLoader', 'GamesService', '$uibModalInstance', 'param',
    function ($scope, $rootScope, $http, $translate, $translatePartialLoader, GamesService, $uibModalInstance, param) {
        $translatePartialLoader.addPart('news');
        $translatePartialLoader.addPart('games');
        $translatePartialLoader.addPart('admin');
        $translate.refresh();

        $scope.newsPost = {};
        $scope.data = param.data;
        $http({
            method: 'GET',
            url: 'api/news/' + $scope.data
        }).then(function success(response) {
            console.log(response);
            $scope.newsPost = response.data;
            $scope.newsPost.postAddDate = null;
        });

        $scope.ok = function () {
            $http({
                method: 'PUT',
                url: 'api/news/' + $scope.newsPost.postId,
                data: {
                    newsPost: $scope.newsPost,
                    tagObject: null
                }
            }).then(function success(response) {
                $scope.newsPost = response.data;
                $uibModalInstance.close();
            });
        };

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };

    }]);