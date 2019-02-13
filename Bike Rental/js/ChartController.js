
class ChartFactory{
	createChart(chartType){
		let chart = null;
		if(chartType == "pie"){
			chart = new PieChart();
		} else if(chartType == "bar"){
			chart = new BarChart();
		} else if(chartType == "line"){
			chart = new LineChart();
		}else if(chartType == "doughnut"){
			chart = new DoughnutChart();
		}
		return chart;
	}
}


class ChartMain{
	constructor(chartFactory){
		this.chartFactory = new ChartFactory();
	}

	orderChart(chartType){
		var chart = this.chartFactory.createChart(chartType);
		return chart;
	}
}


class ChartOperation{

	constructor(chart, name,type, plotchartTool, labels = [], chartData = [], columnValue ,backgroundColor = '', borderColor = '', options = {}) {
		if(chart != undefined || chart != null){
		this.name = chart.name;
		this.type = chart.type;
		this.plotchartTool = chart.plotchartTool;
		this.labels = chart.labels;
		this.chartData = chart.chartData;
    this.columnValue = chart.columnValue;
		this.backgroundColor = chart.backgroundColor;
		this.borderColor = chart.borderColor;
		this.options = chart.options;
		}
	}

	plot(){
		var plotchartTool = this.plotchartTool;
		if(plotchartTool){
			plotchartTool.innerHTML = "";
		}
		new Chart(plotchartTool, {
			type: this.type,
			data: {
				labels: this.labels,
				datasets: [{
					label: this.columnValue,
					data: this.chartData,
					backgroundColor: this.backgroundColor,
					borderColor: 'green',
					borderWidth: 1
				}]
			},
			options: this.options
		});
	}

	getbgColor(noOfCols){
		var bgColor = colorCodes;
		var bgColors = [];
		for(var i=0;i<noOfCols;i++){
			var randomIndex = Math.floor((Math.random() * (bgColor.length-1)) + 0);
			bgColors.push(bgColor[randomIndex]);
		}
		return bgColors;
	}

	getborderColor(noOfCols){
		var borderColor = colorCodes;
		var borderColors = [];
		for(var i=0;i<noOfCols;i++){
			var randomIndex = Math.floor((Math.random() * (borderColor.length-1)) + 0);
			borderColors.push(borderColor[randomIndex]);
		}
		return borderColors;
	}
}


class ChartConfig extends ChartOperation{

	constructor(chart) {
		super(chart);
	}
	setLabelAndData(labels, data, columnValue){
	  this.labels = labels;
	  this.chartData = data;
    this.columnValue = columnValue;
	}

	applyBackgroundColor(chartConfig){

	}

	applyBorderColor(chartConfig){

	}


}


class ChartDecorator extends ChartConfig {

	applyBorderColor(chartConfig){
		chartConfig.borderColor = chartConfig.getborderColor(chartConfig.chartData.length);
	}
}


class BarChartDecorator extends ChartDecorator {

	applyBackgroundColor(chartConfig){
		chartConfig.backgroundColor = chartConfig.getbgColor(chartConfig.chartData.length);
	}
}

class PieChartDecorator extends ChartDecorator {

	applyBackgroundColor(chartConfig){
		chartConfig.backgroundColor = chartConfig.getbgColor(chartConfig.chartData.length);
	}
}



class DoughnutChartDecorator extends ChartDecorator {

	applyBackgroundColor(chartConfig){
		chartConfig.backgroundColor = chartConfig.getbgColor(chartConfig.chartData.length);
	}
}

class LineChartDecorator extends ChartDecorator {

}



class PieChart extends ChartConfig{
  constructor() {
    super({
      name: 'Pie Chart',
      type: 'pie',
      plotchartTool: document.getElementById("pieChart"),
      labels: [],
  	  chartData: [],
  	  backgroundColor: '',
  	  borderColor: '',
  	  options:{
  			title: {
  				display: true,
  				text: 'Pie Chart',
  				position: 'bottom',
  				fontSize: 20,
  				fontStyle: 'bold'
  			}
  		}
    });
  }
}


class DoughnutChart extends ChartConfig{
  constructor() {
    super({
      name: 'Doughnut Chart',
      type: 'doughnut',
      plotchartTool: document.getElementById("doughnutChart"),
      labels: [],
  	  chartData: [],
  	  backgroundColor: '',
  	  borderColor: '',
  	  options:{
  			title: {
  				display: true,
  				text: 'Doughnut Chart',
  				position: 'bottom',
  				fontSize: 20,
  				fontStyle: 'bold'
  			}
  		}
    });
  }
}


class BarChart extends ChartConfig{

  constructor() {
    super({
      name: 'Bar Chart',
      type: 'bar',
      plotchartTool: document.getElementById("barChart"),
      labels: [],
  	  chartData: [],
  	  backgroundColor: '',
  	  borderColor: '',
  	  options:{
  			title: {
  				display: true,
  				text: 'Bar Chart',
  				position: 'bottom',
  				fontSize: 20,
  				fontStyle: 'bold'
  			},
  			scales: {
  	            yAxes: [{
  	                ticks: {
  	                    min: 0
  	                }
  	            }]
          }
  		}
      });
  }
}



class LineChart extends ChartConfig{

  constructor() {
    super({
      name: 'Line Chart',
      type: 'line',
      plotchartTool: document.getElementById("lineChart"),
      labels: [],
  	  chartData: [],
  	  backgroundColor: '',
  	  borderColor: '',
  	  options:{
  			title: {
  				display: true,
  				text: 'Line Chart',
  				position: 'bottom',
  				fontSize: 20,
  				fontStyle: 'bold'
  			},
        scales: {
  	            yAxes: [{
  	                ticks: {
  	                    min: 0
  	                }
  	            }]
          }
  		}
      });
  }
}
