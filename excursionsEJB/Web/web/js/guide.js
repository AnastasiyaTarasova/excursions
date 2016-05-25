/**
 * Created by root on 16.05.2016.
 */
var currentGuideId = 5;

$(function () {
    fillExcursionsTable();

    $('#create-excursion').click(function () {
        var excursion = {
            GuideId: currentGuideId,
            Name: $('#new-excursion [name=Name]').val(),
            Description: $('#new-excursion [name=Description]').val(),
            Price: $('#new-excursion [name=Price]').val()
        };

        var backEnd = new Services.BackEndService();
        backEnd.createEntity('excursion', excursion, fillExcursionsTable);
    });
});

function fillExcursionsTable() {
    $('.excursion-row').remove();

    $.getJSON('${pageContext.servletContext.contextPath}/entity?type=excursion&query=get-all', function (excursions) {
        for (var index in excursions) {
            var excursion = excursions[index];
            if (excursion.guideId != currentGuideId) {
                continue;
            }

            var row =
                '<tr class="excursion-row" data-id="' + excursion.id + '">' +
                    '<td>' + excursion.name + '</td>' +
                    '<td>' + excursion.description + '</td>' +
                    '<td>' + excursion.price + '</td>' +
                    '<td class="people">0</td>' +
                '</tr>'
            ;


            $('#excursions tr:last').after(row);
        }
    });

    $.getJSON('${pageContext.servletContext.contextPath}/entity?type=booking&query=get-all', function (bookings) {
        for (var index in bookings) {
            var booking = bookings[index];

            var row = $('.excursion-row[data-id=' + booking.excursionId + ']');
            if (row.length == 0) {
                continue;
            }

            var td = $(row).find('.people');

            var count = parseInt($(td).html());

            $(td).html(count + 1);
        }
    });
}
