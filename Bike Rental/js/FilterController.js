class Filter{
	constructor(){}

	FilterRow(filter_data, value, rowValue){
		let result = filter_data.filter(fil => fil[value] === rowValue);
		return result;
    }

    FilterColumn(filter_data, columnValue){
      var col = new Array();
      //var result ={};

      for(var x in filter_data) {
       var obj = new Object();
       for(var i in columnValue){
        var result = filter_data.map(item => { return item[columnValue[i]] });
          obj[columnValue[i]] = result[x];
      }
      col.push(obj);
      }
      return col;
    }

}
