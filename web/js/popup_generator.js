function addLabelWithInput(p_id, input_id, input_name) {
    var p_element = $("#" + p_id)[0];
    var input_element = $("#" + input_id)[0];

    if (typeof p_element != "undefined" && typeof input_element != "undefined") {
        p_element.remove();
        input_element.remove();
    }

    var p = document.createElement("p");
    p.id = p_id;
    var input = document.createElement("input");
    var form = $("#popup_form")[0];

    form.appendChild(p);
    form.appendChild(input);

    input.id = input_id;
    input.name = input_name;
    input.type="text";
    $("#" + input_id).attr("class", "inputp");
}

function addConfirmButton(confirm_name) {
    var confirm_element = $("#" + confirm_name)[0];
    if (typeof confirm_element != "undefined") {
        confirm_element.remove();
    }

    var input = document.createElement("input");
    $("#popup_form")[0].appendChild(input);
    input.type = "submit";
    input.id = confirm_name;
    $("#" + confirm_name).attr("class", "show-btn");
    input.formMethod = "post";
}

function removeDefaultFields() {
    $("#popup_title").remove();
    $("#popup_text").remove();
    $("#popup_small_text").remove();
}
