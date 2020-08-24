/**
 * 
 */
	// Get gateList

	const url = "http://localhost:8080/airport-monitor-server/gateNames/getGateNames"; 
	// Get data
    var eventSource = new EventSource( url );
    
	eventSource.onmessage = function(event) {
		var gateNameList = JSON.parse(event.data);
		console.log("event! Recieved: "+gateNameList)
		// getting the current tablebody
		var current_tbody = document.getElementById("buttonList"); 
		// create new tbody 
		var new_tbody = document.createElement('tbody');
		new_tbody.setAttribute("id", "buttonList"); // adding id to the new tbody
		new_tbody.setAttribute("class", "list"); // adding class to the new tbody

		for (var i in gateNameList) {
			var gateName = JSON.parse(gateNameList[i]).gateName;			
			// inserting new row and cells
			var row = new_tbody.insertRow(i); 	// <tr></tr>
			var cell1 = row.insertCell(0); 		// <td></td>
			var cell2 = row.insertCell(1); 		// <td></td>
			// set cell content
			cell1.innerHTML = linkedButton(gateName, "arrival");
			cell2.innerHTML = linkedButton(gateName, "departure");
		}
		// replace the current tbody with the new tbody
		current_tbody.parentNode.replaceChild(new_tbody, current_tbody);

	}; 



	var linkedButton = function(gateName, arrivalOrDeparture){
		var link = ""+arrivalOrDeparture+".html?gate="+gateName;
		var buttonText = "Gate "+gateName+" "+arrivalOrDeparture; 
		var buttonScript = "<button onclick = \"window.location.href='"+link+"'\">"+buttonText+"</button>";
		return buttonScript;
	}

	eventSource.addEventListener("error", function(){console.log("error here too");});

	eventSource.onopen = function(){
		// not implemented
	}

	eventSource.onerror = function(event) {		
        if (event.eventPhase == EventSource.CLOSED) {
			eventSource.close();
		}
	}