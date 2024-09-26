<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/subnav_admin.jsp" %>

<div class="wrapper">

    <%--SIDEBAR--%>
        <%@ include file="../sidebar.jsp" %>


                <div id="main-wrapper" class="col-sm-10">
                    <div class="col-sm-12">
                            <h2>Vehicle Make List</h2>

            <!-- Success message display -->
<%--            <c:if test="${not empty successMessage}">--%>
<%--                <div class="alert alert-success">--%>
<%--                    ${successMessage}--%>
<%--                </div>--%>
<%--            </c:if>--%>

            <!-- Error message display -->
<%--            <c:if test="${not empty errorMessage}">--%>
<%--                <div class="alert alert-danger">--%>
<%--                    ${errorMessage}--%>
<%--                </div>--%>
<%--            </c:if>--%>

                <a href="<c:url value='/admin/vehicleMake/add'/>" class="btn btn-primary">Add New Vehicle Make</a>

            <%--LIST OF VEHICLE MAKES--%>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>Make</th>
                    <th>Model</th>
                    <th>Create Date</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="vehicleMake" items="${vehicleMakeList}">
                        <tr>
                            <td>${vehicleMake.vehicleMakeName}</td>
                            <td>
                                <c:forEach var="model" items="${vehicleMake.vehicleModelList}">
                                    ${model.modelName}<br/>
                                </c:forEach>
                            </td>
                            <td>${vehicleMake.createDate}</td>
                            <td><a href="/admin/vehicleMake/edit/${vehicleMake.id}" class="btn btn-warning">Edit</a></td>
                            <td><a href="/admin/vehicleMake/delete/${vehicleMake.id}" class="btn btn-danger"
                                onclick="return confirm('Are you sure you want to delete this vehicle make?');">Delete</a>
                            </td>
                        </tr>

                    </c:forEach>

                </tbody>

            </table>

            </div>
        </div>

</div>

<%@include file="../../includes/footer.jsp" %>