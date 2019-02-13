function pieChart(){
        var chart_data = new Dataset();
        var count;
        var chartdiv = document.getElementById("pieChart");
        if(chartdiv){
          chartdiv.innerHTML = "";
        }
        chartdiv.style.display = "block";

        var columnValue = document.getElementById("chart_column_pie").value;
        chartdiv.innerHTML ="";
        var labels = chart_data.map(item => { return item[columnValue]});
        var data = [];
        for(var i in chart_data){
          data[i] = chart_data[i][columnValue];
        }
        var uniq = data
                  .map((name) => {
                      return {count: 1, name: name}
                    })
                    .reduce((a, b) => {
                      a[b.name] = (a[b.name] || 0) + b.count
                      return a
                    }, {})

        var key = Object.keys(uniq);
        var value = Object.values(uniq);
        var x = [];
        var rand = colorCodes[Math.floor(Math.random() * colorCodes.length)];
        for(var i in data){
          for(var y in colorCodes)
            x.push(colorCodes[y]);
        }

        let cs = new ChartMain();
        var chart = null;
        let isBackgroundColorRequired = true;
        let pieChartDecorator = new PieChartDecorator();
        chart = cs.orderChart("pie");
        chart.setLabelAndData(key,value,columnValue);
        pieChartDecorator.applyBackgroundColor(chart);
        pieChartDecorator.applyBorderColor(chart);
        chart.plot();


}
