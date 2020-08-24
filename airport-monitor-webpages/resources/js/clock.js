function displayClock(){

	let time = new Date();
	let formattedTime = time.getHours()+":"+time.getMinutes()+":"+time.getSeconds();
	document.getElementById("clock").innerHTML = formattedTime;
	setTimeout(displayClock, 1000); 
}