(function($) {

	$.fn.formSerilize = function(options) {

		var $this, defaults, options, data = {};

		// assign element to local variable
		$this = $(this);

		// default options
		defaults = {
			checkOption : [ 'Y', 'N' ]
		};

		options = $.extend(defaults, options);

		var namespace = function(name, baseNameSpace, val) {
			var a = name, o = null, i, j, d;
			d = a.split(".");
			o = baseNameSpace;
			for (j = 0; j < d.length; j = j + 1) {
				if (j === d.length - 1) {
					o[d[j]] = val;
				} else {
					o[d[j]] = o[d[j]] || {};
				}
				o = o[d[j]];
			}

			return o;
		};
		var _getTextBox = function(element, baseNameSpace) {
			var name, value;
			name = element.attr('name');
			value = element.val();
			namespace(name, baseNameSpace, value);
		};

		var _getRadioBox = function(element, baseNameSpace) {
			var ele, name, value, attrVal, checkOption;
			ele = element;
			attrVal = ele.attr('value');
			name = ele.attr('name');
			if (ele.is(':checked')) {
				namespace(name, baseNameSpace, attrVal);
			}
		};

		var _getCheckBox = function(element, baseNameSpace) {
			var ele, name, value, attrVal, checkOption;
			ele = element;
			attrVal = ele.attr('value');
			checkOption = (attrVal && !_.isEmpty(attrVal)) ? attrVal.split(",")
				: options.checkOption;
			name = ele.attr('name');
			value = ele.is(':checked') ? checkOption[0] : checkOption[1];
			namespace(name, baseNameSpace, value);
		};

		var _getSelectBox = function(element, baseNameSpace) {
			var ele, name, value;
			ele = element;
			name = ele.attr('name');
			value = ele.find("option:selected").val();
			namespace(name, baseNameSpace, value);
		};

		var _getMultiSelectBox = function(element, baseNameSpace) {
			var ele, name, value;
			ele = element;
			name = ele.attr('name');
			value = ele.val();
			namespace(name, baseNameSpace, value);
		};

		function serialize(baseNameSpace) {
			$this.find(':input').each(function() {
				if ($(this).attr('name') !== undefined) {
					if (this.type == 'text' || this.type == 'textarea' || this.type == 'hidden' || this.type == 'password' || this.type == 'email') {

						_getTextBox($(this), baseNameSpace);
					} else if (this.type == 'radio') {

						_getRadioBox($(this), baseNameSpace);
					} else if (this.type == 'checkbox') {

						_getCheckBox($(this), baseNameSpace);
					} else if (this.type == 'select-one') {

						_getSelectBox($(this), baseNameSpace);
					} else if (this.type == 'select-multiple') {
						_getMultiSelectBox($(this), baseNameSpace);
					}
				}
			});

			return baseNameSpace;
		}

		return initilize();

		function initilize() {
			return serialize(data)
		}

	}
}(jQuery));