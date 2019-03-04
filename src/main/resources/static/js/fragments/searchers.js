$(document).ready(function() {
    simpleSearchersActivator();
    $(".selectable-table").each(function(){
        $(this).dataTable();
    });
    activateSelectableTables();
});

function activateSelectableTables() {
    $(".selectable-table").each(function(){
        let dataTable = $(this);
        dataTable.find("tbody").find("tr").click(function() {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
            } else {
                dataTable.find('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        });
    });
}

function simpleSearchersActivator() {
    $(".searcher-simple").each(function() {
        let searcherSimple = $(this);
        let searchLink = searcherSimple.attr("search-link");
        let acceptLink = searcherSimple.attr("accept-link");
        searcherSimple.find(".searcher-shown-field").each(function() {
            $(this).click(function() {
                searcherSimple.find(".popup-searcher-simple").css("display", "block");
            });
        });
        searcherSimple.find(".cancel-button-searcher").click(function() {
            searcherSimple.find(".popup-searcher-simple").css("display", "none");
        });

        searcherSimple.find(".accept-button-searcher").click(function() {
            let selected = searcherSimple.find("tr.selected");
            if (selected) {
                searcherSimple.find(".searcher-shown-field").each(function() {
                    $(this).val(selected.find("[variable='"+$(this).attr("attribute")+"']").val());
                });
            } else {
                searcherSimple.find(".searcher-shown-field").each(function() {
                    $(this).val("");
                });
            }
            searcherSimple.find(".popup-searcher-simple").css("display", "none");
        });

        searcherSimple.find(".search-button-searcher").click(function() {
            let currentSearch = searchLink;
            let filters = searcherSimple.find(".searcher-filter");
            currentSearch += "?";
            for (var i = 0; i < filters.length; i++) {
                currentSearch+=$(filters[i]).attr("attribute")+"="+$(filters[i]).val();
                if (i < filters.length-1) {
                    currentSearch+="&";
                }
            }
            $.ajax({
                url: currentSearch,
                method: 'GET',
                success: function(data) {
                    searcherSimple.find(".simple-table-fw").replaceWith(data);
                    scanTables();
                    activateSelectableTables();
                }
            });
        });
    });
}