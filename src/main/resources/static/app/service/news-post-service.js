/**
 * Created by Stefan on 31.03.2016.
 */
gameItAngularApp.factory('NewsPostService',[ '$resource', function ($resource) {
    return $resource('api/news/:id', {}, {
        'update': {method: 'PUT'}
    });
}]);
gameItAngularApp.factory('ApiNews', ['$http', '$rootScope', function ($http, $rootScope) {
    var ApiNews = function() {
        this.items = [];
        this.page = 0;
        this.size = 5;
        this.busy = $rootScope.searchTagResultList !== undefined;
    };

    ApiNews.prototype.nextPage = function () {
        // if ($rootScope.searchTagResultList) {
        //     this.busy = true
        //     return;
        // }
        if (this.busy) return;
        this.busy = true;

        console.log("Calling next page of ApiNews");
        var url = "api/news?page=" + this.page + "&size=" + this.size;
        $http({
            method: 'GET',
            url: url
        }).then(function success(data) {
            var items = data.data;
            if (items.length == 0) {
                this.busy = true;
            } else {
                for (var i = 0; i < items.length; i++) {
                    this.items.push(items[i]);
                }
                this.page += 1;
                this.busy = false;
            }
        }.bind(this));
    };

    return ApiNews;
}]);