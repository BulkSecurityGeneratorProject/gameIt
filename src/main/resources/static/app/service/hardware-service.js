/**
 * Created by Stefan on 31.03.2016.
 */
gameItAngularApp.factory('HardwareService',[ '$resource', function ($resource) {
    return $resource('api/hardware/:id',  {id:'@id'}, {
        'update': {method: 'PUT'}
    });
}]);