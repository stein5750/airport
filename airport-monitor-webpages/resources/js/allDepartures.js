/**
 * 
 */
	// Get data for all departures

	const url = "http://localhost:8080/airport-monitor-server/departures/getAll"; 
	// Get data
	var source = new EventSource( url );

	source.onmessage = function(event) {

		var dataList = JSON.parse(event.data);

		// getting the current tablebody
		var current_tbody = document.getElementById("departuresInnerTbody"); 
		// create new tbody 
		var new_tbody = document.createElement('tbody');
		new_tbody.setAttribute("id", "departuresInnerTbody"); // adding id to the new tbody
		new_tbody.setAttribute("class", "list"); // adding class to the new tbody

        var i = 0;
        for (i in dataList) {
			
			var data = dataList[i];

			// inserting new row and cells
			var row = new_tbody.insertRow(i); 	// <tr></tr>
			var cell0 = row.insertCell(0); 		// <td></td>
			var cell1 = row.insertCell(1); 		// <td></td>
			var cell2 = row.insertCell(2); 		// <td></td>
			var cell3 = row.insertCell(3); 		// <td></td>
			var cell4 = row.insertCell(4); 		// <td></td>
			// set cell content
			cell0.innerHTML = data.time.split("@")[1]; // Get second half of yyyy-MM-dd@HH:mm:ss
			cell1.innerHTML = data.flight;
			cell2.innerHTML = data.location.substring(0,14); // get first 14 letters of location
			cell3.innerHTML = data.gateName;
			cell4.innerHTML = data.status;			
		}
		
        // trick: inserting an empty row which allows for expanding height to 100%
        // increase line number only if there are any lines present.
        if ( i != 0){ 
          i++; 
        }
        // insert empty line
        var row = new_tbody.insertRow(i);     // <tr></tr>
		
		// replace the current tbody with the new tbody
		current_tbody.parentNode.replaceChild(new_tbody, current_tbody);

	}; 