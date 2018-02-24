/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

module.controller('NewCatCtrl', ['$scope', '$http', function ($scope, $http) {
        console.log("Controlador NewProductCtrl muro cargado!!!!");
        controllerBase($scope);
        
        $scope.titulo = "REGISTRAR NUEVA CATEGORIA";
        $scope.nuevoCat = {};
        $scope.errores = {};
        $scope.formSend = false;
        $scope.mensajeObligatorio = "* Este campo es obligatorio";
        $scope.nuevoCat={};
        
        $scope.saveCategoria = function () {
            $scope.formSend = true;
            var error = false;
            if (!$scope.nuevoCat.codigo) {
                $scope.errores.code = $scope.mensajeObligatorio;
                error = true;
            } else {
                $scope.errores.code = '';
            }
            if (!$scope.nuevoCat.descripcion) {
                $scope.errores.decription = $scope.mensajeObligatorio;
                error = true;
            } else {
                $scope.errores.decription = '';
            }
            if (!$scope.nuevoCat.estado) {
                $scope.errores.estado = $scope.mensajeObligatorio;
                error = true;
            } else {
                $scope.errores.estado = '';
            }
            
            if (error) {
                return;
            }
            console.log(JSON.stringify($scope.nuevoCat));
            $http.post("./webresources/categoriaServicios/save/", $scope.nuevoCat, {}).success(function (data, status) {
                console.log(JSON.stringify(data));
                if (data.codigo === 'EXITO') {
                    $scope.procesoExitoso(data.mensaje);
                    $scope.nuevoCat = {};
                    $scope.errores = {};
                    $scope.formSend = false;
                    window.location = "#/listarcategoria/";
                } else {
                    $scope.procesoError(data.mensaje);
                }
            }).error(function (data, status) {
                $scope.procesoError(mensajeErrorServices);
            });
        };

        $scope.cancelar = function () {
            window.location = "#/listarcategoria/";
        };

    }]);

module.controller('EditarCatCtrl', ['$scope', '$http', '$routeParams', function ($scope, $http, $routeParams) {
        console.log("Controlador EditarProductCtrl muro cargado!!!!");
        controllerBase($scope);
        
        $scope.titulo = "EDITAR CATEGORIA";
        $scope.nuevoCat = {};
        $scope.errores = {};
        $scope.formSend = false;

        var idcat;
        $scope.buscarPorId = function (id) {
            $http.get('./webresources/categoriaServicios/cat/' + id, {})
                    .success(function (data, status, headers, config) {
                        if (data.codigo === 'EXITO') {
                            $scope.nuevoCat = data.object;
                        } else {
                            $scope.procesoError(data.mensaje);
                        }
                    }).error(function (data, status, headers, config) {
                $scope.procesoError(mensajeErrorServices);
            });
        };

        if ($routeParams.id) {
            idcat = $routeParams.id;
            $scope.buscarPorId(idcat);
            console.log("Id de categoria a editar : " + idcat);
        } else {
            return;
        }

        $scope.saveCategoria = function () {
            $scope.formSend = true;
            var error = false;
            if (!$scope.nuevoCat.codigo) {
                $scope.errores.code = $scope.mensajeObligatorio;
                error = true;
            } else {
                $scope.errores.code = '';
            }
            if (!$scope.nuevoCat.descripcion) {
                $scope.errores.decription = $scope.mensajeObligatorio;
                error = true;
            } else {
                $scope.errores.decription = '';
            }
            if (!$scope.nuevoCat.estado) {
                $scope.errores.estado = $scope.mensajeObligatorio;
                error = true;
            } else {
                $scope.errores.estado = '';
            }
            
            if (error) {
                return;
            }
            console.log(JSON.stringify($scope.nuevoCat));
            $http.post("./webresources/categoriaServicios/save/", $scope.nuevoCat, {}).success(function (data, status) {
                console.log(JSON.stringify(data));
                if (data.codigo === 'EXITO') {
                    $scope.procesoExitoso(data.mensaje);
                    $scope.nuevoCat = {};
                    $scope.errores = {};
                    $scope.formSend = false;
                    window.location = "#/listarcategoria/";
                } else {
                    $scope.procesoError(data.mensaje);
                }
            }).error(function (data, status) {
                $scope.procesoError(mensajeErrorServices);
            });
        };

        $scope.cancelar = function () {
            window.location = "#/listarcategoria/";
        };

    }]);

module.controller('ListCatCtrl', ['$scope', '$http', function ($scope, $http) {
        controllerBase($scope);
        console.log("Controlador ListCatCtrl muro cargado!!!!");

        $scope.listCategorias = [];
        $scope.catSelected;

        $scope.cargarCatbase = function () {
            $scope.listProductos = [];
            $http.get('./webresources/categoriaServicios/listCategorias/', {})
                    .success(function (data, status, headers, config) {
                        if (data.codigo === 'EXITO') {
                            $scope.listCategorias = data.object;
                        } else {
                            $scope.procesoError(data.mensaje);
                        }
                    }).error(function (data, status, headers, config) {
                $scope.procesoError(mensajeErrorServices);
            });
        };
        $scope.cargarCatbase();

        $scope.editar = function (id) {
            console.log("Id de producto a editar : " + id);
            if (id) {
                window.location = "#/editarcategoria/" + id;
            }
        };

        //pendiente
        $scope.eliminarCategoria = function (cat) {
            $scope.catSelected = cat;
            $('#modelEliminarProducto').modal();
        };

        $scope.confirmarEliminarCat = function () {
            if ($scope.catSelected) {
                $http.post("./webresources/categoriaServicios/delete/", $scope.catSelected, {}).success(function (data, status) {
                    console.log(JSON.stringify(data));
                    if (data.codigo === 'EXITO') {
                        $scope.cargarCatbase();
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
