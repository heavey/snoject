app = angular.module 'RoutePlanner', ['ngProgress', 'ngAnimate']
app.base = "/assets/templates/"
app.api = "/api/"


# Directives
app.directive "application", () ->

  directive = {}
  directive.restrict = "E"
  directive.templateUrl = app.base.concat "application.html"
  directive.controller = "ApplicationController"
  return directive


# Controllers
app.controller 'ApplicationController', ['$scope', ($scope) ->
	console.log 'appcontroller started'
]

app.controller 'SearchController', ['$scope', '$http', 'ngProgress', ($scope, $http, ngProgress) ->

	ngProgress.height('6px')
	ngProgress.color('#FFBB00')

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
			alert('lol')
			return

		ngProgress.start()

		$http.get('/api/route/' + $scope.from + '/' + $scope.to).then((response) ->
			console.log response
			$scope.results = response.data
			$scope.resultsAvailable = true
			ngProgress.complete()
		)
]

app.controller 'NavigationController', ['$scope', '$location', 'Menu', ($scope, $location, Menu) ->

  $scope.mobileNav = false

  Menu.get {}, (data) ->
    $scope.menu = data.menu

  $scope.isActive = (route) ->
    return route is $location.path()

  $scope.toggleMobileNav = ->
    $scope.mobileNav = !$scope.mobileNav

  $scope.hideMobileNav = ->
    $scope.mobileNav = false

  return
]