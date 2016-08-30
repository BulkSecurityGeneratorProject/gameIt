/**
 * Created by Stefan on 31.03.2016.
 */
gameItAngularApp.factory('GamesService',[ '$resource', function ($resource) {
    return $resource('api/games/:id', {id:'@id'}, {
        'update': {method: 'PUT'}
    });
}]);

gameItAngularApp.factory('ApiGames', ['$http', function ($http) {
    var ApiGames = function() {
        this.items = [];
        this.page = 0;
        this.size = 6;
        this.busy = false;
    };

    ApiGames.prototype.nextPage = function () {
        if (this.busy) return;
        this.busy = true;

        console.log("Calling next page of Games");
        var url = "api/games?page=" + this.page + "&size=" + this.size;
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

    return ApiGames;
}]);