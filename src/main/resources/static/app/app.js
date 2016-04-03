/**
 * Created by Stefan on 24.03.2016.
 */
var gameItAngularApp = angular.module('gameItApp', [
    'ui.router', 'ngResource', 'textAngular', 'pascalprecht.translate','angularTranslateApp',
        'LocalStorageModule',
    'ngCookies',
    'ngAria',
    'ngFileUpload',
    'smart-table',
    'ui.bootstrap',
    'toastr',
    'angular-loading-bar',
    'ngAnimate',
    'infinite-scroll'




]);

gameItAngularApp.run([
    '$rootScope', '$location', '$window', '$http', '$state', 'CredentialsService',
    function ($rootScope, $location, $window, $http, $state, CredentialService) {
        $rootScope.language='mk';

        $rootScope.$on('$stateChangeStart',
            function (event, toState, toParams, fromState, fromParams, options) {
            });
        $rootScope.$on('$stateChangeSuccess',
            function (event, toState, toParams, fromState, fromParams) {
            });

        $rootScope.$on('$stateNotFound',
            function (event, unfoundState, fromState, fromParams) {
                console.log(unfoundState.to); // "lazy.state"
                console.log(unfoundState.toParams); // {a:1, b:2}
                console.log(unfoundState.options); // {inherit:false} + default options

                $state.go('notFound');
            });
    }]);
gameItAngularApp.config(['$httpProvider', '$stateProvider', '$urlRouterProvider',
    function ($httpProvider, $stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/');
        $stateProvider
            .state('main', {
                abstract: true,
                views: {
                    'navbar@': {
                        templateUrl: 'views/navbar.html',
                        controller: 'NavbarController'
                    }
                }
            })
            .state('home', {
                url: '/',
                parent: 'main',
                views: {
                    'page@': {
                        templateUrl: 'views/home.html',
                        controller: 'HomeController'
                    }
                }

            })
            .state('login', {
                url: "/login",
                parent: 'main',
                views: {
                    'page@': {
                        templateUrl: 'views/login.html',
                        controller: 'LoginController'
                    }
                }
            })
            .state('register', {
                url: "/register",
                parent: 'main',
                views: {
                    'page@': {
                        templateUrl: 'views/register.html',
                        controller: 'RegisterController'
                    }
                }
            })
            .state('accountManagement', {
                url: "/account-management",
                parent: 'main',
                views: {
                    'page@': {
                        templateUrl: 'views/account-management.html',
                        controller: 'AccountManagementController'
                    }
                }
            })
            .state('admin', {
                url: "/administrator",
                parent: 'main',
                views:{
                    'page@':{
                        templateUrl: 'views/admin/admin.html',
                        controller: 'AdminController'
                    }
                }
            })
            .state('admin-news-post', {
                url: "/admin-news-post",
                parent: 'admin',
                views: {
                    'admin': {
                        templateUrl: 'views/admin/admin-news-post.html',
                        controller: 'AdminNewsPostController'
                    }
                }
            })
            .state('user-management', {
                url: "/user-management",
                parent: 'admin',
                views: {
                    'admin': {
                        templateUrl: 'views/admin/user-management.html',
                        controller: 'UserManagementController'
                    }
                }
            });

       // $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

//dpsaiojdsaiopjdasiodjoisa
    }]);

