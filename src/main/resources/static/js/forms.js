$(document).ready(function() {
	loadDatepickers();

	$(".no-submittable-form").find(".custom-submit-button").css("display", "none");

});

function loadDatepickers() {
    $.fn.datepicker.defaults.format = "yyyy-mm-dd";
    $(".datepicker").each(function(){
        let datepicker = $(this);

        datepicker.datepicker({
            format: datepicker.attr("data-date-format")
        });
    });
}