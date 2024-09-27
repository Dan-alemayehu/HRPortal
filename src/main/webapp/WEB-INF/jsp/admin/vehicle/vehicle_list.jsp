<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/subnav_admin.jsp" %>

<div class="wrapper">

    <%-- SIDEBAR --%>
    <%@include file="../sidebar.jsp" %>

    <div id="main-wrapper" class="col-sm-10">
        <div class="col-sm-12">

            <form action="/admin/vehicle/search" method="GET">
                <label for="model">Search by Model:</label>
                <input type="text" id="model" name="modelName" placeholder="Enter model name"/>
                <button type="submit">Search</button>
            </form>

            <form action="/admin/vehicle/searchByMake" method="GET">
                <label for="make">Search by Make:</label>
                <input type="text" id="make" name="makeName" placeholder="Enter make name"/>
                <button type="submit">Search</button>
            </form>


        <%-- LIST OF VEHICLE --%>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>Year</th>
                        <th>Color</th>
                        <th>Make</th>
                        <th>Model</th>
                        <th>Price</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="vehicle" items="${vehicles}">
                        <tr>
                            <td>${vehicle.year}</td>
                            <td>${vehicle.color}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${vehicle.vehicleModel != null && vehicle.vehicleModel.vehicleMake != null}">
                                        ${vehicle.vehicleModel.vehicleMake.vehicleMakeName}
                                    </c:when>
                                    <c:otherwise>
                                        <!-- Display empty or default value -->
                                        N/A
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${vehicle.vehicleModel != null}">
                                        ${vehicle.vehicleModel.modelName}
                                    </c:when>
                                    <c:otherwise>
                                        <!-- Display empty or default value -->
                                        N/A
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${vehicle.purchasePrice}</td>
                            <td><a href="/admin/vehicle/edit/${vehicle.id}">Edit</a></td>
                            <td><a href="/admin/vehicle/delete/${vehicle.id}" class="btn btn-danger"
                                onclick="return confirm('Are you sure you want to delete this vehicle?');">Delete</a>
                            </td>
<%--                            <td>--%>
<%--                                <form action="${pageContext.request.contextPath}/admin/vehicle/delete/${vehicle.id}" method="post" style="display:inline;">--%>
<%--                                    <button type="submit" onclick="return confirm('Are you sure you want to delete this vehicle?');">Delete</button>--%>
<%--                                </form>--%>
<%--                            </td>--%>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>

        </div>
    </div>

</div>

<%@include file="../../includes/footer.jsp" %>
