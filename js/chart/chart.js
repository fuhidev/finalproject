google.charts.load('current', {
    'packages': ['line']
});
var data, chart;
var options = {
    chart: {
        title: 'Chart of gold and dollar',
        subtitle: 'in hundrend of dollars (USD)'
    },
    width: 900,
    height: 500,
    axes: {
        x: {
            0: {
                side: 'bottom'
            }
        }
    }
};
Date.prototype.addDays = function (unit, num) {
    switch (unit) {
        case 'day':
            this.setDate(this.getDate() + num);
            break;
        case 'week':
            this.setDate(this.getDate() + num * 7);
            break;
        case 'month':
            this.setMonth(this.getMonth() + num);
            break;
        case 'year':
            this.setFullYear(this.getFullYear() + num);
    }
    return this;
}


google.charts.setOnLoadCallback(function () {

    data = new google.visualization.DataTable();
    data.addColumn('string', 'Time');
    data.addColumn('number', 'VN Gold');
    data.addColumn('number', 'USD');
    // data.addColumn('number', 'US Gold');
    chart = new google.charts.Line(document.getElementById('line_top_x'));
    // addRows(loadData());
    // chart.draw(data, google.charts.Line.convertOptions(options))

    $('#fromDate').on('change', redraw);
    $('#toDate').on('change', redraw);
    $("button[name='period']").on('click', function (event) {
        // $('#loading').html("<img src='images/loading2.gif'id='loaded'/>").fadeIn();
        var period = event.target.getAttribute('data-period');
        var date = new Date();
        setToDate(getCurrentTime());
        switch (period) {
            case '1w':
                setFromDate(getTime(date.addDays('week', -1)))
                break;
            case '3m':
                setFromDate(getTime(date.addDays('week', -3)))
                break;
            case '6m':
                setFromDate(getTime(date.addDays('week', -6)))
                break;
            case '1y':
                setFromDate(getTime(date.addDays('year', -1)))
                break;
            case '3y':
                setFromDate(getTime(date.addDays('year', -3)))
                break;
            case '10y':
                setFromDate(getTime(date.addDays('year', -10)))
                break;
            default:
                setFromDate(getTime(new Date(1900, 01, 01)));

        }

        redraw();

    });
    var date = new Date();



    redraw();
});

function setToDate(val) {
    $('#toDate').val(val);
}

function setFromDate(val) {
    $('#fromDate').val(val);
}

function getCurrentTime() {
    var date = new Date();
    return getTime(date);
}

function getTime(date) {
    var y = date.getFullYear();
    var m = parseInt(date.getMonth()) < 10 ? '0' + date.getMonth() : date.getMonth();
    var d = parseInt(date.getDate()) < 10 ? '0' + date.getDate() : date.getDate();

    var time = y + '-' + m + '-' + d;
    return time;
}

function loadData(url = 'http://localhost:8080/finalproject/services.php/goldworld', table = 'goldworld') {
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

const addRow = (data, time, gold, usd) => {
    return new Promise((resolve, reject) => {
        data.addRow([time, gold, usd]);
    });

}

function addRows(recordGolds, recordDollars) {
    data.qg = [];
    // let firstGold = recordGolds[0],
    //     firstDollar = recordDollars[0],
    //     firstGoldPrice = firstGold[2],
    //     firstDollarPrice = firstDollar[2];
    let firstDollarPrice = 4508.637,
    firstGoldPrice = 1842229.15654;
    addRow(data, '', 0, 0);
    for (let i in recordGolds) {
        let goldRc = recordGolds[i],
                dollarRc = recordDollars[i],
                time = goldRc[4],
                goldPrice = goldRc[2],
                dollarPrice = dollarRc[2];
        // if (i == 0) {
            // addRow(data, time, 0, 0);
        // } else {
            
            // data.addRow([rc[4], rc[2], rc[3] * 22000]);
            addRow(data, time, (goldPrice - firstGoldPrice)/firstGoldPrice, (dollarPrice - firstDollarPrice)/firstDollarPrice);
        // }

    }
}

var globalData;

function redraw() {
    var from = $('#fromDate').val();
    var to = $('#toDate').val();
    if (from != undefined && from != '' && to != undefined && to != '') {

        let gold = loadData('http://localhost/services.php/goldworld?filter=datetime,bt,' + from + ',' + to);
        let dollar = loadData('http://localhost/services.php/dollar?filter=date,bt,' + from + ',' + to, 'dollar');
        Promise.all([gold, dollar]).then((records) => {
            // console.log(res);
            addRows(records[0], records[1]);
            globalData = records;
            chart.draw(data, google.charts.Line.convertOptions(options));
            // console.log(records);
        })

    }
}

$('#download').click(function () {
    var data = globalData;
    if (data == '')
        return;
    JSONToCSVConvertor(data, "Vehicle Report", true);
});

// Convert JSON to Excel
function JSONToCSVConvertor(JSONData, ReportTitle, ShowLabel) {
    //If JSONData is not an object then JSON.parse will parse the JSON string in an Object
    var arrData = typeof JSONData != 'object' ? JSON.parse(JSONData) : JSONData;

    var CSV = '';
    //Set Report title in first row or line

    CSV += ReportTitle + '\r\n\n';

    //This condition will generate the Label/Header
    if (ShowLabel) {
        var row = "";

        //This loop will extract the label from 1st index of on array
        for (var index in arrData[0]) {

            //Now convert each value to string and comma-seprated
            row += index + ',';
        }

        row = row.slice(0, -1);

        //append Label row with line break
        CSV += row + '\r\n';
    }

    //1st loop is to extract each row
    for (var i = 0; i < arrData.length; i++) {
        var row = "";

        //2nd loop will extract each column and convert it in string comma-seprated
        for (var index in arrData[i]) {
            row += '"' + arrData[i][index] + '",';
        }

        row.slice(0, row.length - 1);

        //add a line break after each row
        CSV += row + '\r\n';
    }

    if (CSV == '') {
        alert("Invalid data");
        return;
    }

    //Generate a file name
    var fileName = "Report-" + $('#fromDate').val() + "_" + $('#toDate').val();
    //this will remove the blank-spaces from the title and replace it with an underscore
    fileName += ReportTitle.replace(/ /g, "_");

    //Initialize file format you want csv or xls
    var uri = 'data:text/csv;charset=utf-8,' + escape(CSV);

    // Now the little tricky part.
    // you can use either>> window.open(uri);
    // but this will not work in some browsers
    // or you will not get the correct file extension    

    //this trick will generate a temp <a /> tag
    var link = document.createElement("a");
    link.href = uri;

    //set the visibility hidden so it will not effect on your web-layout
    link.style = "visibility:hidden";
    link.download = fileName + ".csv";

    //this part will append the anchor tag and remove it after automatic click
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}