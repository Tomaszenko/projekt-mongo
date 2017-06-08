$(function(){
    let state = 0;
    $('.switcher').each().on('click', function(){
        event.preventDefault();
        if(state===0) {
            $(this).siblings('.intro')[0].css('display', 'none');
            $(this).siblings('.full')[0].css('display', 'block');
        } else {
            $(this).siblings('.intro')[0].css('display', 'block');
            $(this).siblings('.full')[0].css('display', 'none');
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