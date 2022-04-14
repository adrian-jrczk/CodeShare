
const codeArea = document.getElementById("code-area");
if (codeArea != null) {
	codeArea.setAttribute("style", "height:" + (codeArea.offsetHeight) + "px");
	codeArea.addEventListener("input", adjustHeight, false);
}

function adjustHeight() {
	this.style.height = "auto";
	this.style.height = this.scrollHeight + "px";
}

function clearOptions() {
	document.getElementById("name").value = "";
	document.getElementById("time-restriction").value = "";
	document.getElementById("views-restriction").value = "";
	document.getElementById("private").checked = false;
	document.getElementById("editable").checked = false;
}

function clearCode() {
	document.getElementById("code-area").value = "";
	document.getElementById("code-area").style.width = "1100px";
	document.getElementById("code-area").style.height = "730px";
}

function hidePanel() {
	document.getElementById("side-panel").style.display = "none";
	document.getElementById("show-panel-button").style.display = "block";
}

function showPanel() {
	document.getElementById("show-panel-button").style.display = "none";
	document.getElementById("side-panel").style.display = "block";
}

function copyValue(elementId) {
	 navigator.clipboard.writeText(document.getElementById(elementId).value);
}

function goTop() {
	document.body.scrollTop = 0;
	document.documentElement.scrollTop = 0;
} 

function closeResponseBox() {
	document.getElementById("response-box").style.display = "none";
}
