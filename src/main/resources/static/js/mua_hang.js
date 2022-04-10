$(document).ready(function() {
	$("#button2MuaHang").on("click", function(e) {
		muaHang();


	});
});

function muaHang() {
	url = "/gio-hang/mua-hang";
	$.ajax({
		type: "POST",
		url: url,
		beforeSend: function(xhr) {
			xhr.setRequestHeader(csrfHeaderName, csrfValue);
		}
	}).done(function(response) {
		$("#modalTitle").text("Giỏ hàng");
		if (response.includes("Sẽ chuyển hướng trong 2s!")) {

			window.location = '/hoa-don';

		}
		$("#modalBody").text(response);
		$("#myModal").modal();
	}).fail(function() {
		$("#modalTitle").text("Giỏ hàng");
		$("#modalBody").text("Xảy ra lỗi khi thêm sản phẩm!");
		$("#myModal").modal();
	});
}

