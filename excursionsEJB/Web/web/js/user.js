/**
 * Created by root on 15.05.2016.
 */
$(function () {
    fillExcursionsTable();

    $('#excursions').on('click', '.create-booking', function () {
        var row = $(this).closest('.excursion-row');

        var booking = {
            UserId: 1,
            ExcursionId: $(row).data('id')
        };

        var backEnd = new Services.BackEndService();
        backEnd.createEntity('booking', booking, fillExcursionsTable);
    });
});

function fillExcursionsTable() {
    $('.excursion-row').remove();

    $.getJSON('${pageContext.servletContext.contextPath}/entity?type=excursion&query=get-all', function (excursions) {
        for (var index in excursions) {
            var excursion = excursions[index];


            var row =
                '<tr class="excursion-row" data-id="' + excursion.id + '">' +
                    '<td>' + excursion.guideId + '</td>' +
                    '<td>' + excursion.name + '</td>' +
                    '<td>' + excursion.description + '</td>' +
                    '<td>' + excursion.price + '</td>' +
                    '<td><input class="create-booking" type="button" value="Order" data-func="order"/></td>' +
                '</tr>'
            ;


            $('#excursions tr:last').after(row);
        }
    });

    $.getJSON('${pageContext.servletContext.contextPath}/entity?type=booking&query=get-all', function (bookings) {
        for (var index in bookings) {
            var booking = bookings[index];

            var row = $('.excursion-row[data-id=' + booking.excursionId + ']');
            $(row)
                .css('background-color', '#b3b3b3')
                .css('color', 'white')
            ;
            $(row)
                .find('.create-booking')
                .val('Cancel')
                .attr('disabled', true)
                .data('func', 'cancel')
            ;
        }
    });
}
