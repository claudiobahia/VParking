<%-- 
    Document   : updateplaca
    Created on : Sep 27, 2019, 10:02:12 PM
    Author     : claud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update</title>
    </head>
    <body>
        <div align="center">
            <br><br>
            <h2>Edite a placa</h2>
            <form method="post" action="ServletUpadatePlaca">
                <table border ="1" class="table table-striped table-bordered"  style="width: auto">
                    <tr>
                        <th>Placa</th>
                        <th>${placaToUpdate}</th>
                        <th>
                            <input type="text" name="edtPlaca" value="${placaToUpdate}" hidden="true"/>
                            <input type="text" name="edtPlacaUpdated"/>
                        </th>
                    </tr>
                </table>
                <input type="submit"/>
            </form>
        </div>    
    </body>
</html>
