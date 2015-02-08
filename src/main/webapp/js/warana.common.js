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
				timeout : 10000000,
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

WARANA.displayLoadingModel = function(){
    if (jQuery('body').find('#resultLoading').attr('id') != 'resultLoading') {
        jQuery('body').append('<div id="resultLoading" style="display:none"><div><img src="/warana/images/ajax-loader.gif"></div><div class="bg"></div></div>');
    }

    jQuery('#resultLoading').css({
        'width': '100%',
        'height': '100%',
        'position': 'fixed',
        'z-index': '10000000',
        'top': '0',
        'left': '0',
        'right': '0',
        'bottom': '0',
        'margin': 'auto'
    });

    jQuery('#resultLoading .bg').css({
        'background': '#000000',
        'opacity': '0.7',
        'width': '100%',
        'height': '100%',
        'position': 'absolute',
        'top': '0'
    });

    jQuery('#resultLoading>div:first').css({
        'width': '250px',
        'height': '75px',
        'text-align': 'center',
        'position': 'fixed',
        'top': '0',
        'left': '0',
        'right': '0',
        'bottom': '0',
        'margin': 'auto',
        'font-size': '16px',
        'z-index': '10',
        'color': '#ffffff'

    });

    jQuery('#resultLoading .bg').height('100%');
    jQuery('#resultLoading').fadeIn(300);
    jQuery('body').css('cursor', 'wait');
};

WARANA.hideLoadingModel= function(){
    jQuery('#resultLoading .bg').height('100%');
    jQuery('#resultLoading').fadeOut(300);
    jQuery('body').css('cursor', 'default');
};
$.fn.extend({
    enabled : function() {
        return this.each(function() {
            $(this).prop('disabled', false);
        });
    },
    disabled : function() {
        return this.each(function() {
            $(this).prop('disabled', true);
        });
    },
});

