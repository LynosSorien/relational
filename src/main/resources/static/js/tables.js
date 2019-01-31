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
				var link = paginationEndpoint+"?"+pageVar+"="+$(this).attr("page")+"&"+sizeVar+"="+table.attr("size")+"&id="+id;
				$.ajax({
					url: link,
					method: 'GET',
					success: function(data) {
						table.replaceWith(data);
						scanTables();
					}
				});
			});
		});
		scanTableLinks(table);
	});
}

function scanTableLinks(table) {
    table.find(".table-link").each(function() {
        let currentLink = $(this);
        let linkClass = currentLink.attr("class");
        let link = currentLink.attr("link");
        let withPathVariable = currentLink.attr("with-path-variable");
        if (!withPathVariable) {
            withPathVariable = "false";
        }
        let type = currentLink.attr("type");
        let value = currentLink.attr("value");

        if (withPathVariable === "true") {
            if (link.substr(-1) !== '/') {
                link = link + '/';
            }
            link = link + value;
        }
        currentLink.attr("class", linkClass+' '+getClassAssociatedToLinkType(type));
        currentLink.click(function() {
            location.href = link;
        });
    });
}

function getClassAssociatedToLinkType(type) {
    if (type === 'BUTTON') {
        return 'btn';
    } else if (type === 'INFO_BUTTON') {
        return 'btn btn-info';
    } else if (type === 'DELETE_BUTTON') {
        return 'btn btn-danger';
    } else if (type === 'WARNING_BUTTON') {
        return 'btn btn-warning';
    } else if (type === 'SUCCESS_BUTTON') {
        return 'btn btn-success';
    }
    return '';
}