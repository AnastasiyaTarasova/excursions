package com.excursions.web.util;

import com.excursions.beans.entities.Booking;
import com.excursions.beans.entities.Excursion;
import com.excursions.beans.entities.User;
import com.excursions.beans.session.CrudSessionBean;
import com.excursions.web.exceptions.UnknownEntityTypeException;
import com.excursions.web.models.PostData;
import com.excursions.web.models.Result;

import java.util.Map;
import java.util.Objects;

/**
 * All the SHIT is here
 *
 * Created by root on 15.05.2016.
 */
public class EntityHelper {
    public static Result createEntity(CrudSessionBean entities, PostData model) throws UnknownEntityTypeException {
        Map properties = (Map)model.getEntity();

        if (Objects.equals(model.getEntityType(), "user")) {
            User user = new User(
                properties.get("UserName").toString(),
                properties.get("FirstName").toString(),
                properties.get("LastName").toString(),
                properties.get("Password").toString(),
                (int) Double.parseDouble(properties.get("UserType").toString())
            );
            User addedUser = entities.create(user);
            return new Result(addedUser.getId(), true);
        }

        if (Objects.equals(model.getEntityType(), "booking")) {
            Booking booking = new Booking(
                (int) Double.parseDouble(properties.get("UserId").toString()),
                (int) Double.parseDouble(properties.get("ExcursionId").toString())
            );
            Booking addedBooking = entities.create(booking);
            return new Result(addedBooking.getId(), true);
        }

        if (Objects.equals(model.getEntityType(), "excursion")) {
            Excursion excursion = new Excursion(
                (int) Double.parseDouble(properties.get("GuideId").toString()),
                (int) Double.parseDouble(properties.get("Price").toString()),
                properties.get("Name").toString(),
                properties.get("Description").toString()
            );
            Excursion addedExcursion = entities.create(excursion);
            return new Result(addedExcursion.getId(), true);
        }

        throw new UnknownEntityTypeException("Unknown entity type: " + model.getEntityType());
    }
}
