<%@ page import="com.example.lab9_base.Bean.Arbitro" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ArrayList<Arbitro> listaArbitro = (ArrayList<Arbitro>) request.getAttribute("listaArbitro") ;%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'/>
        <title>LAB 9</title>
    </head>
    <body>
        <div class='container'>
            <nav class="navbar navbar-expand-md navbar-light bg-light">
                <a class="navbar-brand" href="#">Clasificatorias Sudamericanas Qatar 2022</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
                    <ul class="navbar-nav">
                        <li class="nav-item" >
                            <a class="nav-link" href="<%=request.getContextPath()%>/PartidoServlet">Partidos</a>
                        </li>
                        <li class="nav-item" >
                            <a class="nav-link" href="<%=request.getContextPath()%>/ArbitroServlet">Arbitros</a>
                        </li>
                    </ul>
                </div>
            </nav>
            <div class="row mb-5 mt-4">
                <div class="col-lg-6">
                    <h1 class=''>Lista de Árbitros</h1>
                </div>
                <div class="col-lg-6 my-auto text-lg-right">
                    <a href="<%= request.getContextPath()%>/ArbitroServlet?action=crear" class="btn btn-primary">Crear
                        Árbitro</a>
                </div>
                <form method="post" action="<%= request.getContextPath()%>/ArbitroServlet?action=buscar" class="row">
                    <div class="col-lg-3">
                        <select name="tipo" class="form-control">
                            <%--                    ACA DEBE COLOCAR LA LISTA DE OPCIONES MOSTRADAS EN EL SERVLET--%>
                            <option>
                                nombre
                            </option>
                            <option>
                                pais
                            </option>
                        </select>
                    </div>
                    <div class="col-lg-5">
                        <input type="text" class="form-control" name="buscar">
                    </div>
                    <div class="col-lg-2">
                        <button type="submit" class="btn btn-primary">Buscar</button>
                    </div>
                    <div class="col-lg-2">
                        <a href="<%= request.getContextPath()%>/ArbitroServlet" class="btn btn-danger">Limpiar
                            Búsqueda</a>
                    </div>
                </form>
            </div>
            <table class="table">
                <tr>
                    <th>#</th>
                    <th>Nombre</th>
                    <th>Pais</th>
                    <th></th>
                </tr>
                <%int i = 1;
                    for (Arbitro arbitro: listaArbitro){%>
                <tr>
                    <td><%=arbitro.getIdArbitro()%></td>
                    <td><%=arbitro.getNombre()%></td>
                    <td><%=arbitro.getPais()%></td>
                    <td>
                        <a href="<%=request.getContextPath()%>/ArbitroServlet?action=borrar&id=">
                            Borrar
                        </a>
                    </td>
                </tr>
                <%}%>
            </table>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
                crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
                integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
                crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
                crossorigin="anonymous"></script>
    </body>
</html>
