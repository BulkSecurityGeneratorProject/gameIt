/**
 * Created by Stefan on 19.8.2016.
 */
gameItAngularApp.directive("passwordVerify", function () {
    return {
        require: "ngModel",
        scope: {
            otherModelValue: "=passwordVerify"
        },
        link: function (scope, element, attributes, ngModel) {

            ngModel.$validators.compareTo = function (modelValue) {
                return modelValue == scope.otherModelValue;
            };

            scope.$watch("otherModelValue", function () {
                ngModel.$validate();
            });
        }
    };
});