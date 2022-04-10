$(document).ready(function() {
	$(".minusButton").on("click", function(evt) {
		evt.preventDefault();
		decreaseQuantity($(this));
	});

	$(".plusButton").on("click", function(evt) {
		evt.preventDefault();
		increaseQuantity($(this));
	});
	$(".link-remove").on("click", function(evt) {
		evt.preventDefault();
		removeFromCart($(this));
		location.reload();
		
	});

	updateTotal();
});

function decreaseQuantity(link) {
	productId = link.attr("pid");
	qtyInput = $("#quantity" + productId);

	newQty = parseInt(qtyInput.val());
	if (newQty > 0) {
		qtyInput.val(newQty);
		updateQuantity(productId, newQty);
	}
}
function removeProduct(rowNumber){
	rowId = "row" + rowNumber;
	
	$("#" + rowId).remove();
}
function removeFromCart(link) {
	url = link.attr("href");
	
	$.ajax({
		type: "POST",
		url: url,
		beforeSend: function(xhr) {
			xhr.setRequestHeader(csrfHeaderName, csrfValue);
		}
	}).done(function(response) {
		
		$("#modalTitle").text("Giỏ hàng");
		if(response.includes("đã được loại khỏi")){
			$("#myModal").on("hide.bs.modal", function(e){
				rowNumber = link.attr("rowNumber");
				removeProduct(rowNumber);
				updateTotal();
			});
		}
		$("#modalBody").text(response);
		$("#myModal").modal();
	}).fail(function() {
		
		$("#modalTitle").text("Giỏ hàng");
		$("#modalBody").text("Xảy ra lỗi khi thêm sản phẩm!");
		$("#myModal").modal();
	});
}

function increaseQuantity(link) {
	productId = link.attr("pid");
	qtyInput = $("#quantity" + productId);

	newQty = parseInt(qtyInput.val());
	if (newQty < 10) {
		qtyInput.val(newQty);
		updateQuantity(productId, newQty);
	}
}
function updateQuantity(productId, quantity) {
	url = "/gio-hang/update/" + productId + "/" + quantity;
	$.ajax({
		type: "POST",
		url: url,
		beforeSend: function(xhr) {
			xhr.setRequestHeader(csrfHeaderName, csrfValue);
		}
	}).done(function(newSubtotal) {
		updateSubtoptal(newSubtotal, productId);
		updateTotal();
	}).fail(function() {
		$("#modalTitle").text("Giỏ hàng");
		$("#modalBody").text("Xảy ra lỗi khi thêm sản phẩm!");
		$("#myModal").modal();
	});
}
function formatNumber(num) {
	return num.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,")
}
function updateSubtoptal(newSubtotal, productId) {
	$("#subtotal" + productId).text(formatNumber(newSubtotal) + " VNĐ");
}

function updateTotal() {
	total = 0;
	$(".priceCart").each(function(index, element) {
		var str1 = '';
		var str2 = '';
		str1 = element.innerHTML.replace(new RegExp(',', 'g'), "");
		str2 = str1.replace(new RegExp('VND', 'g'), "");
		total = total + parseInt(str2);
	});


	$('#totalAllQA').text(formatNumber(total) + " VND");

}



