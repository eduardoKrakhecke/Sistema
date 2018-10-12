function mensagemConfirmacaoExclusao(registro,callback) {
    bootbox.confirm({
        message: "Ao continuar registro "+registro+ " será excluído permanentemente",
        buttons: {
            confirm: {
                label: 'Excluir',
                className: 'btn-danger btn-sm'
            },
           cancel: {
                label: 'Cancelar',
                className: 'btn-blue-grey btn-sm'
            }
        },
        callback: function (result) {
            callback(result);
        }
    });
}

mensagemSucesso = function (mensagem) {
    notif({ type: "success", position: "center", autohide:true, msg: mensagem,timeout: 3000, animation: 'slide'
    });
};

carregando = function () {
    var dialog = bootbox.dialog({
        message: '<p class="text-center"><img src="./image/load.gif" height="25" width="25"/> Gerando documento em uma nova aba do navegador...</p>', closeButton: false
    });
    dialog.on("shown.bs.modal", function() {
        dialog.attr("id", "modalLoad");
    });
};

fecharModalLoad = function () {
    setTimeout(function(){ $("#modalLoad").modal('hide'); }, 1000);

};

mensagemErroUsuario = function () {
    var dialog = bootbox.dialog({
        title: 'Erro de autenticação', message: 'Você será redicerionado para tela de login.', closeButton: false
    });
    dialog.init(function(){
        setTimeout(function(){location.href = "/";}, 3000);
    });
};