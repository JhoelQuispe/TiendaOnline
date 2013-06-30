window.oms = window.oms || {};

// constructor
window.oms.ApplicationController = function() {
	this.bind();
};

window.oms.ApplicationController.prototype = {
  bind : function() {
	  var that = this;
	  jQuery(window).hashchange(function(event) {
		  that.onValueChange(that.getHistoryToken());
	  });

	  jQuery(window).bind("addUserEvent", function(event) {
		  that.doAddNewContact();
	  });

	  jQuery(window).bind("editUserEvent", function(event) {
		  that.doEditContact(event.userId);
	  });

	  jQuery(window).bind("editUserCancelledEvent", function(event) {
	  	that.doEditContactCancelled();
	  });

	  jQuery(window).bind("userUpdatedEvent", function(event) {
	  	that.doContactUpdated();
	  });
	  
	  jQuery(window).bind("filterUsersByEmailEvent", function(event) {
	  	that.doFilteredUsersByEmail(event.email);
	  });
  },

  decodeFragment : function(encodedFragment) {
	  // decodeURI() does *not* decode the '#' character.
	  return decodeURI(encodedFragment.replace("%23", "#"));
  },

  encodeFragment : function(fragment) {
	  // encodeURI() does *not* encode the '#' character.
	  return encodeURI(fragment).replace("#", "%23");
  },

  getHistoryToken : function() {
	  return this.decodeFragment(location.hash.replace("#", ""));
  },

  setHistoryToken : function(token, issueEvent) {
	  location.hash = this.encodeFragment(token);
	  // TODO: mover history management a otra clase (ver implementacion de
		// History en GWT)
	  // issueEvent = issueEvent == undefined ? true : issueEvent;
	  // if (issueEvent) {
	  // jQuery(window).hashchange();
	  // }
  },

  go : function(container) {
	  this.container = container;

	  if ("" == this.getHistoryToken()) {
		  this.setHistoryToken("list");
	  } else {
		  jQuery(window).hashchange();
	  }
  },

  onValueChange : function(token) {
	  if (token != null) {
		  var presenter = null;
		  if (token == "list") {
			  presenter = new window.oms.ListUserPresenter(new window.oms.ListUserView());
		  } else if (token.indexOf("list") == 0) {
		  	var email = token.substring(token.indexOf("/")+1);
				presenter = new window.oms.ListUserPresenter(new window.oms.ListUserView(), email);
		  } else if (token == "add") {
			  presenter = new window.oms.EditUserPresenter(new window.oms.EditUserView());
		  } else if (token.indexOf("edit") == 0) {
		  	var userId = token.substring(token.indexOf("/")+1);
			  presenter = new window.oms.EditUserPresenter(new window.oms.EditUserView(), userId);
		  }

		  if (presenter != null) {
			  presenter.go(this.container);
		  }
	  }
  },

  doAddNewContact : function() {
	  this.setHistoryToken("add");
  },

  doEditContact : function(userId) {
	  this.setHistoryToken("edit/" + userId, false);
//	  var presenter = new window.oms.EditUserPresenter(new window.oms.EditUserView(), userId);
//	  presenter.go(this.container);
  },

  doEditContactCancelled : function() {
	  this.setHistoryToken("list");
  },

  doContactUpdated : function() {
	  this.setHistoryToken("list");
  },

  doFilteredUsersByEmail : function(email) {
	  this.setHistoryToken("list/" + email, false);
  }
};
