window.oms = window.oms || {};

/**
 * 
 * @param display
 * @returns {window.oms.EditUserPresenter}
 */
window.oms.EditUserPresenter = function (display, userId) {
  this.display = display;
  this.bind();
  if (userId != null && userId != undefined) {
    this.fetchUser(userId);
  } else {
    this.user = {
        firstName : "",
        lastName : "",
        email : ""
    };
  }
};

window.oms.EditUserPresenter.prototype = {
  bind : function() {
    var that = this;
    jQuery(this.display.getSaveButton()).bind("click", function() {   
        that.doSave();
    });

    jQuery(this.display.getCancelButton()).bind("click", function() {
      var event = jQuery.Event("editUserCancelledEvent");
      jQuery(window).trigger(event);
    });
  },

  go : function(container) {
    jQuery(container).empty();
    jQuery(container).append(jQuery(this.display.asWidget()));
  },

  fetchUser : function(userId) {
    var that = this;
    $.ajax({
      type: "GET",
      url: "user/" + userId
    }).done(function( response ) {
      that.user = response;
      jQuery(that.display.getFirstName()).val(that.user.firstName);
      jQuery(that.display.getLastName()).val(that.user.lastName);
      jQuery(that.display.getEmail()).val(that.user.email);
    });
  },
  
  doSave : function() {
    this.user.firstName = jQuery(this.display.getFirstName()).val();
    this.user.lastName = jQuery(this.display.getLastName()).val();
    this.user.email = jQuery(this.display.getEmail()).val();

    $.ajax({
      type: "POST",
      url: "user",
      contentType : "application/json",
      data: JSON.stringify(this.user)
    }).done(function( response ) {
      var evt = jQuery.Event("userUpdatedEvent");
      evt.user = response;
      jQuery(window).trigger(evt);
    });
  }

};
