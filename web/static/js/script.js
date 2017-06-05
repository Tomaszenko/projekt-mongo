/**
 * Created by Tomek on 05.06.2017.
 */

$(".search-field").find("input").live( "keydown", function (evt) {
    var stroke;
    stroke = (_ref = evt.which) != null ? _ref : evt.keyCode;
    if (stroke == 9) { // 9 = tab key
        $('#tags').append('<option value="' + $(this).val() + '" selected="selected">' + $(this).val() + '</option>');
        $('#tags').trigger('chosen:updated');
    }
});