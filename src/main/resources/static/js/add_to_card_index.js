function addToCard(productId) {
	quantity = 1;
	url = "/gio-hang/add/" + productId;
	$.ajax({
		type: "POST",
		url: url,
		beforeSend: function(xhr) {
			xhr.setRequestHeader(csrfHeaderName, csrfValue);
		}
	}).done(function(response) {
		$("#modalTitle").text("Giỏ hàng");
		$("#modalBody").text(response);
		$("#myModal").modal();
		
	}).fail(function() {
		$("#modalTitle").text("Giỏ hàng");
		$("#modalBody").text("Xảy ra lỗi khi thêm sản phẩm!");
		$("#myModal").modal();
	});
	setTimeout(function() {// wait for 5 secs(2)
			$('#myModal').modal('toggle'); // close modal
		}, 5000);
}


