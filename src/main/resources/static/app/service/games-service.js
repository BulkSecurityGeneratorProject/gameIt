/**
 * Created by Stefan on 31.03.2016.
 */
gameItAngularApp.factory('GamesService',['$resource', function ($resource) {
    return $resource('api/games/:id', {}, {
        'update': {method: 'PUT'}
    });
}]);