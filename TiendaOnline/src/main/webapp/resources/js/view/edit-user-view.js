window.oms = window.oms || {};

window.oms.EditUserView = function() {
  this.firstName = null;
  this.lastName = null;
  this.email = null;
  this.detailsTable = null;
  this.saveButton = null;
  this.cancelButton = null;
  this.contentDetailsPanel = document.createElement("DIV");
  
  this.init();
};

window.oms.EditUserView.prototype = {
  init: function() {
    jQuery(this.contentDetailsPanel).attr("width", "100%");

    // Create the contacts list
    //
    this.detailsTable = document.createElement("TABLE");
//    jQuery(this.detailsTable).attr("width", "100%");
    jQuery(this.detailsTable).addClass("contacts-ListContainer");
    this.firstName = document.createElement("INPUT");
    this.firstName.type = "text";
    this.lastName = document.createElement("INPUT");
    this.lastName.type = "text";
    this.email = document.createElement("INPUT");
    this.email.type = "text";
    this.initDetailsTable();
    jQuery(this.contentDetailsPanel).append(this.detailsTable);

    var menuPanel = document.createElement("DIV");
    this.saveButton = document.createElement("BUTTON");
    jQuery(this.saveButton).html("Save");
    this.cancelButton = document.createElement("BUTTON");
    jQuery(this.cancelButton).html("Cancel");
    jQuery(menuPanel).append(this.saveButton);
    jQuery(menuPanel).append(this.cancelButton);
    jQuery(this.contentDetailsPanel).append(menuPanel);
    jQuery(this.contentDetailsDecorator).add(this.contentDetailsPanel);
  },
  
  initDetailsTable : function() {
    jQuery(this.detailsTable).append(this.createRow("Firstname", this.firstName));
    jQuery(this.detailsTable).append(this.createRow("Lastname", this.lastName));
    jQuery(this.detailsTable).append(this.createRow("Email Address", this.email));
    //this.firstName.setFocus(true);
  },

  createRow : function(label, widget) {
    var row = document.createElement("TR");
    var cell = row.insertCell(0);
    jQuery(cell).html("<span>" + label + "</span>");
    cell = row.insertCell(1);
    jQuery(cell).append(widget);
    return row;
  },
  
  getFirstName : function() {
    return this.firstName;
  },

  getLastName : function() {
    return this.lastName;
  },

  getEmail : function() {
    return this.email;
  },

  getSaveButton : function() {
    return this.saveButton;
  },
  
  getCancelButton : function() {
    return this.cancelButton;
  },

  getFilterTextBox : function() {
	  return this.filterTextBox;
  },

  asWidget : function() {
    return this.contentDetailsPanel;
  }
};
