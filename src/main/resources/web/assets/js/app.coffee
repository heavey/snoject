app = angular.module 'RoutePlanner', ['ngProgress', 'ngAnimate']


# Directives
app.directive "application", () ->

  directive = {}
  directive.restrict = "E"
  directive.templateUrl = "/assets/templates/application.html"
  directive.controller = "ApplicationController"
  return directive


# Controllers
app.controller 'ApplicationController', ['$scope', '$http', 'ngProgress', ($scope, $http, ngProgress) ->

	ngProgress.height('6px')
	ngProgress.color('#FFBB00')
	$scope.containerClass = "search"

	$scope.getLocationFrom = (val) ->
		$scope.listVisibleFrom = if val then true else false
		$scope.getLocation(val)

	$scope.getLocationTo = (val) ->
		$scope.listVisibleTo = if val then true else false
		$scope.getLocation(val)

	$scope.getLocation = (val) ->
		$http.get('/api/nodes/' + val).then((response) ->
			$scope.list = response.data
		)

	$scope.setFrom = (item) ->
		$scope.listVisibleFrom = false
		$scope.from = item.id
		$scope.fromStation = item.name

	$scope.setTo = (item) ->
		$scope.listVisibleTo = false
		$scope.to = item.id
		$scope.toStation = item.name

	$scope.performSearch = ->
		if not $scope.from? or not $scope.to?
			alert('Du måste specificera både start- och slutdestination.')
			return

		ngProgress.start()

		$http.get('/api/route/' + $scope.from + '/' + $scope.to).then((response) ->
			$scope.errorAvailable = false
			$scope.resultsAvailable = false

			if response.data.error?
				$scope.errorAvailable = true
				$scope.error = response.data.error
				$scope.containerClass = "error"

				switch response.data.error
					when "RouteNotFound" then $scope.errorMsg = "Ingen resväg hittades mellan de valda destinationerna."
					when "StationsNotInDataStore" then $scope.errorMsg = "Ogiltig start- eller slutdestination."
					when "MustChooseRoute" then $scope.errorMsg = "Start- och slutdestination måste vara skilda."
					else $scope.errorMsg = "Tekniskt fel, kontakta systemadministratören."

			if response.data.response?
				$scope.results = response.data
				$scope.resultsAvailable = true
				$scope.containerClass = "results"

			ngProgress.complete()
		)

	$scope.getCostClass = (cost) ->
		switch cost
			when -1 then return "text-success"
			when 0 then return "text-warning"
			when 1 then return "text-danger"

	# Default views
	$scope.showRouteType = 'TIME'
]