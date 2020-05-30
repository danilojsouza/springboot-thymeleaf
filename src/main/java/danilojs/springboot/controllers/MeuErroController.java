package danilojs.springboot.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MeuErroController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
    	Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    	Object excessao = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
    	ModelAndView resultado = new ModelAndView("erro");
    	resultado.addObject("status", status.toString());
    	resultado.addObject("excessao", excessao);
        return resultado;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}