/**
 * Created by Stefan on 31.03.2016.
 */
gameItAngularApp.factory('NewsPostService',[ '$resource', function ($resource) {
    return $resource('api/news/:id', {}, {
        'update': {method: 'PUT'}
    });
}]);