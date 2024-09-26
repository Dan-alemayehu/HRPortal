<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/subnav_admin.jsp" %>

<div class="wrapper">

    <%-- SIDEBAR --%>
    <%@include file="vehicle_sidebar.jsp" %>

    <%-- Check if the errorAlert is set to visible --%>
    <c:if test="${errorAlert == 'visible'}">
        <div class="alert alert-danger">
            <strong>Error!</strong> ${errorMessage}
        </div>
    </c:if>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form class="form-horizontal" modelAttribute="vehicle" action="/admin/vehicle/add" method="post">
    <form:hidden path="id" />
    <form:hidden path="version" />
    <form:hidden path="vehicleModel.id" value="${vehicle.vehicleModel.id}" />

    <%--Vehicle Year--%>
    <div class="form-group">
        <label for="inputVehicleYear" class="col-lg-2 control-label">Year</label>
        <div class="col-lg-10">
            <form:input path="year" type="text" class="form-control" id="inputVehicleYear" placeholder="Vehicle Year"></form:input>
        </div>
    </div>

    <%--Vehicle Color--%>
    <div class="form-group">
        <label for="inputVehicleColor" class="col-lg-2 control-label">Color</label>
        <div class="col-lg-10">
            <form:input path="color" type="text" class="form-control" id="inputVehicleColor" placeholder="Vehicle Color"></form:input>
        </div>
    </div>

    <%--License Plate--%>
    <div class="form-group">
        <label for="inputVehicleLicensePlate" class="col-lg-2 control-label">License Plate</label>
        <div class="col-lg-10">
            <form:input path="licensePlate" type="text" class="form-control" id="inputVehicleLicensePlate" placeholder="Vehicle License Plate"></form:input>
        </div>
    </div>

    <%--VIN--%>
    <div class="form-group">
        <label for="inputVehicleVin" class="col-lg-2 control-label">VIN</label>
        <div class="col-lg-10">
            <form:input path="vin" type="text" class="form-control" id="inputVehicleVin" placeholder="Vehicle VIN"></form:input>
        </div>
    </div>

    <%--Purchased?--%>
    <%--                        <div class="form-group">--%>
    <%--                            <label for="isPurchase" class="col-lg-2 control-label">Is Purchase</label>--%>
    <%--                            <div class="col-lg-10">--%>
    <%--                                <form:radiobuttons path="newIsPurchase" id="isPurchase" cssClass="form-check-input" items="${{true:'Yes', false:'No'}}"/>--%>
    <%--                            </div>--%>
    <%--                        </div>--%>

    <%--                        &lt;%&ndash;Purchase Date&ndash;%&gt;--%>
    <%--                    <div class="form-group">--%>
    <%--                        <label for="inputPurchaseDate" class="col-lg-2 control-label">Purchase Date</label>--%>
    <%--                        <div class="col-lg-10">--%>
    <%--                            <form:input path="newPurchaseDate" type="date" class="form-control" id="inputPurchaseDate" placeholder="MM/DD/YYYY"/>--%>
    <%--                        </div>--%>
    <%--                    </div>--%>

    <%--                        &lt;%&ndash;Purchase Price&ndash;%&gt;--%>
    <%--                    <div class="form-group">--%>
    <%--                        <label for="inputVehiclePrice" class="col-lg-2 control-label">Price $</label>--%>
    <%--                        <div class="col-lg-10">--%>
    <%--                            <form:input path="newVehiclePrice" type="text" class="form-control" id="inputVehiclePrice" placeholder="Vehicle Price"></form:input>--%>
    <%--                        </div>--%>
    <%--                    </div>--%>

    <div class="form-group">
        <div class="col-lg-10 col-lg-offset-2">
            <form:button type="reset" value="cancel" class="btn btn-default">Cancel</form:button>
            <form:button type="submit" value="save" class="btn btn-primary">Save</form:button>
        </div>
    </div>
</form:form>
</div>
    <div class="col-sm-4">

        <%--SUCCESS ALERTS--%>
        <div class="${successAlert == null ? 'hidden' : sucessAlert}" id="successAlert">
            <div class="alert alert-dismissible alert-success">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Vehicle added successfully to the database!</strong> You successfully read <a href="#" class="alert-link">this important alert message</a>
            </div>
        </div>

        <%--WARNING ALERT--%>
        <div class="${warningAlert == null ? 'hidden' : warningAlert}" id="warningAlert">
            <div class="alert alert-dismissible alert-warning">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <h4>Be Advised</h4>
                <p>All fields required. Please enter proper vehicle information.</p>
            </div>
        </div>
    </div>

