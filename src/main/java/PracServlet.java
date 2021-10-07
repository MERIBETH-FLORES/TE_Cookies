import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "PracServlet", urlPatterns = {"/PracServlet"})
public class PracServlet extends HttpServlet {
 @Override
 protected void doGet(HttpServletRequest request, HttpServletResponse response)
 throws ServletException, IOException {
        String valor=null;
        String visita=null;
        String mensajes = null;
        boolean nuevavisita=true;       
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("visitante")) {
                nuevavisita = false;
                valor=cookies[i].getValue();
                break;
                }
            }
        }
        if (nuevavisita) {
            Cookie cooki = new Cookie("visitante","1");
            cooki.setMaxAge(30);       
            cooki.setComment("Control de nuevos visitantes");

            response.addCookie(cooki);
            mensajes="Gracias por visitarnos";
            visita="N° de Visitas: 1";
        }
        else{
            int contador = Integer.parseInt(valor);
            contador++;
            Cookie ck = new Cookie("visitante",""+contador);
            ck.setMaxAge(30);
            response.addCookie(ck);
            mensajes="Gracias por la visita";
            visita="N° de Visitas: "+contador;
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h1><FONT COLOR=\"teal\">"+mensajes+"</font></h1>");
        out.println("<h3><FONT COLOR=\"green\">"+visita+"</font></h3>");
        out.println("<a href='index.jsp'>Ir a la pagina de inicio?</a>");
        }
}