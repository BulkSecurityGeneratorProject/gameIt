/**
 * Created by TOMMY on 03-Apr-16.
 */
gameItAngularApp.controller('NewsController',[ '$rootScope', '$scope', '$translate', '$translatePartialLoader','NewsPostService','$state', 'ApiNews',
    function($rootScope, $scope, $translate, $translatePartialLoader, NewsPostService, $state, ApiNews){
        $translatePartialLoader.addPart('news');
        $translate.refresh();
        $scope.newsPostList = new ApiNews();
        console.log($scope.newsPostList);

        $scope.viewNewsPost = function (newsPostId) {
            $state.go('news.single', {id: newsPostId});
        }
    }]);