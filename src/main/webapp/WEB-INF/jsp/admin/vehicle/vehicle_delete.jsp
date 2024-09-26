<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/subnav_admin.jsp" %>

<div class="wrapper">

    <%--SIDEBAR--%>
    <%@include file="vehicle_sidebar.jsp" %>

    <div id="main-wrapper" class="col-sm-10">
        <div class="col-sm-12">

            <h1>Delete Vehicle</h1>
            <p>Are you sure you want to delete this vehicle?</p>

            <!-- Display the vehicle details for confirmation -->
            <ul>
                <li>Make: ${vehicle.vehicleModel.vehicleMake.vehicleMakeName}</li>
                <li>Model: ${vehicle.vehicleModel.modelName}</li>
                <li>Year: ${vehicle.year}</li>
                <li>Color: ${vehicle.color}</li>
                <li>License Plate: ${vehicle.licensePlate}</li>
            </ul>

            <form:form action="${pageContext.request.contextPath}/admin/vehicle/delete" method="post">
                <!-- Hidden field for the vehicle id -->
                <form:hidden path="id" />

                <!-- Confirmation buttons -->
                <button type="submit" class="btn btn-danger">Delete</button>
                <a href="${pageContext.request.contextPath}/admin/vehicle" class="btn btn-default">Cancel</a>
            </form:form>

        </div>
    </div>

</div>

<%@include file="../../includes/footer.jsp" %>
