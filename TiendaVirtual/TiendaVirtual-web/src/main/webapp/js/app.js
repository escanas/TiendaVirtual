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
                a.when('/home', {templateUrl: 'partials/home.html', controller: 'homeCtrl'});
                a.when('/crearproducto', {templateUrl: 'partials/crearproducto.html', controller: 'NewProductCtrl'});
                a.when('/editarproducto/:id', {templateUrl: 'partials/crearproducto.html', controller: 'EditarProductCtrl'});
                a.when('/listarproductos', {templateUrl: 'partials/listproductos.html', controller: 'ListProductCtrl'});
               
                a.when('/crearcategoria', {templateUrl: 'partials/crearcategoria.html', controller: 'NewCatCtrl'});
                a.when('/editarcategoria/:id', {templateUrl: 'partials/crearcategoria.html', controller: 'EditarCatCtrl'});
                a.when('/listarcategoria', {templateUrl: 'partials/listcategoria.html', controller: 'ListCatCtrl'});
                
                
                
                a.otherwise({redirectTo: "/home"});
            }
        ]);

//app.config(["$httpProvider", function ($httpProvider) {
//    $httpProvider.interceptors.push('httpInterceptorSession'); 
//}]);

