/**
 * Created by Stefan on 02.04.2016.
 */
gameItAngularApp.controller('AdminNewsPostController', ['$scope', '$rootScope','NewsPostService','$http',
    function ($scope, $rootScope,NewsPostService,$http) {
        $scope.newsPost = {};
            $scope.click = function(){
                console.log($scope.newsPost);
           $http({
               method: 'POST',
               url: '/api/news',
               data: $scope.newsPost
           }).then(function success(response){
               $scope.inserted =response.postDescription;
           });
        }
    }]);