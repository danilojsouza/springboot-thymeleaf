package danilojs.springboot.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MeuErroController implements ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        return String.format("<html xmlns:th='http://www.thymeleaf.org' lang='pt-br'>"
        		+ "<head>"+ 
	        		"   <meta charset='UTF-8' />" + 
	        		"	<title>Erro na ação</title>" + 
	        		"  	<meta name='viewport' content='width=device-width, initial-scale=1'/>" + 
	        		"  	<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css'/>" + 
	        		"  	<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js'></script>" + 
	        		"  	<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js'></script>" +
	        		"   <style>body {background-color: #666666; color: white;}</style>"
        		+ "</head>"
        		+ "<body>"
        		+ "<div th:replace='../../resources/templates/fragmentos/fragmentos :: header'></div>"
        		+ "<h2 align='center'>Página de erro</h2>"
        		+ "<div>" +
        			"Status: <b>%s</b>"
        		+ "</div>"
                + "<div>"
                	+ "Mensagem para o desenvolvedor: <b>%s</b>"
                + "</div>"
                + "<div th:replace='../../resources/templates/fragmentos/fragmentos :: footer'></div>"
                + "<body></html>",
                statusCode, exception==null ? "Não possui Exception." : exception.getMessage());
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}