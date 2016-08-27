/**
 * Created by Stefan on 22.8.2016.
 */
gameItAngularApp.controller('SingleNewsPostController', ['$rootScope', '$scope', '$http', '$translate', '$translatePartialLoader', 'NewsPostService', '$state',
    function ($rootScope, $scope, $http, $translate, $translatePartialLoader, NewsPostService, $state) {
        $translatePartialLoader.addPart('news');
        $translate.refresh();

        var postId = $state.params.id;
        NewsPostService.get({id: postId}, function (response) {
            $scope.newsPost = response;
        });

        $scope.searchNewsByTag = function (tagName) {
            $http({
                method: 'GET',
                url: 'api/news/tag/' + tagName
            }).then(function success(response) {
                var newsPostList = response.data;
                $state.go('news', {tagNewsPostList: newsPostList});
            }, function error(response) {
                console.log(error);
            })
        };
    }]);