/**
 * Created by Stefan on 01.04.2016.
 */
gameItAngularApp.factory('CredentialsService', ['$http', '$rootScope', function ($http, $rootScope) {
    return {
        authenticate: function (credentials, callback) {
            var headers = credentials ? {authorization: "Basic " + btoa(credentials.username + ":" + credentials.password)} : {};
            $http({
                method: 'GET',
                url: 'api/authenticate',
                headers: headers
            }).then(function success(response) {
                if (response.data.name) {
                    if ($rootScope.loggedInUser === undefined) {
                        $rootScope.loggedInUser = response.data.principal;
                    }
                    $rootScope.authenticated = true;
                } else {
                    $rootScope.loggedInUser = undefined;
                    $rootScope.authenticated = false;
                }
                console.log(response);
                callback && callback();
            }, function error(response) {
                $rootScope.authenticated = false;
                console.log(response);
                callback && callback();
            });

        }
    };

}]);