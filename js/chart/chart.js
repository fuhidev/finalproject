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
    switch (unit){
        case 'day':
        this.setDate(this.getDate()+num);
        break;
        case 'week':
        this.setDate(this.getDate()+num*7);
        break;
        case 'month':
        this.setMonth(this.getMonth()+num);
        break;
        case 'year':
        this.setFullYear(this.getFullYear()+num);
    }
    return this;
}


google.charts.setOnLoadCallback(function () {
    data = new google.visualization.DataTable();
    data.addColumn('string', 'Time');
    data.addColumn('number', 'VN Gold');
    data.addColumn('number', 'US Gold');
    chart = new google.charts.Line(document.getElementById('line_top_x'));
    // addRows(loadData());
    // chart.draw(data, google.charts.Line.convertOptions(options))

    $('#fromDate').on('change', changeTimer);
    $('#toDate').on('change', changeTimer);
    $("button[name='period']").on('click', function (event) {
        var period = event.target.getAttribute('data-period');
         var date = new Date();
        switch (period) {
            case '1w':
               
                setToDate(getCurrentTime());
                setFromDate(getTime(date.addDays('week',-1)))

                break;
            case '3m':
            setToDate(getCurrentTime());
                setFromDate(getTime(date.addDays('week',-3)))
                break;
            case '6m':
            setToDate(getCurrentTime());
                setFromDate(getTime(date.addDays('week',-6)))
                break;
            case '1y':
            setToDate(getCurrentTime());
                setFromDate(getTime(date.addDays('year',-1)))
                break;
            case '3y':
            setToDate(getCurrentTime());
                setFromDate(getTime(date.addDays('year',-3)))
                break;
            case '10y':
            setToDate(getCurrentTime());
                setFromDate(getTime(date.addDays('year',-10)))
                break;
            default:
                setToDate(getCurrentTime());
                setFromDate(new Date(1900,01,01));

        }
        changeTimer();

    });
    var date = new Date();

    setFromDate(date.getFullYear() + "-01-01");
    setToDate(getCurrentTime());

    changeTimer();
});

function setToDate(val) {
    $('#toDate').val(val);
}

function setFromDate(val) {
    $('#fromDate').val(val);
}

function getCurrentTime() {
    var date = new Date();
 return   getTime(date);
}

function getTime(date) {
    var y = date.getFullYear();
    var m = parseInt(date.getMonth()) < 10 ? '0' + date.getMonth() : date.getMonth();
    var d = parseInt(date.getDate()) < 10 ? '0' + date.getDate() : date.getDate();

    var time = y + '-' + m + '-' + d;
    return time;
}

function loadData(url = 'http://localhost/FinalProject/services.php/goldworld') {
    var datas;
    $.ajax({
        url: url,
        async: false,
        success: function (respone, status, xhr) {
            if (respone != undefined) {
                datas = respone.goldworld.records;

            }
        }
    });
    return datas;
}

function addRows(records) {
    data.qg = [];
    for (var i in records) {
        var rc = records[i];
        data.addRow([rc[4], rc[2], rc[3] * 22000]);

    }
}


function changeTimer() {
    var from = $('#fromDate').val();
    var to = $('#toDate').val();
    if (from != undefined && from != '' && to != undefined && to != '') {

        var records = loadData('http://localhost/FinalProject/services.php/goldworld?filter=datetime,bt,' + from + ',' + to);
        addRows(records);

        chart.draw(data, google.charts.Line.convertOptions(options));
    }
}
