/*
 * 基础前台封装
 *
 */

var $$ = {};
$$.serializeObject = function(form) {
	var object = {};
	$.each(form.serializeArray(), function (index) {
		if (object[this['name']]) {
			object[this['name']] += "," + this['value'];
		} else {
			object[this['name']] = this['value'];
		}
	});
	return object;
}