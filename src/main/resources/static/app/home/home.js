'use strict';
angular.module('app.core').controller('homeController', function($scope, Statement) {
	
	/*
	 * File upload handler
	 * This function will be executed after user select the file from computer
	 */
	$scope.uploadHandler = function($files) {
		if($files && $files.length > 0) {
			var data = {
				url: 'upload',
				method: 'POST',
				file: $files,
				arrayKey: ''
			};
			
			Statement.upload(data)
			.then(function(result) {
				$scope.statements = result.data.allStatements;
				$scope.issues = result.data.issues;
				$scope.lastIssues = result.data.issues
			}).catch(function(err) {
				// Handle error here
			});
		}
	}
	
	$scope.onSearch = function(search) {
		var _lastIssues = angular.copy($scope.lastIssues);
		if(search) {
			$scope.issues = []
			for(var i = 0; i < _lastIssues.length; i++) {
				var issue = _lastIssues[i];
				if(issue.count.toString() === search) {
					$scope.issues.push(issue);
				}
			}
		} else {
			$scope.issues = _lastIssues
		}
	}
	
});