/**
 * Created by TOMMY on 03-Apr-16.
 */
gameItAngularApp.controller('NewsController', ['$rootScope', '$scope', '$translate', '$stateParams', '$translatePartialLoader', 'NewsPostService', '$state', 'ApiNews',
    function ($rootScope, $scope, $translate, $stateParams, $translatePartialLoader, NewsPostService, $state, ApiNews) {
        $translatePartialLoader.addPart('news');
        $translate.refresh();

        $scope.newsPostList = new ApiNews($state.params.tagNewsPostList != null);
        if ($state.params.tagNewsPostList != null) {
            $scope.newsPostList.items = $state.params.tagNewsPostList;
        }

        console.log($scope.newsPostList);

        $scope.viewNewsPost = function (newsPostId) {
            $state.go('news.single', {id: newsPostId});
        }
    }]);