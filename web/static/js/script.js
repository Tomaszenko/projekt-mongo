$(function(){
    let state = 0;
    $(".switcher").on("click", function(){
        if(state===0) {
            $(this).closest(".entry").find(".my-intro").css("display", "none");
            $(this).closest(".entry").find(".my-full").css("display", "block");
            $(this).text("Schowaj");
        } else {
            $(this).closest(".entry").find(".my-intro").css("display", "block");
            $(this).closest(".entry").find(".my-full").css("display", "none");
            $(this).text("Czytaj więcej");
        }
        state = (state+1)%2;
        console.log(state);
    });
    /// / searchViaAjax();})

});

// function searchViaAjax() {
//
//     alert("kukulele");
//     console.log("kukulele");
//
//     let search = {};
//     search["username"] = "tomko";
//     search["email"] = "tomko@mail.pl";
//
//     $.ajax({
//         type : "POST",
//         contentType : "application/json",
//         url : "http://localhost:8080/rest/entries",
//         data : JSON.stringify(search),
//         dataType : 'json',
//         timeout : 100000,
//         success : function(data) {
//             console.log("SUCCESS: ", data);
//             // display(data);
//         },
//         error : function(e) {
//             console.log("ERROR: ", e);
//             // display(e);
//         },
//         done : function(e) {
//             console.log("DONE");
//             // enableSearchButton(true);
//         }
//     });
//
// }