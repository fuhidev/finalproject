loadData('/services.php/porfolio','porfolio').then(datas=>{
    if(datas.length>0){
        const data = datas[datas.length-1];
        if(data){
            $('#pfl_gold').text(data[1] + '%');
            $('#pfl_dollar').text(data[2]+'%');
            $('#pfl_bank').text(data[3]+'%');
            $('#pfl_from').text(data[4]+'%');
            $('#pfl_to').text(data[5]);
        }
    }
});