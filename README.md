# airport
Just a project while reading up on various topics.<br/>
Consequently the project will be subject to major changes in code and design,
and may break at any time.<br/>

## Java
Consists of 7 interacting projects and a total of approximately 157 files, mostly Java.<br/>
Includes concurrency and Spring Reactor.

## Servers
### airport-towerserver
Based on Spring Reactor.<br/>
Handles communication between aircrafts and tower.<br/>

### airport-gateserver
Based on Spring Reactor.<br/>
Handles communication between tower and gates.<br/>

### airport-monitor-server
Based on Spring Reactor.<br/>
Handles communication between gates and monitors (web-based information boards).<br/>

## Clients
### airport-aircrafts
Spawns multiple aircrafts running in separate threads.<br/>
Each aircraft communicates with airport-tower through airport-tower-server.<br/>
- Sends flightplan to tower.
- Sends landingrequest to tower.
- Receives landingpermission from tower.
- Sends takeoffrequest to tower.
- Receives takeoffpermission from tower.

### airport-tower
Communicates with aircrafts through airport-towerserver.<br/>
Communicates with gates through airport-gateserver.<br/>
- Receives flightplan from aircraft.
- Receives landing request form aircraft.
- Receives available gate from airport-gateserver.
- Sends landing permission with gatename to aircraft.
- Sends gatename as unavailable to airport-gateserver.
- Sends info about flight and arrivaltime to airport-gateserver.
- Sends info about flight and departuretime to airport-gateserver.
- Receives takeoff request from aircraft.
- Sends takeoff permission from to aircraft.

### airport-gates
Initiates multiple gates running in separate threads.<br/>
Each gate communicates with airport-gateserver and airport-monitor-server.<br/>
- Receives info about flight and arrivaltime from tower.
- Receives info about flight and departuretime from tower.
- Sends info about flight and arrivaltime from to airport-monitor-server.
- Sends info about flight and departuretime to airport-monitor-server.
- Sends statusupdates for gates to airport-monior-server, like "Boarding", Go to gate" and more.

### airport-monitor-webpages
- Gets information about arrivals and departures from airport-monitor-server
- index.html : Dynamically created buttons for selecting pages.
- allArrivals.html : Displays all arrivals.
- allDepartures.html : Displays all departures.
- arrival.html : Displays arrival for specific gate.
- departure.html : Displays departure for specific gate.
