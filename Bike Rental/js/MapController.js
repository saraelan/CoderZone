class MapController{
  constructor(mapProp,myCenter,new_map){

    this.mapProp = mapProp;
    this.myCenter = myCenter;
    this.new_map = new_map;

  }
  drawMap(lat,lng){
    var marker = new google.maps.Marker({
         position: new google.maps.LatLng(lat,lng),
         map: this.new_map
    });
  }
}



class MapConfig extends MapController{
  constructor(){
    super();
  }

  center(lats,lngs,div){
    this.myCenter=new google.maps.LatLng(lats,lngs);
    this.mapProp = {
            center:this.myCenter,
            zoom:14,
            minZoom:12,
            mapTypeId : google.maps.MapTypeId.ROADMAP,
            mapTypeControl: true
        };

    this.new_map = new google.maps.Map(div,this.mapProp);
  }
}
