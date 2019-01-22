function createCallbackObject(data) {
	let callbacks = [];
	if (data.attr("callbacks")) {
		var callbacksForm = data.attr("callbacks").split(",");
		var callbackTypes = data.attr("callback-types").split(",");
		var callbackUrls = data.attr("callback-urls").split(",");
		for (let index = 0; index < callbacksForm.length ; index++) {
			var callbackItem = {};
			callbackItem['id'] = callbacksForm[index];
			callbackItem['type'] = callbackTypes[index];
			callbackItem['url'] = callbackUrls[index];
			callbacks.push(callbackItem);
		}
	}
	return callbacks;
}

function callCallbacks(data, callbackObjects) {
	for (let i = 0; i < callbackObjects.length; i++) {
		callCallback(data, callbackObjects[i]);
	}
}

function callCallback(data, callbackObject) {
	let id = callbackObject['id'], type = callbackObject['type'], url = callbackObject['url'];
	let htmlObject = $("#"+id);
	if (htmlObject) {
		$.ajax({
			type: 'GET',
			url: url,
			success: function(d) {
				htmlObject.replaceWith(d);
			},
			error: function(d) {
				createGlobalErrorMessage(d);
			}
		});
	}
}