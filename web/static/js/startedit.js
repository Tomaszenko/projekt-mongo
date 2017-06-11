/**
 * Created by Tomek on 07.06.2017.
 */
allEntries = [];

$(function() {
    // $('#new-tags').tagEditor();
    // $('.do-edit').on('click', function(){
    //     let link = $(this).prev();
    //     let referen = link.attr('href');
    //     console.log(referen);
    //     let res_id = referen.match(/[a-z0-9]+$/)[0];
    //     console.log(res_id);
    //
    //     event.preventDefault();
    //     selectByIdViaAjax(res_id);
    // });

    // $('#do-add').on('click', function(){
    //     event.preventDefault();
    //     console.log('kukulele');
    //     takePlaceTo("add");
    //     populateAdd();
    //     console.log('kukulele');
    // });
    //
    // $('#confirm-add').on('click', function() {
    //     event.preventDefault();
    //     addViaAjax();
    // });

    $('.submit-comment').click(function () {
        event.preventDefault();
        console.log("Klikłem");
        let commentary = {};
        let text = $(this).closest('div').find('.text').val();
        let nick = $(this).closest('div').find('.nick').val();
        let entry_id = $(this).attr('entid');
        commentary["text"] = text;
        commentary["nick"] = nick;
        console.log(commentary);
        console.log(entry_id);
        addCommentViaAjax(commentary, entry_id);
    });
});
    // $('.submit-comment').live('click', function() {
    //
    // });

    // $('#add-tag').on('click', function(){
    //     event.preventDefault();
    //     $('#new-tags').append('<li>'+$('#new-tag').val() + '</li>');
    // });

    // $('#add-tag-new').on('click', function() {
    //     event.preventDefault();
    //     console.log("lipka");
    //     $('#new-tags-new').append('<li>'+$('#new-tag-new').val() + '</li>');
    //     debugger;
    // });

    // selectAllViaAjax();
// });

// function selectByIdViaAjax(entry_id) {
//
//     let search = {};
//     search["username"] = "tomko";
//     search["email"] = "tomko@mail.pl";
//
//     $.ajax({
//         type : "GET",
//         contentType : "application/json",
//         url : "http://localhost:8080/rest/entry/"+entry_id,
//         // data : JSON.stringify(search),
//         dataType : 'json',
//         timeout : 100000,
//         success : function(data) {
//             console.log("SUCCESS: ", data);
//             populateSelection(data);
//             takePlaceTo("selection");
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

// function addViaAjax() {
//
//     let new_post = {};
//     new_post["title"] = $('#new-title-new').val();
//     new_post["text"] = $('#new-text-new').val();
//     new_post["tags"] = $('#new-tags-new').tagEditor('getTags')[0].tags;
//     // $('#new-tags-new').children('li').each(function() {
//     //     console.log($(this));
//     //     new_post["tags"].push($(this).text());
//     // });
//
//     console.log(new_post);
//
//     $.ajax({
//         type : "POST",
//         contentType : "application/json",
//         url : "http://localhost:8080/rest/new",
//         data : JSON.stringify(new_post),
//         dataType : 'json',
//         // timeout : 100000,
//         // success : function() {
//         //     console.log("SUCCESS: ");
//         //     // selectAllViaAjax();
//         //     // display(data);
//         // }
//         // error : function(e) {
//         //     console.log("ERROR: ", e);
//         //     // display(e);
//         // },
//         // done : function(e) {
//         //     console.log("DONE");
//         //     // enableSearchButton(true);
//         // }
//     });
//
//     setTimeout(function() {
//         selectAllViaAjax();
//     }, 1000);
//
//
// }

    function addCommentViaAjax(comment, entid) {

        console.log(comment);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://localhost:8080/rest/komentarz/" + entid,
            data: JSON.stringify(comment),
            dataType: 'json',
            timeout: 100000,
            success: function (data) {
                console.log("SUCCESS: ");
                console.log(data);
                location.replace("http://localhost:8080/");
                // selectAllViaAjax();
                // display(data);
            }
        });
    } // error : function(e) {
        //     console.log("ERROR: ", e);
        //     // display(e);
        // },
        // done : function(e) {
        //     console.log("DONE");
        //     // enableSearchButton(true);
        // }
    // });
// }

// function selectAllViaAjax() {
//     allEntries = [];
//     $.ajax({
//         type : "GET",
//         contentType : "application/json",
//         url : "http://localhost:8080/rest/entries/",
//         // data : JSON.stringify(search),
//         dataType : 'json',
//         timeout : 100000,
//         success : function(data) {
//             console.log("SUCCESS: ", data);
//             populateAll(data);
//             // takePlaceTo("selection");
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
// }
//
//
// function deleteViaAjax(entryId) {
//     $.ajax({
//         type : "GET",
//         contentType : "application/json",
//         url : "http://localhost:8080/rest/delete/" + entryId,
//         // data : JSON.stringify(),
//         dataType : 'json',
//         timeout : 100000,
//         success : function(data) {
//             console.log("SUCCESS: ", data);
//             populateAll(data);
//             // takePlaceTo("selection");
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
// }
//
// function populateSelection(data) {
//     console.log(data);
//     // selected["datetime"] = data["datetime"];
//
//     $('#new-title').val(data["title"]);
//     $('#new-text').val(data["text"]);
//     $('#new-tags').tagEditor({initialTags: data["tags"]});
//     // $('#new-tags').empty();
//     // for(let tag of data["tags"]) {
//     //     $('#new-tags').append('<li>' + tag + '</li>')
//     // }
//     // $('#new-tag').val(' ');
//     // $('#existing-date-time').val(selected["datetime"]);
//     // for(let commentary of selected["commentaries"])
//     // $('#existing-commentaries').val(selected["commentaries"]);
// }
//
// function populateAdd() {
//     $('#new-title-new').val("");
//     $('#new-text-new').val("");
//
//     // $('#new-tags').val([]);
//     $('#new-tags-new').tagEditor('destroy');
//     // $('#new-tag-new').val("");
// }
//
// function populateAll(data) {
//     console.log(data);
//     // selected["datetime"] = data["datetime"];
//     for(let elem of data) {
//         allEntries.push(elem);
//     }
//
//     let div=$('#entries-div');
//     div.empty();
//
//     for(let elem of allEntries) {
//         // div.append()
//         // $('entries-div').append('innerHTML('
//         let delhtml = '<button class="do-del" id="del-' + elem["id"] + '">Usuń</button>';
//         let edithtml = '<button class="do-edit" id="edit-' + elem["id"] + '">Edytuj</button>';
//         let html = '<div class="col-md-8" style="padding:3px; font-weight: bold;">'+
//                    '<h4>'+ elem["title"] + '</h4>'+
//                    '</div>'+
//                    '<div class="col-md-4">' +
//                    delhtml +
//                    edithtml +
//                    ' </div> ' +
//                    '<div style="height: 10px;"></div>';
//         div.append(html);
//
//
//         div.find('.do-edit')[0].addEventListener('click', function() {
//             event.preventDefault();
//             selectByIdViaAjax(elem["id"]);
//         });
//
//         div.find('.do-del')[0].addEventListener('click', function () {
//             event.preventDefault();
//             deleteViaAjax(elem["id"]);
//         });
//     }
//
//     displayOff();
// }
//
// function takePlaceTo(type) {
//     if(type==="selection") {
//         $('#new-entry').css('display', 'none');
//         $('#selected-entry').css('display', 'block');
//     }
//     if(type==="add") {
//         $('#new-entry').css('display', 'block');
//         $('#selected-entry').css('display', 'none');
//     }
// }
//
// function displayOff() {
//     $('#new-entry').css('display', 'none');
//     $('#selected-entry').css('display', 'none');
// }