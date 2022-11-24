$("#searchFarm").click(function(e) {
    e.preventDefault();
    $.ajax({
        type: "GET",
        url: "/api/farm/" + $("#farm").val(),
        success: function(result) {
            alert("Hay " + result.chickenList.length + " pollos.");
            console.log(result.status.locationID);
        },
        error: function(result) {
            alert('ERROR');
        }
    });
});

$("#lookUpFarm").click(function(e){
	/*e.preventDefault();
	//	
	window.location.href = "farm.html"*/
	e.preventDefault();
    $.ajax({
        type: "GET",
        url: "/home/farm/" + $("#farm").val(),
        success: function(result) {
            window.location.href = "/home/farm/" + $("#farm").val()
        },
        error: function(result) {
            alert('ERROR');
        }
    });
	

});

