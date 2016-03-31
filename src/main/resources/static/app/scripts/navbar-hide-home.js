/**
 * Created by Stefan on 31.03.2016.
 */
var timer;

$(window).on('mousemove', function () {
    $('#navbar').addClass('show');
    try {
        clearTimeout(timer);
    } catch (e) {}
    timer = setTimeout(function () {
        $('#navbar').removeClass('show');
    }, 5000);
});