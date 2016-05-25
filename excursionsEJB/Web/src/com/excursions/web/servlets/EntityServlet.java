package com.excursions.web.servlets;


import com.excursions.beans.session.CrudSessionBean;
import com.excursions.web.exceptions.UnknownEntityTypeException;
import com.excursions.web.models.Result;
import com.excursions.web.models.PostData;
import com.excursions.web.util.EntityHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.IOUtils;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;


public class EntityServlet extends HttpServlet {
    @EJB
    private CrudSessionBean entities;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();


        String entity = request.getParameter("type");
        if (entity == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "specify 'type' parameter");
        }

        String query = request.getParameter("query");
        if (query == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "specify 'query' parameter");
        }

        List resultList = null;
        try {
            resultList = entities.findWithNamedQuery(
                String.format("%s-%s", entity, query)
            );
        }
        catch (Exception e) {
            out.print(String.format(
                "{ class: \"%s\", message: \"%s\", cause: \"%s\"}",
                e.getClass(),
                e.getMessage(),
                e.getCause()
            ));
            return;
        }


        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(resultList);

        out.print(json);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        Result result = null;
        try {

            String body = IOUtils.toString(
                request.getInputStream(),
                Charset.forName("utf-8")
            );
            Gson gson = new GsonBuilder().create();
//            PostData model = gson.fromJson(body, PostData.class);
//
//            Map properties = (Map)model.getEntity();
//            String userId = properties.get("UserId").toString();
//            String excursionId = properties.get("ExcursionId").toString();
//
//            out.print(String.format(
//                "{ \"body\": \"%s\", \"userId\": \"%s\", \"excursionId\": \"%s\" }",
//                body,
//                userId,
//                excursionId
//            ));

            result = EntityHelper.createEntity(
                entities,
                gson.fromJson(body, PostData.class)
            );

            out.print(
                gson.toJson(result)
            );

        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
        catch (UnknownEntityTypeException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }
}
