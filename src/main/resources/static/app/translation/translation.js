/**
 * Created by Stefan on 02.04.2016.
 */
angular.module('angularTranslateApp', ['pascalprecht.translate'])
    .config(['$translateProvider', '$translatePartialLoaderProvider',
        function ($translateProvider, $translatePartialLoaderProvider) {
            $translateProvider.useLoader('$translatePartialLoader', {
                urlTemplate: 'app/translation/{lang}/{part}.json'

            });
            $translateProvider.preferredLanguage('mk');
            // Enable escaping of HTML
            $translateProvider.useSanitizeValueStrategy('escapeParameters');
        }]);