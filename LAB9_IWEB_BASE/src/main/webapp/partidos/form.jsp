<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab9_base.Bean.Seleccion" %>
<%@ page import="com.example.lab9_base.Bean.Arbitro" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Seleccion> listaSelecciones = (ArrayList<Seleccion>) request.getAttribute("listaSelecciones");
    ArrayList<Arbitro> listaArbitros = (ArrayList<Arbitro>) request.getAttribute("listaArbitros");
%>
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
            <div class="row mb-4">
                <div class="col"></div>
                <div class="col-md-6">
                    <h1 class='mb-3'>Crear un Partido de Clasificatorias</h1>
                    <form class="needs-validation" method="POST" action="<%=request.getContextPath()%>/PartidoServlet?action=guardar" novalidate>
                        <div class="form-group">
                            <label>Jornada</label>
                            <input type="number" class="form-control" name="jornada" required>
                            <div class="invalid-feedback">Ingrese una jornada</div>
                        </div>
                        <div class="form-group">
                            <label>Fecha</label>
                            <input class="form-control datetimepicker" id="fecha" name="fecha"
                                   type="date" required>
                            <div class="invalid-feedback">Seleccione una fecha</div>
                        </div>
                        <div class="form-group">
                            <label>Selección local</label>
                            <select name="local" class="form-control" required>
                                <option selected disabled value="">Escoga una selección</option>
                                <%for (Seleccion s: listaSelecciones){%>
                                <option value="<%=s.getIdSeleccion()%>"><%=s.getNombre()%></option>
                                <%}%>
                            </select>
                            <div class="invalid-feedback">Seleccione un equipo</div>
                        </div>
                        <div class="form-group">
                            <label>Selección Visitante</label>
                            <select name="visitante" class="form-control" required>
                                <option selected disabled value="">Escoga una selección</option>
                                <%for (Seleccion s: listaSelecciones){%>
                                <option value="<%=s.getIdSeleccion()%>"><%=s.getNombre()%></option>
                                <%}%>
                            </select>
                            <div class="invalid-feedback">Seleccione un equipo</div>
                        </div>
                        <div class="form-group">
                            <label>Árbitro</label>
                            <select name="arbitro" class="form-control" required>
                                <option selected disabled value="">Escoga un árbitro</option>
                                <%for (Arbitro a: listaArbitros){%>
                                <option value="<%=a.getIdArbitro()%>"><%=a.getNombre()%></option>
                                <%}%>
                            </select>
                            <div class="invalid-feedback">Seleccione un árbitro</div>
                        </div>
                        <button type="submit" class="btn btn-primary">Guardar</button>
                        <a href="<%=request.getContextPath()%>/PartidoServlet" class="btn btn-danger">Cancelar</a>
                    </form>
                </div>
                <div class="col"></div>
            </div>
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

        <script>
            // Example starter JavaScript for disabling form submissions if there are invalid fields
            (function () {
                'use strict'

                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.querySelectorAll('.needs-validation')

                // Loop over them and prevent submission
                Array.prototype.slice.call(forms)
                    .forEach(function (form) {
                        form.addEventListener('submit', function (event) {
                            if (!form.checkValidity()) {
                                event.preventDefault()
                                event.stopPropagation()
                            }

                            form.classList.add('was-validated')
                        }, false)
                    })
            })()
        </script>
    </body>
</html>
