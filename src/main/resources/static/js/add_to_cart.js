$(document).ready(function() {
	$("#buttonAdd2Cart").on("click", function(e) {
		addToCard();

		setTimeout(function() {// wait for 5 secs(2)
			location.reload(); // then reload the page.(3)
		}, 5000);

	});
});

function addToCard() {
	quantity = $("#quantity" + productId).val();
	url = "/gio-hang/add/" + productId + "/" + quantity;
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
}

