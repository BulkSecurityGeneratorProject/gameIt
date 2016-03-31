/**
 * Created by Stefan on 31.03.2016.
 */
gameItAngularApp.factory('UserService', ['$resource', function ($resource) {
    return $resource('api/users/:id', {}, {
        'update': {method: 'PUT'}
    });
}]);