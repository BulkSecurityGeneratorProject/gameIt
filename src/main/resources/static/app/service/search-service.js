/**
 * Created by Stefan on 22.8.2016.
 */
gameItAngularApp.factory('SearchService', ['$resource', function ($resource) {
    return $resource('api/search', {}, {
        'getSearch': {method: 'POST', isArray: true}
    });
}]);