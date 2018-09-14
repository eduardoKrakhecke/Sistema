function mensagemConfirmacaoExclusao(callback) {
    bootbox.confirm({
        message: "Ao continuar registro será excluído permanentemente",
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