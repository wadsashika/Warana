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

/* Success Message*/
WARANA.successConfirmMessage = function(successFn, title, msg) {

    BootstrapDialog.show({
        title : title,
        message : msg,
        cssClass : 'message-box',
        type : BootstrapDialog.TYPE_SUCCESS,
        buttons : [
            {
                label : 'Yes',
                icon: 'glyphicon glyphicon-ok',
                cssClass : 'btn-success',
                action : function(dialogRef) {
                    dialogRef.close();
                    if (_.isFunction(successFn)) {
                        successFn.call();
                    }
                }
            },
            {
                label : 'No',
                icon: 'glyphicon glyphicon-remove',
                cssClass : 'btn-success',
                action : function(dialogRef) {
                    dialogRef.close();
                }
            }
        ]
    });
};

WARANA.successMessage = function(msg) {

    BootstrapDialog.show({
        title : "Success",
        message : msg,
        cssClass : 'message-box',
        type : BootstrapDialog.TYPE_SUCCESS,
        buttons : [
            {
                label : 'Ok',
                icon: 'glyphicon glyphicon-ok',
                cssClass : 'btn-success',
                action : function(dialogRef) {
                    dialogRef.close();
                }
            }
        ]
    });
};

WARANA.successMessageWithCallBack = function(successFn,msg) {

    BootstrapDialog.show({
        title : "Success",
        message : msg,
        cssClass : 'message-box',
        type : BootstrapDialog.TYPE_SUCCESS,
        buttons : [
            {
                label : 'Ok',
                icon: 'glyphicon glyphicon-ok',
                cssClass : 'btn-success',
                action : function(dialogRef) {
                    dialogRef.close();
                    if (_.isFunction(successFn)) {
                        successFn.call();
                    }
                }
            }
        ]
    });
};

/* Delete confirmation alert */
WARANA.messageConfirmation = function(successFn,title, msg) {

	BootstrapDialog.show({
		title : title,
		message : msg,
		cssClass : 'message-box',
		buttons : [
			{
				label : 'Yes',
                icon: 'glyphicon glyphicon-ok',
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
                icon: 'glyphicon glyphicon-remove',
				cssClass : 'btn-primary',
				action : function(dialogRef) {
					dialogRef.close();
				}
			}
		]
	});
};

