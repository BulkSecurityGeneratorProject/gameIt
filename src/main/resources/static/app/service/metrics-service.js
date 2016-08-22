/**
 * Created by Stefan on 03.04.2016.
 */
gameItAngularApp.factory('MetricsService', ['$rootScope', '$http', function ($rootScope, $http) {
    var service = {
        getMetrics: getMetrics,
        threadDump: threadDump
    };
    return service;

    function getMetrics() {
        return $http.get('/metrics').then(function (response) {
            console.log(response);
            return response.data;
        });
    }

    function threadDump() {
        return $http.get('dump').then(function (response) {
            return response.data;
        });
    }
}]);
