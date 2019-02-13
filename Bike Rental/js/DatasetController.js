class Dataset {

  constructor(){
    this.dataset = datasetToDisplay;
    return JSON.parse(this.dataset);
  }

  completeData(){
    this.dataset = final_data;
    return JSON.parse(this.dataset);
  }
}
