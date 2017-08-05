loadData('/services.php/porfolio','porfolio').then(datas=>{
    if(datas.length>0){
        const data = datas[datas.length-1];
        if(data){
            $('#pfl_gold').innerText = data[1];
            $('#pfl_dollar').innerText = data[2];
            $('#pfl_bank').innerText = data[3];
            $('#pfl_from').innerText = data[4];
            $('#pfl_to').innerText = data[5];
        }
    }
});