/**
 * Created by root on 15.05.2016.
 */
var Services = Services || {};

Services.BackEndService = function () {
    var _this = this;

    this.createEntity = function (entityType, entity, callback) {
        var data = {
            entityType: entityType,
            entity: entity
        };
        $.ajax({
            type: 'POST',
            url: '${pageContext.servletContext.contextPath}/entity',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(data),
            success: function (response) {
                if (response.success) {
                    if (callback !== undefined) {
                        callback();
                    }
                }
            },
            error: function(jqXhr, textStatus, errorThrown) {
                console.log('Debug: ajax error');
                console.log(jqXhr);
                console.log('textStatus: ' + textStatus);
                console.log('errorThrown: ' + errorThrown);
            }
        });
    }
};