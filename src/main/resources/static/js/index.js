$(function(){
	$("#publishBtn").click(publish);
});

function publish() {
	$("#publishModal").modal("hide");

	//��ȡ���������
    var title = $("#recipient-name").val();
    var content = $("#message-text").val();
    // �����첽����
    $.post(
        CONTEXT_PATH + "/discuss/add",
        {"title":title,"content":content},
        function(data){
            data = $.parseJSON(data);
			// ����ʾ������ʾ���ص���Ϣ
            $("#hintBody").text(data.msg);
			// ��ʾ��ʾ��
			$("#hintModal").modal("show");
			// 2����Զ�������ʾ��
			setTimeout(function(){
				$("#hintModal").modal("hide");
	            // ˢ��ҳ��
				if(data.code === 0)
					window.location.reload();

			},2000);
        }
    );

	$("#hintModal").modal("show");
	setTimeout(function(){
		$("#hintModal").modal("hide");
	}, 2000);
}