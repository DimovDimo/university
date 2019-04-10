package org.softuni.university.web.interceptors;

import org.softuni.university.web.annotations.PageTitle;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TitleInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String title = "University";

        if (modelAndView == null) {
            modelAndView = new ModelAndView();
        } else {
            if (handler instanceof HandlerMethod) {
                PageTitle methodAnnotation = ((HandlerMethod) handler).getMethodAnnotation(PageTitle.class);

                if (methodAnnotation != null) {
                    modelAndView
                            .addObject("titleText", title + " - " + methodAnnotation.value());
                }
            }
        }
    }

    private String getTitle(ModelAndView modelAndView) {
        String title = modelAndView.getViewName();
        title = title.substring(0, 1).toUpperCase() + title.substring(1);
        title = title.replace("-", " ");
        title = title.replace("/", " ");

        return title;
    }
}
