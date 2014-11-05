// Create the namespace

var WARANA = WARANA || {};
WARANA.module = WARANA.module || {};

WARANA.messageType = {
	"NOTICE" : "notice",
	"INFO" : "info",
	"SUCCESS" : "success",
	"ERROR" : "error"
};

WARANA.namespace = function() {
	var a = arguments, o = null, i, j, d;
	for (i = 0; i < a.length; i = i + 1) {
		d = a[i].split(".");
		o = window.WARANA;
		for (j = 0; j < d.length; j = j + 1) {
			o[d[j]] = o[d[j]] || {};
			o = o[d[j]];
		}
	}
	return o;
};

WARANA.generateMap = function(dataObj, keyForText, keyForValue, keyForAdditionalDesc, filterFn) {
	if (_.isFunction(filterFn)) {
		dataObj = _.filter(dataObj, filterFn)
	}
	if (dataObj != null) {
		var obj = {};
		$.each(dataObj, function(k, value) {
			k = (keyForValue !== undefined) ? value[keyForValue] : k;
			obj[k] = (keyForText) ? ((keyForAdditionalDesc) ? (value[keyForText] + " - " + value[keyForAdditionalDesc])
				: value[keyForText]) : value;
		});
		return obj;
	} else {
		return {};
	}
};

WARANA.generateMapFromList = function(list, keyForValue, keyForText, keyForAdditionalDesc, filterFn) {
	if (_.isFunction(filterFn)) {
		list = _.filter(list, filterFn)
	}
	var obj = {};
	if (list.length > 0) {
		for (var i = 0; i < list.length; i++) {
			obj[list[i][keyForValue]] = list[i][keyForText]
				+ ((keyForAdditionalDesc) ? " - " + list[i][keyForAdditionalDesc] : "")
		}
		return obj;
	} else {
		return {};
	}
};

/*
 Create a Value string with the giving id string and value Object

 @idString = "1,2,3";
 @ValuesObj = {1 : "one" , 2 : "two", 3 : "three"}

 <= result "one, two, three";
 */
WARANA.mapIdsToValues = function(idString, ValuesObj) {
	return idString.split(",").map(function(d) {
		return ValuesObj[d];
	}).join(", ");
}

WARANA.generateListFromMap = function(map) {
	var list = [];
	$.each(map, function(k, value) {
		list.push(value);
	});
	return list;
};


WARANA.supports_html5_storage = function() {
	try {
		return 'localStorage' in window && window['localStorage'] !== null;
	} catch (e) {
		return false;
	}
};

WARANA.common = function() {
	return {
		ajaxCall : function(customOptions, doneFn, failFn) {
			var defaultOptions = {
				type : 'POST',
				async : true,
				cache : true,
				timeout : 120000,
				url : '',
				data : {},
				dataType : 'json'
			}, option, request;

			options = $.extend({}, defaultOptions, customOptions);

			request = $.ajax(options);
			if (failFn) {
				request.fail(failFn);
			} else {
				request.fail(function(jqXHR, textStatus, errorThrown) {
					console.log("Your Session is expired. Please re-login to the system");
				});
			}
			if (doneFn) {
				request.done(doneFn);
			}
			return request;
		},

		postCall : function(action, data, target) {
			var form;
			form = $('<form />', {
				action : action,
				method : "POST",
				style : 'display: none;',
				target : target
			});
			if (typeof data !== 'undefined') {
				$.each(data, function(name, value) {
					$('<input />', {
						type : 'hidden',
						name : name,
						value : value
					}).appendTo(form);
				});
			}
			form.appendTo('body').submit();
		}
	};
}();

WARANA.message = function(type, message, page) {
	parent.$.pnotify({
		text : message ? message : false,
		type : type
	});
};

WARANA.noRecordFound = function(element, moduleName) {
	var count = element.jqGrid('getGridParam', 'records');
	if (count == 0) {
		WARANA.message(WARANA.messageType.INFO, "No Records", moduleName);
	}
};

WARANA.showError = function(message) {
	WARANA.message(WARANA.messageType.ERROR, message, false);
};

WARANA.scrollToElement = function(ele) {
	$(window).scrollTop(ele.offset().top).scrollLeft(ele.offset().left);
};


/* Cancel confirmation alert */
WARANA.cancelConfirmation = function(successFn, text) {
	var key = 1;
	var msg = "Are you sure, you want to cancel this {1}?";
	var title = "Cancel {1}";
	text = text || "Record";
	msg = msg.replace(new RegExp("\\{" + key + "\\}", "g"), function() {
		return text;
	});
	title = title.replace(new RegExp("\\{" + key + "\\}", "g"), function() {
		return text;
	});

	BootstrapDialog.show({
		title : title,
		message : msg,
		cssClass : 'message-box',
		buttons : [
			{
				label : 'Yes',
				cssClass : 'btn-primary',
				action : function(dialogRef) {
					dialogRef.close();
					if (_.isFunction(successFn)) {
						successFn.call();
					}
				}
			},
			{
				label : 'No',
				cssClass : 'btn-primary',
				action : function(dialogRef) {
					dialogRef.close();
				}
			}
		]
	});
};

/* Delete confirmation alert */
WARANA.deleteConfirmation = function(successFn, text) {
	var key = 1;
	var msg = "Are you sure, you want to Delete this {1}?";
	var title = "Delete {1}";
	text = text || "Record";
	msg = msg.replace(new RegExp("\\{" + key + "\\}", "g"), function() {
		return text;
	});
	title = title.replace(new RegExp("\\{" + key + "\\}", "g"), function() {
		return text;
	});

	BootstrapDialog.show({
		title : title,
		message : msg,
		cssClass : 'message-box',
		buttons : [
			{
				label : 'Yes',
				cssClass : 'btn-primary',
				action : function(dialogRef) {
					dialogRef.close();
					if (_.isFunction(successFn)) {
						successFn.call();
					}
				}
			},
			{
				label : 'No',
				cssClass : 'btn-primary',
				action : function(dialogRef) {
					dialogRef.close();
				}
			}
		]
	});
};

/* Copy confirmation alert */
WARANA.copyConfirmation = function(successFn, cancelFn, text, closeFn) {
	if (parent && parent.WARANA && parent.WARANA.module && parent.WARANA.module.homepage) {
		var element = parent.WARANA.module.homepage.getConfirmMessageBox();
		var messageDiv = $("<div></div>");
		element.empty().append(messageDiv);
		var key = 1;
		var msg = "Are you sure, you want to copy this {1}?";
		var title = "Copy {1}";
		text = text || "Record";
		msg = msg.replace(new RegExp("\\{" + key + "\\}", "g"), function() {
			return text;
		});
		title = title.replace(new RegExp("\\{" + key + "\\}", "g"), function() {
			return text;
		});
		messageDiv.attr('title', title);
		messageDiv.append($('<p><label>' + msg + '</label></p>'));
		messageDiv.dialog({
			height : 140,
			title : title,
			modal : true,
			close : function() {
				if (_.isFunction(closeFn)) {
					closeFn.call();
				}
			},
			buttons : {
				Yes : function() {
					messageDiv.dialog('close');
					if (_.isFunction(successFn)) {
						successFn.call();
					}
				},
				No : function() {
					messageDiv.dialog('close');
					if (_.isFunction(cancelFn)) {
						cancelFn.call();
					}
				}
			},
			draggable : false,
			resizable : false
		});
	}
};

/* page leave confirmation dialog */
WARANA.leaveConfirmation = function(successFn) {
	var msg = "Your changes might be discarded. Are you sure you want to leave this page?";
	var title = "Leave this page";

	BootstrapDialog.show({
		title : title,
		message : msg,
		cssClass : 'message-box',
		buttons : [
			{
				label : 'Yes',
				cssClass : 'btn-primary',
				action : function(dialogRef) {
					dialogRef.close();
					if (_.isFunction(successFn)) {
						successFn.call();
					}
				}
			},
			{
				label : 'No',
				cssClass : 'btn-primary',
				action : function(dialogRef) {
					dialogRef.close();
				}
			}
		]
	});
};

/* validation error object */
var ValidationException = function(element, errorMsg, parameters) {
	var pElement = element;
	var pErrorMsg = errorMsg;
	var pParameters = parameters;

	this.getElement = function() {
		return pElement;
	};

	this.getErrorMessage = function() {
		return pErrorMsg;
	};

	this.getParameters = function() {
		if (pParameters == null) {
			pParameters = {};
		}
		return pParameters;
	};
};
/**
 * This function helps to autocomplete the date format MMDDYYY
 * Converts M to 0M and MMD to MM0D. Ex. `1/` to `01/`, `01/1/` to `01/01/`
 * Adds slash for MM and MMDD Ex. `01` to `01/`, `01/02` to `01/02/`
 * Converts YY to YYYY. Ex. `01/01/01` to `01/01/2001`
 *
 * @param {String} str
 * @return {String}
 */
var autocompleteMMDDYYYYDateFormat = function(str) {
	str = str.trim();
	var matches, year,
		looksLike_MM_slash_DD = /^(\d\d\/)?\d\d$/,
		looksLike_MM_slash_D_slash = /^(\d\d\/)?(\d\/)$/,
		looksLike_MM_slash_DD_slash_DD = /^(\d\d\/\d\d\/)(\d\d)$/;

	if (looksLike_MM_slash_DD.test(str)) {
		str += "/";
	} else if (looksLike_MM_slash_D_slash.test(str)) {
		str = str.replace(looksLike_MM_slash_D_slash, "$10$2");
	} else if (looksLike_MM_slash_DD_slash_DD.test(str)) {
		matches = str.match(looksLike_MM_slash_DD_slash_DD);
		year = Number(matches[2]) < 20 ? "20" : "19";
		str = String(matches[1]) + year + String(matches[2]);
	}
	return str;
};

ValidationException.prototype = new Error();

