/**
 * Created by Stefan on 02.04.2016.
 */
gameItAngularApp.controller('AdminNewsPostController', ['$scope', '$rootScope', 'NewsPostService', '$http', '$translate', '$translatePartialLoader', '$state',
    function ($scope, $rootScope, NewsPostService, $http, $translate, $translatePartialLoader, $state) {
        $translatePartialLoader.addPart('news');
        $translate.refresh();
        $scope.newsPost = {};
        $scope.tagObject = {};

        $scope.addNewPost = function () {
            console.log($scope.newsPost);
            $http({
                method: 'POST',
                url: '/api/news',
                data: {
                    newsPost: $scope.newsPost,
                    tagObject: $scope.tagObject
                }
            }).then(function success(response) {
                $scope.success = 'SUCCESS';
                $scope.error = undefined;
                $scope.newsPost = {};
                $scope.tagObject = {};
            }, function error(response) {
                $scope.error = 'ERROR';
                $scope.success = undefined;
            });
        }
    }]);