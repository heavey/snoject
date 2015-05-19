app = angular.module 'RoutePlanner', ['ngResource', 'ngProgress', 'ngAnimate']
app.base = "/assets/templates/"
app.api = "/api/"


# Directives
app.directive "application", () ->

  directive = {}
  directive.restrict = "E"
  directive.templateUrl = app.base.concat "application.html"
  directive.controller = "ApplicationController"
  return directive


# Factories
app.factory 'Nodes', ['$resource', ($resource) ->
  return $resource app.api.concat "nodes/:query"
]

app.factory 'Routes', ['$resource', ($resource) ->
  return $resource app.api.concat "route/:from/:to"
]


# Controllers
app.controller 'ApplicationController', ['$scope', ($scope) ->
	console.log 'appcontroller started'
]

app.controller 'HeaderController', ['$scope', '$window', ($scope, $window) ->

  $scope.site = $window.site

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

app.controller 'PageController', ['$scope', '$routeParams', '$sce', 'ngProgress', 'Page', '$window', ($scope, $routeParams, $sce, ngProgress, Page, $window) ->

  ngProgress.height('4px')
  ngProgress.color($window.site.color)
  ngProgress.start()

  Page.get {slug: $routeParams.slug}, (data) ->
    $scope.page = data.page[0]
    $scope.content = []

    for d, i in data.content
      $scope.content[i] = []
      $scope.content[i]["content"] = $sce.trustAsHtml data.content[i]["content"]
      $scope.content[i]["image"] = data.content[i]["image"]

    $scope.excerpt = $sce.trustAsHtml data.page[0]["excerpt"]
    ngProgress.complete()

  , (error) ->
    console.log(error)
    ngProgress.color('#d30000')
    ngProgress.set(99)
    $scope.page = {"id": 0, "title": "404"}
    $scope.content = $sce.trustAsHtml "<h3>The requested page could not be found.</h3>"

  return
]