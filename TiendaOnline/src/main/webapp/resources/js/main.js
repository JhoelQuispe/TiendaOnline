
window.oms = {};

jQuery(document).ready(function() {
	window.oms.applicationController = new window.oms.ApplicationController();
	window.oms.applicationController.go(jQuery(document.body));
});
