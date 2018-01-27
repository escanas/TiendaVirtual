/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

'use strict';

// Declare app level module which depends on filters, and services
var app = angular.module("TiendaVirtual", [
    "ngRoute",
    "TiendaVirtual.controllers"]).
        config(["$routeProvider", function (a) {
                a.when('/home', {templateUrl: 'partials/principal.html', controller: 'homeCtrl'});
                a.when('/crearproducto', {templateUrl: 'partials/crearproducto.html', controller: 'NewProductCtrl'});
                a.otherwise({redirectTo: "/home"});
            }
        ]);

//app.config(["$httpProvider", function ($httpProvider) {
//    $httpProvider.interceptors.push('httpInterceptorSession'); 
//}]);

