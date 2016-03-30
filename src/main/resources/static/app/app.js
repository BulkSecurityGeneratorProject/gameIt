/**
 * Created by Stefan on 24.03.2016.
 */
var gameItAngularApp = angular.module('gameItApp', [
    'ngCookies', 'ngAria', 'ngFileUpload',
    'ui.router',  'infinite-scroll', 'angular-loading-bar']);
gameItAngularApp.config('$httpProvider', '$stateProvider', '$urlRouterProvider',
    function ($httpProvider, $stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("/");
    $stateProvider
        .state('home', {
            url: "/",
            templateUrl: 'views/home.html',
            controller: 'HomeController'
        })
        .state('login', {
            url: "/login",
            templateUrl: 'views/login.html',
            controller: 'LoginController'
        });

    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

});
