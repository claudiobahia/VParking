<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>VParking - Dashboard</title>

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    </head>

    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion toggled" id="accordionSidebar">

                <!-- Sidebar - Brand -->
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="redirect.jsp">
                    <div class="sidebar-brand-icon rotate-n-15">
                        <i class="fas fa-laugh-wink"></i>
                    </div>
                    <div class="sidebar-brand-text mx-3">VParking</div>
                </a>

                <!-- Divider -->
                <hr class="sidebar-divider my-0">

                <!-- Nav Item - Dashboard -->
                <!--                <li class="nav-item active">
                                    <a class="nav-link" href="index.jsp">
                                        <i class="fas fa-fw fa-tachometer-alt"></i>
                                        <span>Painel</span></a>
                                </li>-->

                <li class="nav-item active">
                    <a class="nav-link" href="#" data-toggle="modal" data-target="#modalAdicionarPlaca">
                        <i class="fas fa-fw fa-laugh-wink"></i>
                        <span>Adicionar</span></a>
                </li>

                <li class="nav-item active">
                    <a class="nav-link" href="#" data-toggle="modal" data-target="#modalFinalizarPlaca">
                        <i class="fas fa-fw fa-laugh-wink"></i>
                        <span>Finalizar</span></a>
                </li>


                <!--Adicionar-->
                <div class="modal" id="modalAdicionarPlaca">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <div align="center" class="modal-body">
                                    <h2>Adicione um novo veículo</h2>
                                    <h3>Placa do veículo</h3>
                                    <form action="ServletAdicionarPlaca" method="POST">
                                        <input type="text" name="edtPlacaAdicionar"/>
                                        <br><br>
                                        <input type="submit" name="btnEnviarPlacaAdicionar"/>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!--Finalizar-->
                <div class="modal" id="modalFinalizarPlaca">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <div align="center" class="modal-body">
                                    <h2>Finalize o serviço</h2>
                                    <h3>Placa do veículo</h3>
                                    <form action="ServletFinalizarPlaca" method="POST">
                                        <input type="text" name="edtPlacaFinalizar"/>
                                        <br><br>
                                        <input type="submit" name="btnEnviarPlacaFinalizar"/>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Divider -->
                <hr class="sidebar-divider d-none d-md-block">

                <!-- Sidebar Toggler (Sidebar) -->

            </ul>
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                        <!-- Sidebar Toggle (Topbar) -->
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>

                        <!-- Topbar Navbar -->
                        <ul class="navbar-nav ml-auto">

                            <div class="topbar-divider d-none d-sm-block"></div>

                        </ul>

                    </nav>
                    <!-- End of Topbar -->

                    <!-- Begin Page Content -->
                    <div class="container-fluid">
                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h1 class="m-0 font-weight-bold text-primary">Veiculos Estacionados</h1>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Placa do Veículo</th>
                                                <th>Número da Vaga</th>
                                                <th>Data/Hora da Entrada</th>
                                                <th> </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${listaVeiculo}" var="veiculo">
                                                <tr>
                                                    <td>${veiculo.placa}</td>
                                                    <td>${veiculo.vaga}</td>
                                                    <td>${veiculo.data}</td>
                                                    <td>
                                                        <form method="post" action="ServletPreUpdate">
                                                            <input name="placa" value="${veiculo.placa}" hidden="true"/>
                                                            <input type="submit" value="Editar"/>
                                                        </form>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /.container-fluid -->

                </div>
                <!-- End of Main Content -->

                <!-- Footer -->
                <footer class="sticky-footer bg-white">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">
                            <span>Copyright &copy; Your Website 2019</span>
                        </div>
                    </div>
                </footer>
                <!-- End of Footer -->

            </div>
            <!-- End of Content Wrapper -->

        </div>
        <!-- End of Page Wrapper -->

        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>

        <!-- Page level plugins -->
        <script src="vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="js/demo/datatables-demo.js"></script>

    </body>

</html>