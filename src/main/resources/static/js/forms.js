$(document).ready(function() {
	customForms();
});


function customForms() {
	$(".custom-forms").each(function() {
		let customForm = $(this);
		let link = customForm.attr("link");
		let callbacks = [];
		if (customForm.attr("callbacks")) {
			var callbacksForm = customForm.attr("callbacks").split(",");
			var callbackTypes = customForm.attr("callback-types").split(",");
			var callbackUrls = customForm.attr("callback-urls").split(",");
			for (let index = 0; index < callbacksForm.length ; index++) {
				var callbackItem = {};
				callbackItem['id'] = callbacksForm[index];
				callbackItem['type'] = callbackTypes[index];
				callbackItem['url'] = callbackUrls[index];
				callbacks.push(callbackItem);
			}
		}
		let method = customForm.attr("method");
		
		customForm.find(".custom-submit-button").each(function() {
			let button = $(this);
			button.click(function() {
				let postObject = {};
				let paramsLink = "";
				customForm.find(".form-item[submit=true]").each(function(){
					var currentItem = $(this);
					var currentValue = currentItem.val();
					if (currentValue && currentValue !== "") {
						if (method === "POST") {
							postObject[currentItem.attr("variable")] = currentValue;
						} else if (method === "GET") {
							if (paramsLink !== "") {
								paramsLink = paramsLink + "&";
							}
							paramsLink = paramsLink+currentItem.attr("variable")+"="+currentValue;
						}
					}
				});
				
				if (method === "GET") {
					$.ajax({
						type: 'GET',
						url: link+"?"+paramsLink,
						success: function(data) {
							createFormResultMessage(data, "success");
						},
						error: function(data) {
							createFormResultMessage(data, "error");
						}
					});
				} else {
					$.ajax({
						type: method,
						url: link,
						dataType: 'json',
						contentType: 'application/json',
						data: JSON.stringify(postObject),
						success: function(data) {
							createFormResultMessage(data, "success");
						},
						error: function(data) {
							createFormResultMessage(data, "error");
						}
					});
				}
			});
		});
	});
}

function createFormResultMessage(data, state) {
	let message = null;
	if (!data) {
		if (state === "success") {
			message="The request have been processed correctly";
		} else if (state === "error") {
			message="Unknown error while trying to process the request";
		}
	} else {
		if (data.message) {
			message = data.message;
		} else {
			message = data;
		}
	}
	if (state === "success") {
		createGlobalSuccessMessage(message);
	} else if (state === "error") {
		createGlobalErrorMessage(message);
	}
}