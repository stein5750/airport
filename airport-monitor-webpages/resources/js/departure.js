/**
 * 
 */
  function getDepartures(){
	// Get gate from url parameters
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	const queryGate = urlParams.get('gate');
	const url = "http://localhost:8080/airport-monitor-server/departures/get/"+queryGate;
	
	// Get data
	var source = new EventSource( url );
	
	source.onmessage = function(event) {
		// Parse data
		var data = JSON.parse(event.data);
		
		// Update elements with recieved data
		document.getElementById("flight").innerHTML = data.flight;
		document.getElementById("destination").innerHTML = data.location.substring(0,14); // get first 14 letters of location
		document.getElementById("departureTime").innerHTML = data.time.split("@")[1]; // Get second half of yyyy-MM-dd@HH:mm:ss
		document.getElementById("status").innerHTML = data.status;
	}; 
	}