
app.controller("usuarioController", function ( $http, $location, $q,usuario) {

    var vm = this;
    vm.usuario={};

    usuario.getUsuario().then(function(retorno){
        vm.usuario=retorno;
        vm.usuario.fotoUsuario=retorno.fotoUsuario;
        vm.imagemUsuario= vm.usuario.fotoUsuario || './image/semfoto.png';
    });

});