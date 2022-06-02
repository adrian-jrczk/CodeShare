package codeshare.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            model.addAttribute("statusCode", Integer.parseInt(status.toString()));
        }
        Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        if (message != null) {
            try {
                Exception exception = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
                model.addAttribute("message", exception.getMessage());
            } catch (ClassCastException ignore) {}
        }
        return "error";
    }
}
