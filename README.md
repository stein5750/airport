# airport
Just a project while reading up on various topics.<br/>
Consequently the project will be subject to major changes in code and design,
and may break at any time.<br/>
The idea is a simulation of an airport with aircrafts, tower, gates and an information board system.

## Java
Consists of 7 interacting projects and more than 157 files and 6577 lines of code, mostly Java.<br/>
The projects use concurrency, Spring and Project Reactor.

## Servers
### airport-towerserver
Based on Reactor.<br/>
Handles communication between aircrafts and tower.<br/>

### airport-gateserver
Based on Reactor.<br/>
Handles communication between tower and gates.<br/>

### airport-monitor-server
Based on Reactor.<br/>
Handles communication between gates and monitors (web-based information boards).<br/>

## Clients
### airport-aircrafts
Spawns multiple aircrafts running in separate threads.<br/>
Each aircraft communicates with airport-tower via airport-tower-server.<br/>
* Sends flightplan to tower.
* Sends landingrequest to tower.
* Receives landingpermission from tower.
* Sends takeoffrequest to tower.
* Receives takeoffpermission from tower.

### airport-tower
Communicates with aircrafts via airport-towerserver.<br/>
Communicates with gates via airport-gateserver.<br/>
* Receives flightplan from aircraft.
* Receives landing request form aircraft.
* Receives available gate from airport-gateserver.
* Sends landing permission with gatename to aircraft.
* Sends gatename as unavailable to airport-gateserver.
* Sends info about flight and arrivaltime to a gate.
* Sends info about flight and departuretime to the same gate.
* Receives takeoff request from aircraft.
* Sends takeoff permission from to aircraft.

### airport-gates
Initiates multiple gates running in separate threads.<br/>
Each gate communicates via airport-gateserver and airport-monitor-server.<br/>
* Receives info about flight and arrivaltime from tower.
* Receives info about flight and departuretime from tower.
* Sends info about flight and arrivaltime to monitors.
* Sends info about flight and departuretime to monitors.
* Sends statusupdates for gates to monitors, like "Boarding", Go to gate" and more.

### airport-monitor-webpages
Gets information about arrivals and departures from airport-monitor-server
<br/>
<br/>
![Example image all arrivals](https://github.com/stein5750/airport/blob/master/airport-monitor-webpages/resources/examples/example_allArrivals.png)
allArrivals.html : Displays all arrivals.
<br/>
<br/>
![Example image all departures](https://github.com/stein5750/airport/blob/master/airport-monitor-webpages/resources/examples/example_allDepartures.png)
allDepartures.html : Displays all departures.
<br/>
<br/>
![Example image arrival](https://github.com/stein5750/airport/blob/master/airport-monitor-webpages/resources/examples/example_arrival.png)
arrival.html : Displays arrival for specific gate.
<br/>
<br/>
![Example image departure](https://github.com/stein5750/airport/blob/master/airport-monitor-webpages/resources/examples/example_departure.png)
departure.html : Displays departure for specific gate.
