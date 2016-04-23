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
                    if(response.data.authorities[0].authority == "ROLE_ADMIN"){
                        $rootScope.administrator = true;
                    }
                    $rootScope.authenticated = true;
                } else {
                    $rootScope.loggedInUser = undefined;
                    $rootScope.authenticated = false;
                    $rootScope.administrator = false;
                }
                console.log(response);
                callback && callback();
            }, function error(response) {
                $rootScope.authenticated = false;
                $rootScope.administrator = false;
                console.log(response);
                callback && callback();
            });

        }
    };

}]);