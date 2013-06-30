/*
 * Copyright (c) 2008 Greg Weber greg at gregweber.info
 * Dual licensed under the MIT and GPLv2 licenses just as jQuery is:
 * http://jquery.org/license
 *
 * documentation at http://gregweber.info/projects/uitablefilter
 *
 * allows table rows to be filtered (made invisible)
 * <code>
 * t = $('table')
 * $.uiTableFilter( t, phrase )
 * </code>
 * arguments:
 *   jQuery object containing table rows
 *   phrase to search for
 *   optional arguments:
 *     column to limit search too (the column title in the table header)
 *     ifHidden - callback to execute if one or more elements was hidden

 * MODIFIED for Reputation.com (Rob Hoexter) 
 *   rather than defining a generic jQuery function, is now a valid jQuery plugin.  the first argument (jquery object containing table rows)
 *   is now the contextual jQuery object referenced by "this".

 */
(function($) {
  $.fn.uiTableFilter = function(phrase, column, ifHidden, showAny){
    var new_hidden = false;
    if( this.last_phrase === phrase ) return false;

    var phrase_length = phrase.length;
    var words = phrase.toLowerCase().split(" ");

    // these function pointers may change
    var matches = function(elem) { elem.show() }
    var noMatch = function(elem) { elem.hide(); new_hidden = true }
    var getText = function(elem) { return elem.text() }

    if( column ) {
      var index = null;
      this.find("thead > tr:last > th").each( function(i){
        if( $.trim($(this).text()) == column ){
          index = i; return false;
        }
      });
      if( index == null ) throw("given column: " + column + " not found")

      getText = function(elem){ return $(elem.find(
        ("td:eq(" + index + ")")  )).text()
      }
    }

    // if added one letter to last time,
    // just check newest word and only need to hide
    if( (words.size > 1) && (phrase.substr(0, phrase_length - 1) ===
          this.last_phrase) ) {

      if( phrase[-1] === " " )
      { this.last_phrase = phrase; return false; }

      var words = words[-1]; // just search for the newest word

      // only hide visible rows
      matches = function(elem) {;}
      var elems = this.find("tbody:first > tr:visible")
    }
    else {
      new_hidden = true;
      var elems = this.find("tbody:first > tr")
    }

    elems.each(function(){
      var elem = $(this);
      $.fn.uiTableFilter.has_words( getText(elem), words, false, showAny ) ?
        matches(elem) : noMatch(elem);
    });

    last_phrase = phrase;
    if( ifHidden && new_hidden ) ifHidden();
    return this;
  };

  // caching for speedup
  $.fn.uiTableFilter.last_phrase = ""

  // not jQuery dependent
  // "" [""] -> Boolean
  // "" [""] Boolean -> Boolean
  $.fn.uiTableFilter.has_words = function( str, words, caseSensitive, showAny )
  {
	var showAll = showAny?true:false;
	var foundAny = false;
    var text = caseSensitive ? str : str.toLowerCase();
	if (words.length == 1 && words[0]=="") return true;
    for (var i=0; i < words.length; i++) {
		if (words[i] == "") continue;
      	if (text.indexOf(words[i]) === -1) {
			if (!showAll) { 
				return false; 
			} 
		} else {
			if (!showAll) {
				continue;
			} else {
				foundAny = true;
			}
		}
    }
	if (!showAll) {
		return true;
	} else {
		return foundAny;
	}
  }
}) (jQuery);
