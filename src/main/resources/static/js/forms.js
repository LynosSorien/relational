$(document).ready(function() {
	loadDatepickers();
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