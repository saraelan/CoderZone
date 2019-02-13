google.charts.load('current', {packages: ['corechart', 'bar']});
// google.charts.setOnLoadCallback(drawStacked);


// 3. This function fires
  $("#btnGetChartData").click(function () {
       $("#btnGetChartData").hide();
      $.ajax({
          url: "RunScorers",
          type: "POST",

      }).done(function (results) {
        // console.log(results);
        var obj = JSON.parse(results);
        // 5. Create a new DataTable (Charts expects data in this format)
        var data = new google.visualization.DataTable();

        // 6. Add two columns to the DataTable
        data.addColumn('string', 'name');
        data.addColumn('number',   'runs');
        var i;
        // 7. Cycle through the records, adding one row per record
        for(i in obj){
          data.addRow([
            obj[i]["name"],obj[i]["runs"],
          ]);
            console.log(obj[i]["runs"]);
        }
        // results.forEach(function(packet) {
        //   data.addRow([
        //       results["name"],results["runs"],
        //     ]);
        // });

        // 8. Create a new line chart
        var chart = new google.visualization.LineChart($('#chart_div').get(0));

        // 9. Render the chart, passing in our DataTable and any config data
        chart.draw(data, {
          title:  'Players',
          height: 150
        });

      });

  });
google.charts.load('current', {packages: ['corechart', 'bar']});
// google.charts.setOnLoadCallback(drawStacked);


// 3. This function fires
  $("#btnGetChartData").click(function () {
       $("#btnGetChartData").hide();
      $.ajax({
          url: "RunScorers",
          type: "POST",

      }).done(function (results) {
        // console.log(results);
        var obj = JSON.parse(results);
        // 5. Create a new DataTable (Charts expects data in this format)
        var data = new google.visualization.DataTable();

        // 6. Add two columns to the DataTable
        data.addColumn('string', 'name');
        data.addColumn('number',   'runs');
        var i;
        // 7. Cycle through the records, adding one row per record
        for(i in obj){
          data.addRow([
            obj[i]["name"],obj[i]["runs"],
          ]);
            console.log(obj[i]["runs"]);
        }
        // results.forEach(function(packet) {
        //   data.addRow([
        //       results["name"],results["runs"],
        //     ]);
        // });

        // 8. Create a new line chart
        var chart = new google.visualization.LineChart($('#chart_div').get(0));

        // 9. Render the chart, passing in our DataTable and any config data
        chart.draw(data, {
          title:  'Players',
          height: 450
        });

      });

  });
