/**
 * Created by Stefan on 24.03.2016.
 */
var gameItAngularApp = angular.module('gameItApp', [
    'ui.router',
    'ngResource',
    'textAngular',
    'pascalprecht.translate',
    'angularTranslateApp',
    'LocalStorageModule',
    'ngCookies',
    'ngAria',
    'ngFileUpload',
    'smart-table',
    'ui.bootstrap',
    'toastr',
    'angular-loading-bar',
    'ngAnimate',
    'infinite-scroll',
    'ngPasswordStrength',
    'vcRecaptcha',
    'datePicker'
]);

gameItAngularApp.run([
    '$rootScope', '$location', '$window', '$http', '$state', 'CredentialsService','$stateParams',
    function ($rootScope, $location, $window, $http, $state, CredentialService, $stateParams) {
        $rootScope.language = 'mk';
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;
        $rootScope.$on('$stateChangeStart',
            function (event, toState, toParams, fromState, fromParams, options) {
                if (fromState.name == 'news') {
                    $rootScope.searchTagResultList = null;
                }
            });
        $rootScope.$on('$stateChangeSuccess',
            function (event, toState, toParams, fromState, fromParams) {
            });

        $rootScope.$on('$stateNotFound',
            function (event, unfoundState, fromState, fromParams) {
                console.log(unfoundState.to); // "lazy.state"
                console.log(unfoundState.toParams); // {a:1, b:2}
                console.log(unfoundState.options); // {inherit:false} + default options

                // $state.go('notFound');
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
                },
                params: {
                    justRegistered: null
                }
            })
            .state('news', {
                url: "/news",
                parent: 'main',
                views: {
                    'page@': {
                        templateUrl: 'views/news.html',
                        controller: 'NewsController'
                    }
                },
                params: {
                    tagNewsPostList: null
                }

            })
            .state('search',{
                url: '/search',
                parent: 'main',
                params: {searchInput: null},
                views: {
                    'page@': {
                        templateUrl: 'views/search.html',
                        controller: 'SearchController'
                    }
                },
            })
            .state('news.single', {
                url: "/:id",
                parent: 'news',
                views: {
                    'page@': {
                        templateUrl: 'views/singleNewsPost.html',
                        controller: 'SingleNewsPostController'
                    }
                }
            })
            .state('settings', {
                url: "/settings",
                parent: 'main',
                views: {
                    'page@': {
                        templateUrl: 'views/settings.html',
                        controller: 'SettingsController'
                    }
                }
            })
            .state('password', {
                url: "/password",
                parent: 'main',
                views: {
                    'page@': {
                        templateUrl: 'views/password.html',
                        controller: 'PasswordController'
                    }
                }
            })
            .state('games', {
                url: "/games",
                parent: 'main',
                views: {
                    'page@': {
                        templateUrl: 'views/games.html',
                        controller: 'GamesController'
                    }
                }
            })
            .state('games.game', {
                url: "/:id",
                parent: 'games',
                views: {
                    'page@': {
                        templateUrl: 'views/game.html',
                        controller: 'GameController'
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
                abstract: true,
                views: {
                    'page@': {
                        templateUrl: 'views/admin/admin.html',
                        controller: 'AdminController'
                    }
                }
            })
            .state('metrics', {
                url: "/metrics",
                parent: 'admin',
                views: {
                    'admin': {
                        templateUrl: 'views/admin/metrics.html',
                        controller: 'AdminMetricsController'
                    }
                }
            })
            .state('admin.admin-news-post', {
                url: "/admin-news-post",
                parent: 'admin',
                views: {
                    'admin': {
                        templateUrl: 'views/admin/admin-news-post.html',
                        controller: 'AdminNewsPostController'
                    }
                }
            })
            .state('admin.admin-graphics-table', {
                url: "/admin-graphics-table",
                parent: 'admin',
                views: {
                    'admin': {
                        templateUrl: 'views/admin/admin-graphics-table.html',
                        controller: 'AdminGraphicsTableController'
                    }
                }
            })
            .state('admin.admin-command-board', {
                url: "/admin-command-board",
                parent: 'admin',
                views: {
                    'admin': {
                        templateUrl: 'views/admin/admin-command-board.html',
                        controller: 'AdminCommandBoardController'
                    }
                }
            })
            .state('admin.admin-games', {
                url: "/admin-games",
                parent: 'admin',
                views: {
                    'admin': {
                        templateUrl: 'views/admin/admin-games.html',
                        controller: 'AdminGamesController'
                    }
                }
            })
            .state('admin.user-management', {
                url: "/user-management",
                parent: 'admin',
                views: {
                    'admin': {
                        templateUrl: 'views/admin/user-management.html',
                        controller: 'UserManagementController'
                    }
                }
            });

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    }]);

