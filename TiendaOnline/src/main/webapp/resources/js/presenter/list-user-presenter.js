window.oms = window.oms || {};

/**
 * 
 * @param display
 * @returns {window.oms.ListUserPresenter}
 */
window.oms.ListUserPresenter = function(display, email) {
	this.display = display;
	this.email = email;
};

window.oms.ListUserPresenter.prototype = {
  bind : function() {
	  var that = this;
	  jQuery(this.display.getAddButton()).bind("click", function(event) {
		  var evt = jQuery.Event("addUserEvent");
		  jQuery(window).trigger(evt);
	  });

	  jQuery(this.display.getDeleteButton()).bind("click", function() {
		  that.deleteSelectedUsers();
	  });

	  jQuery(this.display.getFilterTextBox()).bind("change", function() {
		  var evt = jQuery.Event("filterUsersByEmailEvent");
		  evt.email = jQuery(this).val();
		  jQuery(window).trigger(evt);
//		  that.fetchUsersByEmail(jQuery(this).val());
	  });

  },

  bindList : function() {
	  var that = this;
	  // FIXME: deberia estar en la vista?
	  jQuery("TR > TD > SPAN", this.display.getList()).bind("click", function(event) {
		  var id = jQuery(this).attr("id");
		  if (id != undefined) {
			  var evt = jQuery.Event("editUserEvent");
			  evt.userId = id;
			  jQuery(window).trigger(evt);
		  } else {
			  console.log("WARN: clicked row doesn't contain any userId: " + event.currentTarget);
		  }
	  });
  },

  go : function(container) {
	  this.bind();
	  jQuery(container).empty();
	  jQuery(container).append(jQuery(this.display.asWidget()));
	  if (this.email == undefined) {
	  	this.fetchUserList();
	  } else {
	  	this.fetchUsersByEmail(this.email);
	  }
  },

  fetchUserList : function() {
	  var that = this;
	  $.ajax({
	    type : "GET",
	    url : "user"
	  }).done(function(msg) {
		  that.display.setData(msg);
		  that.bindList();
	  });
  },

  fetchUsersByEmail : function(email) {
	  var that = this;
	  $.ajax({
	    type : "GET",
	    url : "user?email=" + email
	  }).done(function(msg) {
		  that.display.setData(msg);
		  that.bindList();
	  });
  },

  deleteSelectedUsers : function() {
	  var that = this;
	  var ids = this.display.getSelectedIds();
	  if (ids.length == 0) {
	  	console.log("No users to delete.");
	  	return;
	  }
	  var idsAsParams = "id=" + ids.join("&id=");

	  $.ajax({
	    type : "DELETE",
	    url : "user?" + idsAsParams
	  }).done(function(msg) {
	  	if (msg) {
	  		that.fetchUserList();
	  	}
	  });
  }
};
