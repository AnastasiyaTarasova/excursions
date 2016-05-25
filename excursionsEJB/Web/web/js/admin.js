/**
 * Created by root on 15.05.2016.
 */
$(function () {
    fillUsersTable();

    $('#create-user').click(function () {
        var user = {
            UserName: $('#new-user [name=UserName]').val(),
            FirstName: $('#new-user [name=FirstName]').val(),
            LastName: $('#new-user [name=LastName]').val(),
            Password: $('#new-user [name=Password]').val(),
            UserType: $('#new-user [name=UserType]').val()
        };

        var backEnd = new Services.BackEndService();
        backEnd.createEntity('user', user, fillUsersTable);
    });
});

function fillUsersTable() {
    $('.user-row').remove();

    $.getJSON('${pageContext.servletContext.contextPath}/entity?type=user&query=get-all', function (users) {
        for (var index in users) {
            var user = users[index];
            var role = '';
            switch (user.userType) {
                case 0: role = 'Admin';
                    break;
                case 1: role = 'Guide';
                    break;
                case 2: role = 'User';
                    break;
                default: role = 'Unknown'
            }


            var row =
                '<tr class="user-row">' +
                    '<td>' + user.userName + '</td>' +
                    '<td>' + user.firstName + '</td>' +
                    '<td>' + user.lastName + '</td>' +
                    '<td>' + role + '</td>' +
                '</tr>'
            ;


            $('#users tr:last').after(row);
        }
    });
}
