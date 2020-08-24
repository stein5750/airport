/**
 * 
 */
 function updateHeader(){
	// Get gateName from url parameters
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	const gateName = urlParams.get('gate');
	console.log("updateHeader gatename is "+gateName);
	
	// Update header with gateName from url path
	document.getElementById("gateName").innerHTML = gateName;
}
	