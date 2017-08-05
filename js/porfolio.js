loadData('/services.php/porfolio','porfolio').then(datas=>{
    if(datas.length>0){
        const data = datas[datas.length-1];
        if(data){
            $('#pfl_gold').text(data[1]*100 + '%');
            $('#pfl_dollar').text(data[2]*100+'%');
            $('#pfl_bank').text(data[3]*100+'%');
            
            let from = new Date(data[4]),
            fromString = `${from.getDate}-${from.getMonth()}-${from.getFullYear}`;
            $('#pfl_from').text(fromString);

            let to = new Date(data[5]);
            toString = `${to.getDate}-${to.getMonth()}-${to.getFullYear}`;
            $('#pfl_to').text(toString);
        }
    }
});