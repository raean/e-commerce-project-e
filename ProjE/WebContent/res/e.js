
function init() {
	show("dashDiv");
}

function show(v) {
	hideAll();
	let n = document.getElementById(v); 
	n.style.display = "block";
}

function showPrime() {
	show("primeDiv");
}

function showSis() {
	show("sisDiv");
}

function hideAll() {
	let list = document.getElementsByClassName("view");
	for (let e of list) {
		e.style.display = "none";
	}
}

function prime(f) {
	let min = f.elements["min"].value;
	let max = f.elements["max"].value;
	let qs = "min=" + min + "&max=" + max; 
	doSimpleAjax("Prime.do", qs, primeResult);
}

function nextPrime(f) {
	let min	= document.getElementById("minValue").value;
	let max = document.getElementById("maxValue").value;
	let qs = "min=" + min + "&max=" + max;
	doSimpleAjax("Prime.do", qs, nextPrimeResult);
}


function primeResult(request) {
	if ((request.readyState == 4) && (request.status == 200)) {
		let resp = JSON.parse(request.responseText);
		let html = "";
		if (resp.status == 1) {
			html += "<p>The answer is " + resp.result + "</p>";
			let primeRes = document.getElementById("primeResult");
			primeRes.innerHTML = html;
			if (document.getElementById("nextPrimeForm") == null) {
				createNextForm();
			}
		} else {
			html += "<p style='color:red'>Error: " + resp.error + "</p>";		
			document.getElementById("primeResult").innerHTML = html;
		}
	}
}

function nextPrimeResult(request) {
	if ((request.readyState == 4) && (request.status == 200)) {
		let resp = JSON.parse(request.responseText);
		let html = "";
		if (resp.status == 1) {
			html += "<p>The answer is " + resp.result + "</p>";
			let minValue = document.getElementById("minValue");
			minValue.value = resp.result;
			prime(document.getElementById("primeForm"));
		} else {
			html += "<p style='color:red'>Error: " + resp.error + "</p>";		
			document.getElementById("primeResult").innerHTML = html;
		}
	}
}

function createNextForm() {
	let primeRes = document.getElementById("primeResult");
	let nextForm = document.createElement("form");
	nextForm.id = "nextPrimeForm";
	nextForm.setAttribute("onsubmit", "nextPrime(this); return false;");
	primeRes.appendChild(nextForm);
	
	let nextButton = document.createElement("input");
	nextButton.value = "Next";
	nextButton.type = "submit";
	nextForm.appendChild(nextButton);
}


function sis(f) {
	let minGpa = f.elements["minGpa"].value;
	let sortOption = f.elements["sortBy"].value;
	let name = f.elements["name"].value;
	let qs = "name=" + name + "&minGpa=" + minGpa + "&sortBy=" + sortOption; 
	doSimpleAjax("Sis.do", qs, sisResult);
}

function sisResult(request) {
	if ((request.readyState == 4) && (request.status == 200)) {
		let resp = JSON.parse(request.responseText);
		let html = "";
	
		alert("0");
		
		if (resp.status == 1) {
			if (resp.result.length > 0) {
				html += "<p>The answer is <p>";
				html += "<table border=1pt>";
				html += "<tr><td>Name</td><td>Major</td><td>Courses</td><td>GPA</td></tr>";
				for(var i = 0 ; i < resp.result.length ; i++) {
					html += "<tr><td>" + resp.result[i].name + "</td>" +
					"<td>" + resp.result[i].major + "</td>" +
					"<td>" + resp.result[i].courses + "</td>" +
					"<td>" + resp.result[i].gpa + "</td>" +
					"</tr>"
				}
				html+= "</table>"
			} else {
				html += "<p style='color:red'>Error:  No entries found!  </p>";
			}
		} else {
			html += "<p style='color:red'>Error: " + resp.error + "</p>";
		}
		document.getElementById("sisResult").innerHTML = html;
	}
}

function doSimpleAjax(address, data, handler) {
    var request = new XMLHttpRequest();
    request.onreadystatechange = function() {handler(request);};
    request.open("GET", (address + "?" + data), true);
    request.send(null);
}




