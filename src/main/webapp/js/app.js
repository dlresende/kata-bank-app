'use strict';

angular.module('bankApp', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider
            .when('/clients', {
                controller:'ClientsCtrl',
                templateUrl:'partials/clients.html'
            })
            .when('/accounts/', {
                controller:'AccountsCtrl',
                templateUrl:'partials/accounts.html'
            })
            .when('/account/:username', {
                controller:'AccountsCtrl',
                templateUrl:'partials/accounts.html'
            })
            .otherwise({
                redirectTo:'/clients'
            });
    });