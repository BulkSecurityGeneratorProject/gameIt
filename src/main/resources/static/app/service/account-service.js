/**
 * Created by Stefan on 31.03.2016.
 */
gameItAngularApp.factory('AccountService', ['$resource', function ($resource) {
    return $resource('api/account/:id', {}, {
        'update': {method: 'PUT'}
    });
}]);