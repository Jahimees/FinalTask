function addLabel(p_id) {
    var p_element = $("#" + p_id)[0];
    if (typeof p_element != "undefined" && typeof input_element != "undefined") {
        p_element.remove();
    }

    createLabel(p_id);
}

function addLabelWithInput(p_id, input_id, input_name) {
    var p_element = $("#" + p_id)[0];
    var input_element = $("#" + input_id)[0];

    if (typeof p_element != "undefined" && typeof input_element != "undefined") {
        p_element.remove();
        input_element.remove();
    }

    var input = document.createElement("input");
    var form = $("#popup_form")[0];

    createLabel(p_id);
    form.appendChild(input);

    input.id = input_id;
    input.name = input_name;
    input.type="text";
    $("#" + input_id).attr("class", "inputp");
}

function addLabelWithTextarea(p_id, teaxtarea_id, textarea_name) {
    var p_element = $("#" + p_id)[0];
    var textarea_element = $("#" + teaxtarea_id)[0];

    if (typeof p_element != "undefined" && typeof textarea_element != "undefined") {
        p_element.remove();
        textarea_element.remove();
    }

    var textarea = document.createElement("textarea");
    var form = $("#popup_form")[0];

    createLabel(p_id);
    form.appendChild(textarea);

    textarea.id = teaxtarea_id;
    textarea.name = textarea_name;
    textarea.style.width = "80%";
    textarea.style.height = "200px";
}

function createLabel(p_id) {
    var p = document.createElement("p");
    p.id = p_id;
    var form = $("#popup_form")[0];

    form.appendChild(p);
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

function addDefaultFields() {
    var h1 = document.createElement('h1');
    var p1 = document.createElement('p');
    var p2 = document.createElement('p');

    h1.id = "popup_title";
    p1.style.padding = "15px 0 30px 0";
    p1.id = "popup_text";
    p2.style.font = "10px";
    p2.id = "popup_small_text";

    var form = $("#popup_form")[0];

    form.appendChild(h1);
    form.appendChild(p1);
    form.appendChild(p2);
}

function removeDefaultFields() {
    var form = $("#popup_form")[0];
    while(form.firstChild) {
        form.removeChild(form.firstChild);
    }
    $("#popup_title").remove();
    $("#popup_text").remove();
    $("#popup_small_text").remove();
}
