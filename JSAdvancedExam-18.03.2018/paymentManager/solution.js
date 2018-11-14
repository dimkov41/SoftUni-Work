class PaymentManager {
    constructor(title) {
        this.title = title;
    }

    render(id) {
        let table = $("<table>")
            .append(
                $("<caption>")
                    .text(`${this.title} Payment Manager`)
            );

        let thead = $("<thead>")
            .append(
                $("<tr>")
                    .append(
                        $("<th>").addClass("name").text("Name")
                    )
                    .append(
                        $("<th>").addClass("category").text("Category")
                    )
                    .append(
                        $("<th>").addClass("price").text("Price")
                    )
                    .append(
                        $("<th>").text("Actions")
                    )
            );

        table.append(thead);

        let tbody = $("<tbody>").addClass("payments");

        table.append(tbody);

        let tfoot = $("<tfoot>").addClass("input-data");
        let tFootTr = $("<tr>");

        let nameTd = $("<td>").append($("<input>").attr("name", "name").attr("type", "text"));
        let categoryTd = $("<td>").append($("<input>").attr("name", "category").attr("type", "text"));
        let priceTd = $("<td>").append($("<input>").attr("name", "price").attr("type", "number"));

        tFootTr
            .append(nameTd)
            .append(categoryTd)
            .append(priceTd);


        let addButton = $("<td>")
            .append(
                $("<button>")
                    .text("Add")
                    .click(() => {
                        let name = $(nameTd.children()[0]);
                        let category = $(categoryTd.children()[0]);
                        let price = $(priceTd.children()[0]);

                        if (name.val().length > 0 && category.val().length > 0 && price.val().length > 0) {
                            let tr = $("<tr>")
                                .append(
                                    $("<td>").text(name.val())
                                )
                                .append(
                                    $("<td>").text(category.val())
                                )
                                .append(
                                    $("<td>").text(parseFloat(price.val()))
                                )
                                .append(
                                    $("<td>").append(
                                        $("<button>")
                                            .text("Delete")
                                            .click(() => {
                                                $(tr).remove();
                                            })
                                    )
                                );

                            name.val("");
                            category.val("");
                            price.val("");

                            $(tbody).append(tr);
                        }
                    })
            );
        tFootTr.append(addButton);
        tfoot.append(tFootTr);

        table.append(tfoot);

        $("#" + id).append(table);
    };

}