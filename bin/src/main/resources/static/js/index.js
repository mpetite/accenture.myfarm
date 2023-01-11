function buyChicken(id){
		$.ajax({
		        type: "POST",
		        url: "/api/buyChicken",
		        data: {"id": id},
		        success: function(result) {
		            location.reload();
		        },
		        error: function (result) {
					alert(result.responseText);
		        }
		    });
};
		function buyEgg(id){
		$.ajax({
		        type: "POST",
		        url: "/api/buyEgg",
		        data: {"id": id},
		        success: function(result) {
		        	
		            location.reload();
		        },
		        error: function(result) {
		            alert(result.responseText);
		        }
		    });
};
		function sellChicken(id){
		$.ajax({
		        type: "POST",
		        url: "/api/sellChicken",
		        data: {"id": id},
		        success: function(result) {
		        	
		            location.reload();
		        },
		        error: function(result) {
		            alert(result.responseText);
		        }
		    });
};
		function sellEgg(id){
		$.ajax({
		        type: "POST",
		        url: "/api/sellEgg",
		        data: {"id": id},
		        success: function(result) {
		            location.reload();
		        },
		        error: function(result) {
		            alert(result.responseText);
		        }
		    });
};
		function agregarDia(){
		$.ajax({
		        type: "POST",
		        url: "/api/agregarDia",
		        success: function(result) {
		            location.reload();
		        },
		        error: function(result) {
		            alert(result.responseText);
		        }
		    });
};
