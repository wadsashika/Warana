(function($) {

	/*Depends on underscore.js and moment.js*/
	$.fn.validation = function(options) {

		var $this, defaults, options;

		// assign element to local variable
		$this = $(this);

		// default options
		defaults = {
			validated : null,
			customValidations : null,
			selectFieldDefault : "",
			isOnchange : false,
			messages : {
				required : "{ele} is required.",
				remote : "Please fix this field.",
				email : "Invalid value entered for {ele} only email address is accepted",
				url : "Invalid value entered for {ele} only URL is accepted",
				date : "Invalid value entered for {ele} only date is accepted",
                dateMonth : "Invalid value entered for {ele} only month and year is accepted",
				dateISO : "Invalid value entered for {ele} only date is accepted",
				dateTimeISO : "Invalid value entered for {ele} only date and time are accepted",
				dateAfter : "Please enter date greater than {beforeDateLabel} for {ele}.",
				number : "Invalid value entered for {ele} only numbers are accepted",
				positiveNumber : "Invalid value entered for {ele} only positive number is accepted",
				digits : "Invalid value entered for {ele} only digits are accepted",
				creditcard : "Invalid value entered for {ele} only credit card is accepted",
				equalTo : "{ele} does not match with {label}.",
				maxlength : "Please enter less than {max} characters for {ele}.",
				length : "Please enter exactly {length} characters for {ele}.",
				minlength : "Please enter at least {min} characters for {ele}.",
				rangelength : "Please enter a value between {min} and {max} characters long for {ele}.",
				range : "Please enter a value between {min} and {max} for {ele}.",
				max : "Please enter a value less than or equal to {max} for {ele}.",
				min : "Please enter a value greater than or equal to {min} for {ele}.",
				alpha : "Invalid value entered for {ele} only characters are accepted",
				alphaNumeric : "Invalid value entered for {ele} only characters and numbers are accepted",
				alphaWithSpace : "Invalid value entered for {ele} only characters are accepted",
				alphaNumericWithSpace : "Invalid value entered for {ele} only characters and numbers are accepted",
				spChrNotAllow : "Special characters not allowed for {ele}",
				spChrAllow : "Some characters not allowed for {ele} only allow {pattern}",
				spChrAllowWithoutSpace : "Some characters not allowed for {ele} only allow {pattern}",
				telephone : "Invalid value entered for {ele} only phone number is accepted",
				amount : "Invalid value entered for {ele} only amount[{digits}, {decimals}] is accepted",
				requiredBefore : "{reqLabel} is required.",
				today : "Date Cannot be less than today for {ele}",
				notEqualTo : "{ele} can not be the same as {label}",
				lessThanOrEqualTo : "{ele} can not be the greater than {label}",
				greaterThanOrEqualTo : "{ele} can not be the less than {label}",
				format : "Invalid format for the value entered for {ele} The required format is {format}"

			}
		};

		options = $.extend(defaults, options);

		var _elementValue = function(element) {
			var type = element.type, val = $(element).val();

			if (type === "radio" || type === "checkbox") {
				return $("input[name='" + $(element).attr("name") + "']:checked").val();
			}
			if (type === "select-one") {
				if (options.selectFieldDefault === val) {
					return "";
				}

			}

			if (typeof val === "string") {
				return val.replace(/\r/g, "");
			}
			return val;
		};

		var _format = function(source, params) {
			if (arguments.length === 1) {
				return source;
			}

			_.each(params, function(value, key, list) {
				source = source.replace(new RegExp("\\{" + key + "\\}", "g"), function() {
					return value;
				});
			});

			return source;
		};

		var _methods = {

			required : function(value, element, param) {
				if (!_.isEmpty(value)) {
					if (typeof value === 'string') {
						return !_.isEmpty(value.trim());
					}
					return true;
				}
			},

			email : function(value, element) {
				if (!_.isEmpty(value)) {
					return /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i
						.test(value);
				} else {
					return true;
				}
			},

			url : function(value, element) {
				if (!_.isEmpty(value)) {
					return /^(https?|s?ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i
						.test(value);
				} else {
					return true;
				}
			},

			date : function(value, element) {
				if (!_.isEmpty(value)) {
					return !/Invalid|NaN/.test(new Date(value).toString());
				} else {
					return true;
				}
			},

            dateMonth : function(value, element) {
                if (!_.isEmpty(value)) {
                    return moment(value, "MM/YYYY").isValid();
                } else {
                    return true;
                }
            },

			dateISO : function(value, element) {
				if (!_.isEmpty(value)) {
					return moment(value, "DD/MM/YYYY").isValid();
				} else {
					return true;
				}
			},

			dateTimeISO : function(value, element) {
				if (!_.isEmpty(value)) {
					var filter = /^\d{2}[/]\d{2}[/]\d{4}\s*?\d{2}[:]\d{2}$/;
					return filter.test(value);
				} else {
					return true;
				}
			},

			dateAfter : function(value, element, param) {
				if (!_.isEmpty(value)) {
					var dateFromArr = param.dateBefore.split("/");
					var dateToArr = value.split("/");
					// JS Date month is 0-11
					var dateFrom = new Date(dateFromArr[2], dateFromArr[1] - 1, dateFromArr[0]);
					var dateTo = new Date(dateToArr[2], dateToArr[1] - 1, dateToArr[0]);
					return !(dateTo < dateFrom);
				} else {
					return true;
				}
			},

			today : function(value, element, param) {
				if (!_.isEmpty(value)) {
					var dateToArr = value.split("/");
					// JS Date month is 0-11
					var dateEntered = new Date(dateToArr[2], dateToArr[1] - 1, dateToArr[0]);
					var today = moment();
					return !(today < dateEntered);
				} else {
					return true;
				}
			},

			telephone : function(value, element) {
				if (!_.isEmpty(value)) {
					var filter = /^[0-9]+$/;
					return filter.test(value);
				} else {
					return true;
				}
			},

			number : function(value, element) {
				if (!_.isEmpty(value)) {
					return /^-?(?:\d+|\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test(value);
				} else {
					return true;
				}
			},

			positiveNumber : function(value, element) {
				if (!_.isEmpty(value)) {
					return /^(?:\d+|\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test(value);
				} else {
					return true;
				}
			},

			digits : function(value, element) {
				if (!_.isEmpty(value)) {
					return /^\d+$/.test(value);
				} else {
					return true;
				}
			},

			length : function(value, element, param) {
				if (!_.isEmpty(value)) {
					var length = value.length;
					return length == param.length;
				} else {
					return true;
				}
			},

			minlength : function(value, element, param) {
				if (!_.isEmpty(value)) {
					var length = value.length;
					return length >= param.min;
				} else {
					return true;
				}
			},

			maxlength : function(value, element, param) {
				if (!_.isEmpty(value)) {
					var length = value.length;
					return length <= param.max;
				} else {
					return true;
				}
			},

			rangelength : function(value, element, param) {
				if (!_.isEmpty(value)) {
					var length = value.length;
					return (length >= param.min && length <= param.max);
				} else {
					return true;
				}
			},

			min : function(value, element, param) {
				if (!_.isEmpty(value)) {
					return value >= param.min;
				} else {
					return true;
				}
			},

			max : function(value, element, param) {
				if (!_.isEmpty(value)) {
					return value <= param.max;
				} else {
					return true;
				}
			},

			equalTo : function(value, element, param) {
				return (param.value == value);
			},

			notEqualTo : function(value, element, param) {
				if (!_.isEmpty(value)) {
					return(param.value != value);
				} else {
					return true;
				}
			},

			lessThanOrEqualTo : function(value, element, param) {
				if (!_.isEmpty(value)) {
					return (parseInt(param.value, 10) >= parseInt(value, 10));
				} else {
					return true;
				}
			},

			greaterThanOrEqualTo : function(value, element, param) {
				if (!_.isEmpty(value)) {
					return (parseInt(param.value, 10) <= parseInt(value, 10));
				} else {
					return true;
				}
			},

			range : function(value, element, param) {
				if (!_.isEmpty(value)) {
					return parseInt(value, 10) >= parseInt(param.min, 10) && parseInt(value, 10) <= parseInt(param.max, 10);
				} else {
					return true;
				}
			},

			alpha : function(value, element, param) {
				if (value.match(/[^A-Za-z]+/) != null) {
					return value.match(/[^A-Za-z]+/).length == 0;
				} else {
					return true;
				}
			},

			alphaNumeric : function(value, element, param) {
				if (value.match(/[^A-Za-z0-9]+/) != null) {
					return value.match(/[^A-Za-z0-9]+/).length == 0;
				} else {
					return true;
				}
			},

			alphaWithSpace : function(value, element, param) {
				if (value.match(/[^A-Za-z\s]+/) != null) {
					return value.match(/[^A-Za-z\s]+/).length == 0;
				} else {
					return true;
				}
			},

			alphaNumericWithSpace : function(value, element, param) {
				if (value.match(/[^A-Za-z0-9\s]+/) != null) {
					return value.match(/[^A-Za-z0-9\s]+/).length == 0;
				} else {
					return true;
				}
			},

			amount : function(value, element, param) {
				var noOfDigits = function(num, place) {
					return (num.split('.')[place] || []).length;
				};
				if (!_.isEmpty(value)) {
					var filter = /\d+(\.\d{1,4})?/;
					var regExPass = !isNaN(value) && filter.test(value);
					if (regExPass && !param.onkey) {
						return (noOfDigits(value, 0) <= param.digits && noOfDigits(value, 1) <= param.decimals)
					}
					return  regExPass;
				} else {
					return true;
				}
			},

			spChrNotAllow : function(str, element, param) {
				var iChars = "~`!#$%^&*+=-[]\\\';,/{}|\":<>?";
				for (var i = 0; i < str.length; i++) {
					if (iChars.indexOf(str.charAt(i)) != -1) {
						return false;
					}
				}
				return true;
			},

			spChrAllow : function(str, element, param) {
				var regExp = /[A-Za-z0-9\s]+/;
				if (!_.isEmpty(str)) {
					var allowedChars = param.pattern.split("");
					$.each(allowedChars, function(i, value) {
						str = str.split(value).join('');
					});
					if (str.match(regExp) != null) {
						if (str.match(regExp).length == 1) {
							return str.match(regExp)[0] == str;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else {
					return false;
				}
			},

			spChrAllowWithoutSpace : function(str, element, param) {
				var regExp = /[A-Za-z0-9]+/;
				if (!_.isEmpty(str)) {
					var allowedChars = param.pattern.split("");
					$.each(allowedChars, function(i, value) {
						str = str.split(value).join('');
					});
					if (str.match(regExp) != null) {
						if (str.match(regExp).length == 1) {
							return str.match(regExp)[0] == str;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else {
					return false;
				}
			},

			requiredBefore : function(value, element, param) {
				if (!_.isEmpty(value)) {
					return (!_.isEmpty($("#" + param.reqEle).val()));
				} else {
					return true;
				}
			},

			format : function(str, element, param) {
				if (!_.isEmpty(str)) {
					if (/[a-zA-Z]/.test(str)) {
						return false;
					} else {
						return moment(str, param.format).isValid();
					}
				} else {
					return false;
				}
			}
		};

		var _params = {

			required : function(element) {
				return {};
			},

			email : function(element) {
				return {};
			},

			url : function(element) {
				return {};
			},

			date : function(element) {
				return {};
			},

			dateISO : function(element) {
				return {};
			},

            dateMonth : function(element) {
                return {};
            },

			today : function(element) {
				return {};
			},

			dateTimeISO : function(element) {
				return {};
			},

			dateAfter : function(element) {
				return {
					dateBefore : $("#" + element.attr("data-dateBefore")).val(),
					beforeDateLabel : _getLabel($("#" + element.attr("data-dateBefore")))
				};
			},
			telephone : function(element) {
				return {};
			},

			number : function(element) {
				return {};
			},

			digits : function(element) {
				return {};
			},
			positiveNumber : function(element) {
				return {};
			},

			length : function(element) {
				return {
					length : element.attr("data-length")
				};
			},

			minlength : function(element) {
				return {
					min : element.attr("data-min")
				};
			},

			maxlength : function(element) {
				return {
					max : element.attr("data-max")
				};
			},
			equalTo : function(element) {
				var otherEle = $("#" + element.attr("data-ele"));
				return {
					value : otherEle.val(),
					label : _getLabel(otherEle)
				};
			},

			notEqualTo : function(element) {
				var otherEle = $("#" + element.attr("data-ele"));
				return{
					value : otherEle.val(),
					label : _getLabel(otherEle)
				};

			},

			lessThanOrEqualTo : function(element) {
				var otherEle = $("#" + element.attr("data-ele"));
				return{
					value : otherEle.val(),
					label : _getLabel(otherEle)
				};

			},
			greaterThanOrEqualTo : function(element) {
				var otherEle = $("#" + element.attr("data-ele"));
				return{
					value : otherEle.val(),
					label : _getLabel(otherEle)
				};

			},

			rangelength : function(element) {
				return {
					min : element.attr("data-min"),
					max : element.attr("data-max")
				};
			},

			min : function(element) {
				return {
					min : element.attr("data-min")
				};
			},

			max : function(element) {
				return {
					max : element.attr("data-max")
				};
			},

			range : function(element) {
				return {
					min : element.attr("data-min"),
					max : element.attr("data-max")
				};
			},
			alpha : function(element) {
				return {};
			},
			alphaNumeric : function(element) {
				return {};
			},
			alphaWithSpace : function(element) {
				return {};
			},
			alphaNumericWithSpace : function(element) {
				return {};
			},
			amount : function(element) {
				return {
					digits : element.attr("data-digits") || 20,
					decimals : element.attr("data-decimals") || 5
				};
			},
			spChrNotAllow : function(element) {
				return {};
			},
			spChrAllow : function(element) {
				return {
					pattern : element.attr("data-pattern")
				};
			},
			spChrAllowWithoutSpace : function(element) {
				return {
					pattern : element.attr("data-pattern")
				};
			},
			requiredBefore : function(element) {
				return {
					reqEle : element.attr("data-before"),
					errorElement : $("#" + element.attr("data-before")),
					reqLabel : _getLabel($("#" + element.attr("data-before")))
				};
			},
			format : function(element) {
				return {
					format : element.attr("data-format")
				};
			}
		};

		var _getValidation = function(ele) {
			var validation = [];
			if (!_.isUndefined(ele.attr('data-valid'))) {
				validation = ele.attr('data-valid').split(' ');
			}
			return validation;
		};
		var _getOnKeyValidation = function(ele) {
			var validation = [];
			if (!_.isUndefined(ele.attr('data-onkey-valid'))) {
				validation = ele.attr('data-onkey-valid').split(' ');
			}
			return validation;
		};
		var _checkForErrors = function(validation, ele, value) {
			var error = false, i;

			for (i = 0; i < validation.length; i++) {
				var type = validation[i];
				var p = _params[type](ele);
				p.ele = _getLabel(ele);
				p.onkey = false;
				var errorElement = (p.errorElement) ? p.errorElement : ele;
				var result = _methods[type](value, ele, p);
				if (!result) {
					throw new ValidationException(errorElement, options.messages[type], p);
				} else {
					errorElement.removeClass("errorElement");
				}
			}
			return error;
		};

		var _checkForOnKeyErrors = function(validation, ele, value) {
			var error = false, i;

			for (i = 0; i < validation.length; i++) {
				var type = validation[i];
				var p = _params[type](ele);
				p.ele = _getLabel(ele);
				p.onkey = true;
				var errorElement = (p.errorElement) ? p.errorElement : ele;
				var result = _methods[type](value, ele, p);
				if (!result) {
					throw new ValidationException(errorElement, options.messages[type], p);
				} else {
					errorElement.removeClass("errorElement");
				}

			}
			return error;
		};

		var _getLabel = function(ele) {
			var label = "";
			if ($("label[for='" + ele.attr('id') + "']").length > 0) {
				label = $("label[for='" + ele.attr('id') + "']").text();
			} else if (ele.attr("data-label")) {
				label = ele.attr("data-label");
			}
			return label;
		};

		var _searchForErrors = function() {
			var errors = false;
			$this.find(':input').not(':disabled').each(function() {
				var ele, value, validation = [], validity, p;
				validity = true;
				ele = $(this);
				value = _elementValue(this);
				validation = _getValidation(ele);
				if (validation.length > 0) {
					_checkForErrors(validation, ele, value);
				}
			});

			return errors;
		};
		var _bindElements = function() {
			//	var errors = false;
			$this.find(':input').each(function() {
				var ele, value, validation = [], validity, p;
				validity = true;
				var skippedKeys = [0, 8, 9];
				ele = $(this);
				ele.keypress(function(event) {
					validation = _getOnKeyValidation(ele);
					if (validation.length > 0) {
						try {
							var chars = event.which;
							if (skippedKeys.indexOf(chars) == -1) {
								value = _elementValue(this) + String.fromCharCode(chars);
								_checkForOnKeyErrors(validation, ele, value);
							}

						} catch (ex) {
							event.preventDefault();
							if (ex instanceof ValidationException) {
								if (ex.getElement()) {
									if (ex.getElement().data("echSimpleMultiSelect")) {
										ex.getElement().simpleMultiSelect('error');
									}
									ex.getElement().addClass("form-error").delay(5000).queue(function(){
										$(this).removeClass('orm-error').dequeue();
									});
									ex.getElement().addClass("errorElement").delay(5000).queue(function() {
										$(this).removeClass('errorElement').dequeue();
									});
								}
							}
							return false;
						}

					}
				});
			});

		};
		return initialize();

		function initialize() {

			if (options.isOnchange) {
				_bindElements();
			}
			else {
				try {
					var errors = _searchForErrors();
					if (!errors) {
						if (_.isFunction(options.customValidations)) {
							errors = options.customValidations.call();
						}

						if (!errors && _.isFunction(options.validated)) {
							options.validated.call();
						}
					}

					return true;
				} catch (ex) {
					if (ex instanceof ValidationException) {

						WARANA.showError(_format(ex.getErrorMessage(), ex.getParameters()));
						if (ex.getElement()) {
							if (ex.getElement().data("echSimpleMultiSelect")) {
								ex.getElement().simpleMultiSelect('error');
							}
							ex.getElement().addClass("form-error").delay(5000).queue(function(){
								$(this).removeClass('form-error').dequeue();
							});
							ex.getElement().addClass("errorElement").delay(5000).queue(function() {
								$(this).removeClass('errorElement').dequeue();
							});
						}
					}
					return false;
				}
			}
		}

	}
}(jQuery));