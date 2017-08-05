function loadData(url = '/services.php/goldworld', table = 'goldworld') {
    return new Promise((resolve, reject) => {
        var datas;

        $.ajax({
            url: url,
            async: false,
            // beforeSend: function(){
            //      $('#loading').html("<img src='images/loading2.gif'id='loaded'/>").fadeIn();
            // },
            success: function (respone, status, xhr) {
                if (respone != undefined) {
                    datas = respone[table].records;

                }
            }
        });
        resolve(datas);
    });


}
var chart;
var graph, graph1;

var chartData = [];
let gold = loadData('/services.php/goldworld');
let dollar = loadData('/services.php/dollar', 'dollar');
Promise.all([gold, dollar]).then((records) => {
    let recordGolds = records[0],
        recordDollars = records[1];
    let firstDollarPrice = 4508.637,
        firstGoldPrice = 1842229.15654;
    for (var index = 0; index < records[0].length; index++) {
        let goldRc = recordGolds[index],
            dollarRc = recordDollars[index],
            time = goldRc[4],
            goldPrice = goldRc[2],
            dollarPrice = dollarRc[2];
        data = {
            "time": time,
            "value": Math.round(((goldPrice - firstGoldPrice) / firstGoldPrice) * 1000) / 1000,
            "value1": Math.round(((dollarPrice - firstDollarPrice) / firstDollarPrice) * 1000) / 1000
        }
        chartData.push(data);
    }

})


AmCharts.ready(function () {
    // SERIAL CHART
    chart = new AmCharts.AmSerialChart();

    chart.dataProvider = chartData;
    chart.marginLeft = 10;
    chart.categoryField = "time";
    // chart.language = "cn";
    // chart.dataDateFormat = "YYY-MM-DD";

    // listen for "dataUpdated" event (fired when chart is inited) and call zoomChart method when it happens
    chart.addListener("dataUpdated", zoomChart);

    // AXES
    // category
    var categoryAxis = chart.categoryAxis;
    categoryAxis.parseDates = true; // as our data is date-based, we set parseDates to true
    // categoryAxis.minPeriod = "dd/MM/YYYY"; // our data is yearly, so we set minPeriod to YYYY
    categoryAxis.dashLength = 3;
    categoryAxis.minorGridEnabled = true;
    categoryAxis.minorGridAlpha = 0.1;

    // value
    var valueAxis = new AmCharts.ValueAxis();
    valueAxis.axisAlpha = 0;
    valueAxis.inside = true;
    valueAxis.dashLength = 3;
    chart.addValueAxis(valueAxis);

    // GRAPH
    graph = new AmCharts.AmGraph();
    graph.type = "smoothedLine"; // this line makes the graph smoothed line.
    graph.lineColor = "#d1655d";
    // graph.negativeLineColor = "#637bb6"; // this line makes the graph to change color when it drops below 0
    // graph.bullet = "round";
    // graph.bulletSize = 8;
    // graph.bulletBorderColor = "#FFFFFF";
    // graph.bulletBorderAlpha = 1;
    // graph.bulletBorderThickness = 2;
    graph.lineThickness = 2;
    graph.valueField = "value";
    graph.balloonText = "[[time]]<br><b><span style='font-size:14px;'>[[value]]</span></b>";
    chart.addGraph(graph);
    graph1 = new AmCharts.AmGraph();
    // graph1.type = "smoothedLine"; // this line makes the graph1 smoothed line.
    graph1.lineColor = "#2980B9";
    // graph1.negativeLineColor = "#154360"; // this line makes the graph1 to change color when it drops below 0
    // graph1.bullet = "round";
    // graph1.bulletSize = 8;
    // graph1.bulletBorderColor = "#FFFFFF";
    // graph1.bulletBorderAlpha = 1;
    // graph1.bulletBorderThickness = 2;
    graph1.lineThickness = 2;
    graph1.valueField = "value1";
    graph1.balloonText = "[[time]]<br><b><span style='font-size:14px;'>[[value1]]</span></b>";
    chart.addGraph(graph1);
    // CURSOR
    var chartCursor = new AmCharts.ChartCursor();
    chartCursor.cursorAlpha = 0;
    chartCursor.cursorPosition = "mouse";
    chartCursor.pan = true;
    // chartCursor.categoryBalloonDateFormat = "YYYY";
    chart.addChartCursor(chartCursor);

    // SCROLLBAR
    var chartScrollbar = new AmCharts.ChartScrollbar();
    chart.addChartScrollbar(chartScrollbar);

    chart.creditsPosition = "bottom-right";

    // WRITE
    chart.write("chartdiv");
});

// this method is called when chart is first inited as we listen for "dataUpdated" event
function zoomChart() {
    // different zoom methods can be used - zoomToIndexes, zoomToDates, zoomToCategoryValues
    const PERIOD = 10;
    let currentDate = new Date(),
        from = new Date(currentDate.getFullYear() - PERIOD, 0),
        to = currentDate;
    chart.zoomToDates(from, to);
}