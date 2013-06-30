window.oms = window.oms || {};

// constructor
window.oms.ListUserView = function () {
	this.widget = document.createElement("DIV");
	this.addButton  = document.createElement("BUTTON");
	this.deleteButton  = document.createElement("BUTTON");
	this.table  = document.createElement("TABLE");
	this.filterTextBox = document.createElement("INPUT");
	this.init();
};

window.oms.ListUserView.prototype = {
	init : function() {
		this.addButton.innerHTML = "Add";
		this.deleteButton.innerHTML = "Delete";
		jQuery(this.widget).append(this.filterTextBox);
		jQuery(this.widget).append(this.table);
		jQuery(this.widget).append(this.addButton);
		jQuery(this.widget).append(this.deleteButton);
	},

	asWidget : function() {
		return this.widget;
	},

	setData : function(data) {
		var strTable = "";
		for (var i=0; i<data.length; i++) {
			var elemId = id=" id='" + data[i].id + "'";
			strTable += "<tr><td><input type='checkbox' " + elemId + " />";
			strTable += "<span " + elemId + " title='" + data[i].email + "'>";
			strTable += data[i].firstName + " " + data[i].lastName;
			strTable += "</span></td></tr>";
		}
		jQuery(this.table).html(strTable);
	},
	
	getAddButton : function() {
		return this.addButton;
	},
	
	getDeleteButton : function() {
		return this.deleteButton;
	},

	getFilterTextBox : function() {
		return this.filterTextBox;
	},

	getList : function() {
		return this.table;
	},
	
	getSelectedIds : function() {
		var ids = [];
		jQuery("TR > TD > INPUT:checked", this.getList()).each(function() {
			ids.push(jQuery(this).attr("id"));
		});
		return ids;
	}
};
