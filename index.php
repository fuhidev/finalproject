<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/favicon.ico">
    <title>Gold-Dollar Chart</title>
    <!-- Bootstrap core CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/styles.css" rel="stylesheet">
  </head>
  <body>
    <nav class="navbar navbar-inverse">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#"><img src="http://assets.thefreelogomakers.com/generators/free-logo-maker/images/tflm.co.OBSVFJISY.png        
                 " class="img-responsive logo"></a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#about">About</a></li>
            <li><a href="#contact">Contact</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    <div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			   <div id="line_top_x"></div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3 col-md-offset-1">
				<div class="form-group">
					<label for="input" class="col-sm-2 control-label">From:</label>
					<div class="col-sm-10">
						<input type="date" name="" id="fromDate" class="form-control" value="" required="required" title="">
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
				<div class="form-group">
					<label for="input" class="col-sm-2 control-label">To:</label>
					<div class="col-sm-10">
						<input type="date" name="" id="toDate" class="form-control" value="" required="required" title="">
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
				<div class="btn-group">
					<button type="button" name="period" class="btn btn-default" data-period='1w'>1w</button>
					<button type="button" name="period" class="btn btn-default" data-period='3m'>3m</button>
					<button type="button" name="period" class="btn btn-default" data-period='6m'>6m</button>
					<button type="button" name="period" class="btn btn-default" data-period='1y'>1y</button>
					<button type="button" name="period" class="btn btn-default" data-period='3y'>3y</button>
					<button type="button" name="period" class="btn btn-default" data-period='10y'>10y</button>
					<button type="button" name="period" class="btn btn-default" data-period='max'>MAX</button>
				</div>
			</div>
			<div class="col-xs-12 col-sm-1 col-md-1 col-lg-1">
				<button type="button" class="btn btn-success" id="download">Download</button>
			</div>
		</div>
    </div><!-- /.container -->
	<footer class="footer">
      <div class="container">
        <p class="text-muted text-center text-info">This website served for display gold and dollar chart</p>
      </div>
    </footer>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="http://getbootstrap.com/assets/js/ie10-viewport-bug-workaround.js"></script>
    <script src="js/download.js"></script>
    <script type="text/javascript" src="js/loadingoverlay.min.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript" src="js/chart/chart.js"></script>
    
  </body>
</html>
