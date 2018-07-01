'use strict';
angular.module('app.core').service('Statement', function($http, Upload) {
	
	this.upload = function (data) {
		return Upload.upload(data)
    };

});