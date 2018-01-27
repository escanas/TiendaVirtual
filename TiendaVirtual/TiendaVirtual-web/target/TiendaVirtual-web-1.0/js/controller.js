/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

'use strict';

var module = angular.module('TiendaVirtual.controllers', []);

var mensajeErrorServices = "Señor usuario, en este momento la plataforma esta presentando inconvenientes, por favor intente nuevamente";
var numero = /^[0-9]{0,15}$/;
var mail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/;
var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
var password = "/.*[A-Z].*.*[!-_/].*/";
var decimal = "/(-?[0-9]{0,15}+(\\.[0-9]{0,2}+)?)/";
var alfanumerico = /^[0-9a-zA-Z]+$/;
var alfanumericoSimbol = /^[0-9a-zA-Z\s\.\,\!\¡\¿\?\#\°_-]+$/;
var alfanumericoEspacio = /^[0-9a-zA-Z\s]+$/;
var pass = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,10}$/;

module.controller('homeCtrl', ['$scope', '$http', function ($scope, $http) {
        console.log("Controlador ppal muro cargado!!!!");

        $scope.listProductos = [];

        $scope.cargarProductosbase = function () {
            $http.get('./webresources/productoServicios/listarProductos/', {})
                    .success(function (data, status, headers, config) {
                        console.log(JSON.stringify(data));

                        if (data.codigo === 'EXITO') {
                            $scope.listProductos = data.object;
                        } else {
                            alert(data.mensaje);
                        }
                    }).error(function (data, status, headers, config) {
                alert(mensajeErrorServices);
            });
        };
        $scope.cargarProductosbase();



    }]);


module.controller('NewProductCtrl', ['$scope', '$http', function ($scope, $http) {
        console.log("Controlador NewProductCtrl muro cargado!!!!");

        $scope.nuevoProducto = {};
        $scope.errores = {};
        $scope.formSend = false;
        $scope.mensajeObligatorio = "* Este campo es obligatorio";

        $scope.saveProduct = function () {
            $scope.formSend = true;
            var error = false;
            if (!$scope.nuevoProducto.code) {
                $scope.errores.code = $scope.mensajeObligatorio;
                error = true;
            } else {
                $scope.errores.code = '';
            }
            if (!$scope.nuevoProducto.decription) {
                $scope.errores.decription = $scope.mensajeObligatorio;
                error = true;
            } else {
                $scope.errores.decription = '';
            }
            if (!$scope.nuevoProducto.precio) {
                $scope.errores.precio = $scope.mensajeObligatorio;
                error = true;
            } else {
                $scope.errores.precio = '';
            }
            if (!$scope.nuevoProducto.cantidad) {
                $scope.errores.cantidad = $scope.mensajeObligatorio;
                error = true;
            } else {
                $scope.errores.cantidad = '';
            }
            if (!$scope.nuevoProducto.estado) {
                $scope.errores.estado = $scope.mensajeObligatorio;
                error = true;
            } else {
                $scope.errores.estado = '';
            }

            if (error) {
                return;
            }

            console.log(JSON.stringify($scope.nuevoProducto));

            $http.post("./webresources/productoServicios/crear/", $scope.nuevoProducto, {}).success(function (data, status) {
                console.log(JSON.stringify(data));
                if (data.codigo === 'EXITO') {
                    alert("EXITO " + data.mensaje);
                    $scope.nuevoProducto = {};
                    $scope.errores = {};
                    $scope.formSend = false;
                } else {
                    alert("ERROR " + data.mensaje);
                }
            }).error(function (data, status) {
                alert(mensajeErrorServices);
            });
        };


    }]);

module.controller('ListProductCtrl', ['$scope', '$http', function ($scope, $http) {
        console.log("Controlador NewProductCtrl muro cargado!!!!");

        $scope.listProductos = [];

        $scope.cargarProductosbase = function () {
            $http.get('./webresources/productoServicios/listarProductos/', {})
                    .success(function (data, status, headers, config) {
                        console.log(JSON.stringify(data));

                        if (data.codigo === 'EXITO') {
                            $scope.listProductos = data.object;
                        } else {
                            alert(data.mensaje);
                        }
                    }).error(function (data, status, headers, config) {
                alert(mensajeErrorServices);
            });
        };
        $scope.cargarProductosbase();

    }]);