<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>

<head>
    <title>Chương trình bảo vệ tài sản tránh lạm phát - HÙYNH NGỌC TIỄN, HÒ PHƯƠNG HIẾU - ĐẠI HỌC TÔN ĐỨC THẮNG</title>
     <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,500,600,700,800&amp;subset=vietnamese' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <style>
    html,body,#chartdiv{
        width: 100%;
        height: 65vmin;
    }
    .margin-for-top{
        margin: 20px 0px;
    }
    *{
        font-family: 'Open Sans', sans-serif;
    }
    .unstyled{
        list-style-type: none; 
        
    }
    .unstyled li{
        margin-bottom: 10px;
    }
    footer{
        background: #555;
        color: #fff;
        padding: 30px 0px 20px 0px;
        margin-bottom: -10px;
    }
    </style>
</head>

<body>
<div class="container margin-for-top" >
    <div class="row">
        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
            <img src="/images/tdt.png" class="img-responsive" alt="Image">
        </div>
        <div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
            <h3 class="text-center">TỔNG LIÊN ĐOÀN LAO ĐỘNG VIỆT NAM</h3>
            <h3 class="text-center">TRƯỜNG ĐẠI HỌC TÔN ĐỨC THẮNG</h3>
            <h3 class="text-center">KHOA CÔNG NGHỆ THÔNG TIN</h3>
        </div>
        <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
         <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <div id="chartdiv"></div>
            <div id="recommend">
                <div class="bs-callout bs-callout-info">
                    <h1>Khuyến cáo đầu tư</h1>
                    <ul>
                        <li>Đầu tư vào vàng: <b id="pfl_gold">0%</b></li>
                        <li>Đầu tư vào dollar: <b id="pfl_dollar">0%</b></li>
                        <li>Gửi lãi suất tiết kiệm ngân hàng: <b id="pfl_bank">0%</b></li>
                    </ul>
                    <p>Dữ liệu được phân tích từ <i id="pfl_from"></i> đến <i id="pfl_to"></i></p>
                </div>
            </div>
    </div>
</div>
   
</div>
<footer >
    <div class="container margin-for-top">
        <div class="row">
            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                <ul class="unstyled">
                    <li><h4 style="margin: auto;"><strong>Chương trình hỗ trợ phương án bảo vệ tài sản khỏi lạm phát</strong></h4></li>
                    <li>Hội đồng phản biện bao gồm:</li>
                    <li><strong >TS. MAI NGỌC THẮNG</strong></li>
                    <li><strong >Th.S VÕ ĐỨC VĨNH</strong></li>
                    <li><strong>Th.S DƯƠNG HỮU PHÚC</strong></li>
                </ul>
            </div>
            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                <ul class="unstyled">
                    <li>Người hướng dẫn: <strong>TS. NGUYỄN CHÍ THIỆN</strong></li>
                    <li>Người thực hiện: <strong>HUỲNH NGỌC TIỂN - 51303415</strong></li>
                    <li><strong>HỒ PHƯƠNG HIẾU - 51303415</strong></li>
                    <li>Lớp: <strong>13050303</strong></li>
                    <li>Khóa: <strong>17</strong></li>
                </ul>
            </div>
        </div>
    </div>
</footer>
    <!-- Latest compiled and minified CSS & JS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <script src="//code.jquery.com/jquery.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    <script src="js/amcharts/amcharts.js" type="text/javascript"></script>
    <script src="js/amcharts/serial.js" type="text/javascript"></script>
    <script src="js/chart.js"></script>
    <script src="js/porfolio.js"></script>
</body>

</html>