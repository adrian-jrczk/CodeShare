
function switchSyntaxSupport() {
	if (document.getElementById('syntax-checkbox').checked) {
		var extension = document.getElementById('syntax-id').value;
		var fileName = "file" + extension;
		var mode = ace.require("ace/ext/modelist").getModeForPath(fileName).mode;
		editor.getSession().setMode(mode);
	} else {
		editor.session.setMode("ace/mode/text");
	}
}

function extractCodeFromEditor() {
	document.getElementById('stringValue').value = editor.getValue();
	return true;
}

function resetOptions() {
	document.getElementById("name").value = "";
	document.getElementById("time-restriction").value = "";
	document.getElementById("views-restriction").value = "";
	document.getElementById("private").checked = false;
	document.getElementById("editable").checked = false;
}

function hidePanel() {
	document.getElementById("side-panel").style.display = "none";
	document.getElementById("show-panel-button").style.display = "block";
}

function showPanel() {
	document.getElementById("show-panel-button").style.display = "none";
	document.getElementById("side-panel").style.display = "block";
}

function copyUUIDToClipboard() {
	 navigator.clipboard.writeText(document.getElementById('uuid').value);
}

function copyCodeToClipboard() {
	 navigator.clipboard.writeText(editor.getValue());
}

function goTop() {
	document.body.scrollTop = 0;
	document.documentElement.scrollTop = 0;
} 

function closeResponseBox() {
	document.getElementById("serviceResponse-box").style.display = "none";
}
