
google.charts.load('current', {'packages':['corechart']});
      // google.charts.setOnLoadCallback(drawVisualization);

function drawChart(reviewJSON){
  //console.log(reviewJSON);
  var obj = reviewJSON;
  // 5. Create a new DataTable (Charts expects data in this format)
  var data = new google.visualization.DataTable();
  console.log(data);
  //6. Add two columns to the DataTable
  data.addColumn('string','Id')
   //data.addColumn('string','team1');
  //data.addColumn('string', 'team2');
  data.addColumn('number',   'team1_runs');
  data.addColumn('number','team2_runs');
  var i;
  data.setColumnLabel(0,obj[0]['team1']+' vs ' + obj[0]['team2']);
  data.setColumnLabel(1,obj[0]['team1']);
  data.setColumnLabel(2,obj[0]['team2']);
  // 7. Cycle through the records, adding one row per record
  for(i in obj){
    data.addRow([
      obj[i]["Id"],obj[i]["team1_runs"],obj[i]["team2_runs"],
    ]);
    //  console.log(obj[i]["runs"]);
  }
  // results.forEach(function(packet) {
  //   data.addRow([
  //       results["name"],results["runs"],
  //     ]);
  // });

  //8. Create a new line chart
  var chart = new google.visualization.ComboChart($('#chart_div').get(0));
  var options = {
      title : 'Match Details',
      vAxis: {title: obj[0]["team1"]+'vs'+obj[0]["team2"]},
      hAxis: {title: 'Team'},
      seriesType: 'bars',
      series: {5: {type: 'line'}}
    };
  // 9. Render the chart, passing in our DataTable and any config data
  var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
    chart.draw(data, options);

}
