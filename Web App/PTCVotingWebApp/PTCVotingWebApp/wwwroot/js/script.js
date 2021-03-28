

function buildRunners() {
    var runners = "";
    var names = document.getElementsByName("name[]");
    var partys = document.getElementsByName("party[]");
    var descriptions = document.getElementsByName("description[]");

    for (var i = 0; i < names.length; i++) {
        if (descriptions[i].value == "") {
            runners += names[i].value + "~" + partys[i].value + "~NoDesc;";
        } else {
            runners += names[i].value + "~" + partys[i].value + "~" + descriptions[i].value + ";";
        }
    }
    document.getElementById("runners").value = runners;
    document.getElementById("save").value = "Save";
    //alert(runners);
    document.getElementById('update').submit();
}

function cancel() {
    document.getElementById("save").value = "No";
    document.getElementById('update').submit();
}


let place = '';

function picker(where) {
    var conferm = document.getElementById("confermation");
    conferm.style.visibility = 'visible';
    place = where;
}

function send(name, party, description) {
    var conferm = document.getElementById("confermation");
    conferm.style.visibility = 'hidden';
    document.getElementById(place + "NameB").innerHTML = name;
    document.getElementById(place + "Name").value = name;
    document.getElementById(place + "PartyB").innerHTML = party;
    document.getElementById(place + "Party").value = party;
    document.getElementById(place + "DesB").innerHTML = description;
    document.getElementById(place + "Des").value = description;
}

function remove(where) {
    //check to make sure before deploying just testing to see if it works for now
    document.getElementById(where).remove();
}

let count = -1;
function AddRunner() {
    count -= 1;
    var con = document.getElementById("poli");

    var container = document.createElement("div");
    container.setAttribute("style", "border: 0.15em solid black; padding: 1em; margin-top: 1em;");
    container.setAttribute("id", count + "Div");

    var row1 = document.createElement("div");
    row1.setAttribute("class", "row");
    row1.setAttribute("style", "margin-bottom: 1em;");

    var col1 = document.createElement("div");
    col1.setAttribute("class", "col-6");

    var label1 = document.createElement("label");
    label1.innerHTML = "Name:";

    var input1 = document.createElement("input");
    input1.setAttribute("id", count + "Name");
    input1.setAttribute("name", "name[]");
    input1.setAttribute("style", "width: 100%;");
    input1.setAttribute("type", "hidden");

    var p1 = document.createElement("p");
    p1.setAttribute("id", count + "NameB");

    col1.append(label1);
    col1.append(input1);
    col1.append(p1);

    var col2 = document.createElement("div");
    col2.setAttribute("class", "col-6");

    var label2 = document.createElement("label");
    label2.innerHTML = "Party:";

    var input2 = document.createElement("input");
    input2.setAttribute("id", count + "Party");
    input2.setAttribute("name", "party[]");
    input2.setAttribute("style", "width: 100%;");
    input2.setAttribute("type", "hidden");

    var p2 = document.createElement("p");
    p2.setAttribute("id", count + "PartyB");

    col2.append(label2);
    col2.append(input2);
    col2.append(p2);
    row1.append(col1);
    row1.append(col2);

    var row2 = document.createElement("div");
    row2.setAttribute("class", "row");

    var col3 = document.createElement("div");
    col3.setAttribute("class", "col-12");

    var label3 = document.createElement("label");
    label3.innerHTML = "Description:";

    var input3 = document.createElement("input");
    input3.setAttribute("id", count + "Des");
    input3.setAttribute("name", "description[]");
    input3.setAttribute("type", "hidden");

    var p3 = document.createElement("p");
    p3.setAttribute("id", count + "DesB");
    p3.setAttribute("style", "min-height: 8em;")

    col3.append(label3);
    col3.append(input3);
    col3.append(p3);
    row2.append(col3);

    var newP = document.createElement("a");
    newP.setAttribute("href", "javascript:{}");
    newP.setAttribute("onclick", "picker('" + count + "');");
    newP.innerHTML = "Pick new Politician";

    var removeP = document.createElement("a");
    removeP.setAttribute("href", "javascript:{}");
    removeP.setAttribute("onclick", "remove('" + count + "Div');");
    removeP.innerHTML = "Remove Politician";

    container.append(row1);
    container.append(row2);
    container.append(newP);
    container.append(removeP);

    con.append(container);
}