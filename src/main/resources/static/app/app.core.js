'use strict';

// create the module and name it app
var app = angular.module('app.core', ['ngRoute', 'angular-loading-bar', 'ngFileUpload']);

// configure our routes
app.config(function($routeProvider, $locationProvider, cfpLoadingBarProvider) {
    cfpLoadingBarProvider.latencyThreshold = 500;
    $locationProvider.hashPrefix('');
    $routeProvider
        .when('/', {
            templateUrl: 'app/home/home.html',
            controller: 'homeController'
        })

});