$(document).ready(function() {
	scanTables();
});

function scanTables() {
	$(".paged-table").each(function() {
		let table = $(this);
		let id = table.attr("id");
		let paginationEndpoint = table.attr("pagination-link");
		let pageVar = "page";
		if (table.attr("page-var")) {
			pageVar = table.attr("page-var");
		}
		let sizeVar = "size";
		if (table.attr("size-var")) {
			sizeVar = table.attr("size-var");
		}
		table.find(".paged-table-pagination").each(function() {
			let currentPage = $(this);
			currentPage.find(".link-usable").click(function() {
				var link = paginationEndpoint+"?"+pageVar+"="+$(this).attr("page")+"&"+sizeVar+"="+table.attr("size");
				$.ajax({
					url: link,
					method: 'GET',
					success: function(data) {
						table.replaceWith(data);
					}
				});
			});
		});
	});
}