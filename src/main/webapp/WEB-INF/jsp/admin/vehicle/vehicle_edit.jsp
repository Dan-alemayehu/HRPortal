<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/subnav_admin.jsp" %>

<div class="wrapper">

    <%-- SIDEBAR --%>
    <%@include file="vehicle_sidebar.jsp" %>

    <c:if test="${errorAlert == 'visible'}">
        <div class="alert alert-danger">
            <strong>Error!</strong> ${errorMessage}
        </div>
    </c:if>

    <div id="main-wrapper" class="col-sm-10">
        <div class="col-sm-8">

            <form:form class="form-horizontal" modelAttribute="vehicle" action="${pageContext.request.contextPath}/admin/vehicle/edit" method="post">
                <form:hidden path="id" />
                <form:hidden path="version" />
                <form:hidden path="vehicleModel.id" value="${vehicleModel.id}" />
                <form:hidden path="vehicleModel.vehicleMake.id" value="${vehicleModel.vehicleMake.id}" />

<%--                <div class="row">--%>
<%--                    <div class="form-group">--%>
<%--                        <label for="vehicleModel.id" class="col-sm-3 control-label">Vehicle Model Id</label>--%>
<%--                        <div class="col-sm-7">--%>
<%--                            <form:input path="vehicleModel.id" type="text" id="vehicleModel.id" cssClass="form-control"></form:input>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--                <div class="row">--%>
<%--                    <div class="form-group">--%>
<%--                        <label for="vehicleModel.vehicleMake.id" class="col-sm-3 control-label">Vehicle Make ID</label>--%>
<%--                        <div class="col-sm-7">--%>
<%--                            <form:input path="vehicleModel.vehicleMake.id" type="text" id="vehicleModel.vehicleMake.id" cssClass="form-control"></form:input>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>

                <div class="row">
                    <div class="form-group">
                        <label for="year" class="col-sm-3 control-label">Vehicle Year</label>
                        <div class="col-sm-7">
                            <form:input path="year" type="text" id="year" cssClass="form-control"></form:input>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label for="color" class="col-sm-3 control-label">Vehicle Color</label>
                        <div class="col-sm-7">
                            <form:input path="color" type="text" id="color" cssClass="form-control"></form:input>
                        </div>
                    </div>
                </div>

                    <%--License Plate--%>
                <div class="form-group">
                    <label for="licensePlate" class="col-lg-2 control-label">License Plate</label>
                    <div class="col-lg-10">
                        <form:input path="licensePlate" type="text" class="form-control" id="licensePlate" placeholder="Vehicle License Plate"></form:input>
                    </div>
                </div>

                <%--VIN--%>
                <div class="form-group">
                    <label for="vin" class="col-lg-2 control-label">VIN</label>
                    <div class="col-lg-10">
                        <form:input path="vin" type="text" class="form-control" id="vin" placeholder="Vehicle VIN"></form:input>
                    </div>
                </div>

                <div class="row">
                    <button type="submit">Save</button>
                </div>

            </form:form>

        </div>
    </div>

</div>

<%@include file="../../includes/footer.jsp" %>
