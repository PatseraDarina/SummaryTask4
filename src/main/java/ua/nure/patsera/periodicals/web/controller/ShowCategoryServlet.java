package ua.nure.patsera.periodicals.web.controller;

import ua.nure.patsera.periodicals.bean.Category;
import ua.nure.patsera.periodicals.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Дарина on 16.09.2017.
 */
@WebServlet
public class ShowCategoryServlet extends HttpServlet {
    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        categoryService = (CategoryService) getServletContext().getAttribute(ServletAttributes.CATEGORY_SERVICE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request, response);
    }

    private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList = categoryService.getAllCategory();
        request.setAttribute(ServletAttributes.CATEGORY_LIST, categoryList);
        request.getRequestDispatcher(ServletAttributes.JSP_VIEW_PERIODICALS).forward(request, response);
    }
}
