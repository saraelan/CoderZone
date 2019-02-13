var datasetToDisplay = 0;
var arrayObj = [];
var currentLoadedData = "";
var column = [];
var verticalSelection = [];
var data;
var final_data;
var map;
var datay = "";


const colorCodes = ['rgba(255, 99, 132, 1)',
				   'rgba(54, 162, 235, 1)',
				   'rgba(255, 206, 86, 1)',
				   'rgba(75, 192, 192, 1)',
				   'rgba(153, 102, 255, 1)',
				   'rgba(255, 159, 64, 1)',
				   'rgb(255,140,0)',
				   'rgba(54, 162, 235, 1)',
				   'rgb(184,134,11)',
				   'rgb(240,230,140)',
				   'rgb(255,255,0)',
				   'rgb(85,107,47)',
				   'rgb(107,142,35)',
				   'rgb(127,255,0)',
				   'rgb(0,128,0)',
				   'rgb(144,238,144)',
				   'rgb(0,255,127)',
				   'rgb(32,178,170)',
				   'rgb(47,79,79)',
				   'rgb(0,139,139)',
				   'rgb(0,255,255)',
				   'rgb(70,130,180)',
				   'rgb(135,206,235)',
				   'rgb(0,0,128)',
				   'rgb(65,105,225)',
				   'rgb(128,0,128)',
				   'rgb(255,0,255)',
				   'rgb(139,69,19)',
				   'rgb(210,105,30)',
				   'rgb(244,164,96)',
];
//let DataFrame = dfjs.DataFrame;
function loadData(dataset){
  var value = dataset.getAttribute('value');
  if(value!="upload"){

      buildData(value);
			datasetToDisplay = 0;
		//	resetfilterController();

  }
  else if(value == "upload"){
    var fileSelect = document.getElementById("fileSelect"), fileElem = document.getElementById("fileElem");
    console.log(fileSelect);
    fileElem.click();
  }

}

function handleFiles(files) {
  let fileList = files;
  console.log(window.URL.createObjectURL(fileList[0]));
  let file = window.URL.createObjectURL(fileList[0]);
  let fileName = fileList[0].name;
  var fileElem = document.getElementById("fileElem");
	//resetfilterController();
  //console.log(fileElem.value);
  buildData(file);
  //makeFilterElements();
   //makeObjects(colData);
   //ChartGenerator();
   filterController();

}

function filterController(){
  var div = document.getElementById("filterController");
  div.style.display = "block";
	var filter_button = "<button id='showfilters' class='btnbtn-primary filterbtn'  > Show Filters </button>";
	var chart_button = "<button id='showcharts' class='btnbtn-primary chartbtn' > Show Charts </button>";
	var map_button = "<button id='showmap' class='btnbtn-primary mapbtn' > Show Map </button>";
	div.innerHTML = filter_button + " " + chart_button + " " + map_button;
  var showFilter = document.getElementById("showfilters");
	hideFilterElements();
	hideChartElements();
	hideMapElements();

  showFilter.addEventListener('click',function(){
    if(showFilter.innerText == "Show Filters"){
      makeFilterElements();
      showFilter.innerText = "Hide Filters";
			showFilter.style.background = " #e74c3c";
    }
    else if(showFilter.innerText == "Hide Filters"){
      showFilter.innerText = "Show Filters";
      hideFilterElements();
			showFilter.style.background = " ForestGreen";
    }

  });

  var showCharts = document.getElementById("showcharts");

  showCharts.addEventListener('click',function(){
    if(showCharts.innerText == "Show Charts"){
      makeChartElements();
      showCharts.innerText = "Hide Charts";
			showCharts.style.background = " #e74c3c ";
    }
    else if(showCharts.innerText == "Hide Charts"){
      showCharts.innerText = "Show Charts";
      hideChartElements();
			showCharts.style.background = " ForestGreen";
    }

  });

	var showmaps = document.getElementById("showmap");

	showmap.addEventListener('click',function(){
    if(showmap.innerText == "Show Map"){
      makeMapElements();
      showmap.innerText = "Hide Map";
			showmap.style.background = " #e74c3c ";
    }
    else if(showmap.innerText == "Hide Map"){
      showmap.innerText = "Show Map";
      hideMapElements();
			showmap.style.background = " ForestGreen";
    }

  });


}





function buildData(value){

  let DataFrame = dfjs.DataFrame;
  currentLoadedData = value;
  DataFrame.fromCSV(currentLoadedData).then(df =>
  {
      data = df.toJSON('ShareBikeJSON.json');
      datasetToDisplay = data;
      final_data = data;
      verticalSelection = df.toArray();
      column = df.listColumns();
			generateTable(datasetToDisplay);
  });
	datasetToDisplay = new Dataset();
	//console.log(JSON.parse(datasetToDisplay));
	filterController();
}

function reset(){
   generateTable(final_data);
   datasetToDisplay = final_data;
   columnValue = [];
	 filterController();
   disableDisability();

}

function resetfilterController(){
  // var div = document.getElementById("filterController");
  // div.style.display = "block";
  var showFilter = document.getElementById("showfilters");
  //showFilter.style.display = "block";
  showFilter.innerText = "Show Filters";
  var showCharts = document.getElementById("showcharts");
  //showCharts.style.display = "block";
  showCharts.innerText = "Show Charts";
	var showmap = document.getElementById("showmap");
  //showCharts.style.display = "block";
  showmap.innerText = "Show Charts";
  filterController();
}


function generateTable(datas){

  var div = document.getElementById('tableRenderer');
  //makeTableStyle(div);
  if(div){
    div.innerHTML = "";
  }

  var displayTable = "<table id='dataTable' class='table table-striped table-bordered' style=' display:block; overflow: auto; overflow-x: visible;' cellspacing='0'  height='500'><thead ><tr>";
	var data = JSON.parse(datas);
  // console.log("DATA",data);
	var headers = [];
	for (var key in data[0]) {
		//alert(key);
		displayTable+= "<th>" +key+ "</th>";
		headers.push(key);
	}
	displayTable+= "</tr></thead>";
	displayTable+= "<tbody >";


	for (var i = 0; i < data.length; i++) {
		displayTable+= "<tr>";
		for(var j=0;j<headers.length;j++){
		displayTable+= "<td>" +data[i][headers[j]]+" </td>";
    //console.log(data[i][headers[j]]);
		}
		displayTable+="</tr>"
	}

	displayTable+="</tbody></table>";

//  var Columns = makeColumnHeaderForTable(colData);


  div.innerHTML = displayTable;

}

function makeChartElements(){
  var div = document.getElementById("chartContainer");
  div.style.display = "block";
  selectoption();
}

function hideChartElements(){
  var div = document.getElementById("chartContainer");
  div.style.display = "none";

}


function hideMapElements(){
	var div = document.getElementById("mapcontainer");
	div.style.display = "none";
}

function selectoption(){
  var select = document.getElementsByClassName("input_field");
  var option = "";
  for(var i = 0; i < column.length; i++)
    option += "<option value='"+column[i]+"'>"+column[i]+"</option>";
  for(var j =0; j< select.length; j++)
    select[j].innerHTML = option;
}

function makeFilterElements(){
   //var fulldiv = document.getElementById("filterContainer");
    var div = document.getElementById("rowFilter");
    div.style.display = "block";
    var div1 = document.getElementById("columnFilter");
    div1.style.display = "block";

    var div3 = document.getElementById("col_filt");
    selectoption();
		var fil = document.getElementById("rowValue");
		fil.value = "";

    var html ="";
    for(var i = 0; i<column.length;i++){
      var col = column[i];
    html += "<input id ='"+col+"' type='checkbox' value = '"+col+"' name='check' checked/>"+"<label for='id'>"+col+"</label>"+ " ";

    }
    div3.innerHTML = html;

}

function hideFilterElements(){
  var div = document.getElementById("rowFilter");
  div.style.display = "none";
  var div1 = document.getElementById("columnFilter");
  div1.style.display = "none";
}

function enableDisability(columnValue){
   //Getting all select elements
   var arraySelects = document.getElementsByTagName('option');
   //console.log(columnValue,arraySelects[0].value);
   //Getting selected index
       for(var i =0;i<arraySelects.length;i++){
         var label = arraySelects[i].value;
         if(columnValue.indexOf(label)<0){
           arraySelects[i].disabled = true;
         }

   }

}

function disableDisability(){
  var arraySelects = document.getElementsByTagName('option');
  //console.log(columnValue,arraySelects[0].value);
  //Getting selected index
      for(var i =0;i<arraySelects.length;i++){

          arraySelects[i].disabled = false;


  }
}

function filterColumn(){

     var select_column_div = document.getElementsByName("check");
     const columnValue = [];
     for(var i = 0 ; i < select_column_div.length; i++ ){
      		if(select_column_div[i].checked) {
      			columnValue.push(select_column_div[i].value);
      		}
	     }
       enableDisability(columnValue);
      var filter_data = new Dataset();
			let filter = new Filter();
			filter_column = filter.FilterColumn(filter_data, columnValue);
	    var fil = JSON.stringify(filter_column);
	    //console.log("FILTERED",fil);
	    generateTable(fil);
	    datasetToDisplay = fil;

}



function filterRow(){
    var rowToFilter = document.getElementById("rowSelector");
    var value = rowToFilter.value;
		var rowValue = document.getElementById("rowValue").value;
		const filter_data = new Dataset();
		let filter = new Filter();
		let filter_row = filter.FilterRow(filter_data, value, rowValue);
    var fil = JSON.stringify(filter_row);
    generateTable(fil);
    datasetToDisplay = fil;
}



function FullData(){
  return JSON.parse(final_data);
}

function ChartGenerator(){
  var div = document.getElementById("chartContainer").style.display = "block";

}



function generateJSON(){
  return JSON.parse(datasetToDisplay);
}


function downloadCSV(csv, filename) {
    var csvFile;
    var downloadLink;
    csvFile = new Blob([csv], {type: "text/csv"});
    downloadLink = document.createElement("a");
    downloadLink.download = filename;
    downloadLink.href = window.URL.createObjectURL(csvFile);
    downloadLink.style.display = "none";
    document.body.appendChild(downloadLink);
    downloadLink.click();
}

function downloadJSON(filename){
    var jsonfile;
    var downloadLink;
    //var jsonData = JSON.parse(datasetToDisplay);
		var json_data = new Dataset();
		var string = JSON.stringify(json_data);
    jsonfile = new Blob([string], {type:"text/json"});
    downloadLink = document.createElement("a");
    downloadLink.download = filename;
    downloadLink.href = window.URL.createObjectURL(jsonfile);
    downloadLink.style.display = "none";
    document.body.appendChild(downloadLink);
    downloadLink.click();
}

function exportTableToCSV(filename) {

    var csv = [];
    var table = document.getElementById("dataTable");
    var rows = document.querySelectorAll("table tr");
    for (var i = 0; i < rows.length; i++) {
        var row = [], cols = rows[i].querySelectorAll("td, th");
        for (var j = 0; j < cols.length; j++)
            row.push(cols[j].innerText);
        csv.push(row.join(","));
    }

    downloadCSV(csv.join("\n"), filename);
}

function makeMapElements(){
	var div = document.getElementById("mapcontainer");
	div.style.display = "block";
	let map_data = FullData();
	var array_latlng = ["Latitude","Longitude"];
	let la = new Filter();
	let latlng = la.FilterColumn(map_data, array_latlng);


	var child = document.createElement("p");
	child.innerHTML = "If you see this message, then there are no coordinates in the dataset";
	div.appendChild(child);
	let maps = new MapConfig();
  maps.center(latlng[0]["Latitude"],latlng[0]["Longitude"],div);
	 for(var i = 0;i < latlng.length;i++)
    {
      var lat = latlng[i]['Latitude']; //latitude
      var lng = latlng[i]['Longitude'];
			maps.drawMap(lat,lng);
     }
}
