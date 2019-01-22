function scanActions() {
	$(".custom-action").each(function() {
		let action = $(this);
		action.click(function() {
			let url = action.attr("action-url");
			let value = action.attr("action-variable-value");
			let pathVariable = action.attr("action-path-variable");
			let requestParam = action.attr("action-request-param");
			let method = action.attr("action-method");
			let parentId = action.attr("parent-id");
			
			if (pathVariable && pathVariable === "true") {
				url = url+"/"+value+"?id="+parentId;
			} else {
				url = url+"?"+requestParam+"="+value+"&id="+parentId;
			}
			
			$.ajax({
				url: url,
				type: method,
				success: function(data) {
					if (parentId && parentId !== "") {
						$("#"+parentId).replaceWith(data);
					}
				},
				error: function(data) {
					createGlobalErrorMessage(data);
				}
			});
		});
	});
}