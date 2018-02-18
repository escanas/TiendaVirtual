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

function controllerBase($scope) {

    $scope.procesoExitoso = function (mensaje) {
        $.notify({
            allow_dismiss: false,
            icon: 'glyphicon glyphicon glyphicon-ok',
            title: '<strong>Tienda Virtual</strong> ',
            message: mensaje
        }, {
            type: 'success'
        });
    };

    $scope.procesoError = function (mensaje) {
        $.notify({
            allow_dismiss: false,
            icon: 'glyphicon glyphicon glyphicon-remove',
            title: '<strong>Tienda Virtual</strong> ',
            message: mensaje
        }, {
            type: 'danger'
        });
    };
}
;

module.controller('homeCtrl', ['$scope', '$http', function ($scope, $http) {
        console.log("Controlador ppal muro cargado!!!!");
        controllerBase($scope);
        $scope.listProductos = [];

        $scope.cargarProductosbase = function () {
            $http.get('./webresources/productoServicios/listarProductos/', {})
                    .success(function (data, status, headers, config) {
                        if (data.codigo === 'EXITO') {
                            $scope.listProductos = data.object;
                        } else {
                            $scope.procesoError(data.mensaje);
                        }
                    }).error(function (data, status, headers, config) {
                alert(mensajeErrorServices);
            });
        };
        $scope.cargarProductosbase();



    }]);

module.controller('NewProductCtrl', ['$scope', '$http', function ($scope, $http) {
        console.log("Controlador NewProductCtrl muro cargado!!!!");
        controllerBase($scope);
        $scope.titulo = "REGISTRAR NUEVO PRODUCTO";
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
            $scope.nuevoProducto.imagen = $('#archivo').val();

            $http.post("./webresources/productoServicios/crear/", $scope.nuevoProducto, {}).success(function (data, status) {
                console.log(JSON.stringify(data));
                if (data.codigo === 'EXITO') {
                    $scope.procesoExitoso(data.mensaje);
                    $scope.nuevoProducto = {};
                    $scope.errores = {};
                    $scope.formSend = false;
                    window.location = "#/listarproductos/";
                } else {
                    $scope.procesoError(data.mensaje);
                }
            }).error(function (data, status) {
                $scope.procesoError(mensajeErrorServices);
            });
        };

        $scope.cancelar = function () {
            window.location = "#/listarproductos/";
        };

    }]);

module.controller('EditarProductCtrl', ['$scope', '$http', '$routeParams', function ($scope, $http, $routeParams) {
        console.log("Controlador EditarProductCtrl muro cargado!!!!");
        controllerBase($scope);
        $scope.titulo = "EDITAR PRODUCTO";
        $scope.nuevoProducto = {};
        $scope.errores = {};
        $scope.formSend = false;

        var idProducto;
        $scope.buscarPorId = function (id) {
            $http.get('./webresources/productoServicios/buscar/' + id, {})
                    .success(function (data, status, headers, config) {
                        if (data.codigo === 'EXITO') {
                            $scope.nuevoProducto = data.object;
                        } else {
                            $scope.procesoError(data.mensaje);
                        }
                    }).error(function (data, status, headers, config) {
                $scope.procesoError(mensajeErrorServices);
            });
        };

        if ($routeParams.id) {
            idProducto = $routeParams.id;
            $scope.buscarPorId(idProducto);
            console.log("Id de producto a editar : " + idProducto);
        } else {
            return;
        }

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
            $scope.nuevoProducto.imagen = $('#archivo').val();

            $http.post("./webresources/productoServicios/crear/", $scope.nuevoProducto, {}).success(function (data, status) {
                console.log(JSON.stringify(data));
                if (data.codigo === 'EXITO') {
                    $scope.procesoExitoso(data.mensaje);
                    $scope.errores = {};
                    $scope.formSend = false;
                    window.location = "#/listarproductos/";
                } else {
                    $scope.procesoError()(data.mensaje);
                }
            }).error(function (data, status) {
                $scope.procesoError(mensajeErrorServices);
            });
        };

        $scope.cancelar = function () {
            window.location = "#/listarproductos/";
        };

    }]);

module.controller('ListProductCtrl', ['$scope', '$http', function ($scope, $http) {
        console.log("Controlador NewProductCtrl muro cargado!!!!");

        $scope.listProductos = [];
        $scope.productoSelected;

        $scope.cargarProductosbase = function () {
            $scope.listProductos = [];
            $http.get('./webresources/productoServicios/listarProductos/', {})
                    .success(function (data, status, headers, config) {
                        if (data.codigo === 'EXITO') {
                            $scope.listProductos = data.object;
                        } else {
                            $scope.procesoError(data.mensaje);
                        }
                    }).error(function (data, status, headers, config) {
                $scope.procesoError(mensajeErrorServices);
            });
        };
        $scope.cargarProductosbase();

        $scope.editar = function (id) {
            console.log("Id de producto a editar : " + id);
            if (id) {
                window.location = "#/editarproducto/" + id;
            }
        };

        $scope.eliminarProducto = function (producto) {
            $scope.productoSelected = producto;

            $('#modelEliminarProducto').modal();
        };

        $scope.confirmarEliminarProducto = function () {
            if ($scope.productoSelected) {
                $http.post("./webresources/productoServicios/eliminar/", $scope.productoSelected, {}).success(function (data, status) {
                    console.log(JSON.stringify(data));
                    if (data.codigo === 'EXITO') {
                        $scope.cargarProductosbase();
                        $scope.procesoExitoso(data.mensaje);
                    } else {
                        $scope.procesoError(data.mensaje);
                    }
                }).error(function (data, status) {
                    $scope.procesoError(mensajeErrorServices);
                });
                $('#modelEliminarProducto').modal('hide');
            } else {
                alert("Debe seleccionar un producto para poder eliminar!!");
            }
        };

    }]);