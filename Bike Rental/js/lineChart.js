function lineChart(){
        var chart_data = new Dataset();
        var count = 0;
        var chartdiv = document.getElementById("lineChart");
        if(chartdiv){
          chartdiv.innerHTML = "";
        }
        chartdiv.style.display = "block";

        var columnValue = document.getElementById("chart_column_line").value;
          chartdiv.innerHTML ="";
      var labels = chart_data.map(item => { return item[columnValue]});
      var data = [], uniq =[];
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

      var rgb = [];
      for(var i in chart_data){
        rgb.push(Math.floor(Math.random() * 255));
      }

      let cs = new ChartMain();
      var chart = null;
      let isBackgroundColorRequired = true;
      let lineChartDecorator = new LineChartDecorator();
      chart = cs.orderChart("line");
      chart.setLabelAndData(key,value,columnValue);
      lineChartDecorator.applyBackgroundColor(chart);
      lineChartDecorator.applyBorderColor(chart);
      chart.plot();



}
