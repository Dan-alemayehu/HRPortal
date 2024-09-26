<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/subnav_admin.jsp" %>

<script>

    $(document).ready(function() {
        //attach an onclick function to the remove buttons
        $('.remove_button').click(function() {
            //log name button, and contents of associated text box
            console.log(this.name);
            console.log($('#' + this.name).val())
            //clear the value
            $('#' + this.name).val('');
            //submit the form
            $('#vehicleMake').submit();
        });
    });

</script>

<div class="wrapper">

    <%--SIDEBAR--%>
<%@include file="../sidebar.jsp" %>

        <div id="main-wrapper" class="col-sm-10">
            <div class="col-sm-8">

                <c:set var="idx" value="0" scope="page" />
                <%--@elvariable id="vehicleMakeVO" type="aj"--%>
                <form:form class="form-horizontal" modelAttribute="vehicleMake" action="/admin/vehicleMake/edit" method="post">
                    <form:hidden path="id" />
                    <form:hidden path="version" />

                    <div class="row">
                        <div class="form-group">
                            <label for="inputVehicleMake" class="col-sm-2 control-label">Vehicle Make</label>
                            <div class="col-sm-8">
                                <form:input path="vehicleMakeName" type="text" id="inputVehicleMake" cssClass="form-control"></form:input>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-10">
                            <hr>
                        </div>
                    </div>

                    <c:forEach items="${vehicleMake.vehicleModelList}" var="vehicleModel">
                        <form:hidden path="vehicleModelList[${idx}].id" />
                        <form:hidden path="vehicleModelList[${idx}].version" />
                        <div class="row">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="${idx}">Model</label>
                                <div class="col-sm-7">
                                    <div class="input-group">
                                        <form:input path="vehicleModelList[${idx}].modelName" id="${idx}" cssClass="form-control" />
                                            <span class="input-group-btn">
                                                <a href="/admin/vehicle/add/${vehicleModel.id}" class="btn btn-primary" role="button">Add Vehicle</a>
                                            </span>
                                            <span class="input-group-btn">
                                                <a href="/admin/vehicleModel/delete/${vehicleModel.id}" class="btn btn-danger"
                                                onclick="return confirm('Are you sure you want to delete this vehicle Model?');">Delete</a>
                                            </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <c:set var="idx" value="${idx + 1}" scope="page" />
                    </c:forEach>

                        <div class="row">
                            <button name="inputNewVehicleModel" class="btn btn-primary">Update</button>
                        </div>
                    </form:form>

                    <form:form action="/admin/vehicleMake/addModel" method="post" id="addModelForm">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="inputNewModel">Add New Model</label>
                            <div class="col-sm-7">
                                    <input type="hidden" name="vehicleMakeId" value="${vehicleMake.id}">
                                    <input type="text" class="form-control" name="newModelName" id="inputNewModel"/>
                            </div>
                            <div class="col-sm-2">
                                <button type="submit" class="btn btn-primary">Add</button>
                            </div>
                        </div>
                    </div>
                </form:form>

            </div>

            <div class="col-sm-4">

                <%--SUCCESS ALERTS--%>
                <div class="${successAlert == null ? 'hidden' : sucessAlert}" id="successAlert">
                    <div class="alert alert-dismissible alert-success">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <strong>Well done!</strong> You successfully read <a href="#" class="alert-link">this important alert message</a>
                    </div>
                </div>

                <%--WARNING ALERT--%>
                    <div class="${warningAlert == null ? 'hidden' : warningAlert}" id="warningAlert">
                        <div class="alert alert-dismissible alert-warning">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <h4>Warning!</h4>
                            <p>Best check yo self, you're not looking too good. Nulla vitae elit libro, a phareta ague. Present commodo cursus magna</p>
                        </div>
                    </div>

                <%--ERROR ALERT--%>
                    <div class="${errorAlert == null ? 'hidden' : errorAlert}" id="errorAlert">
                        <div class="alert alert-dismissible alert-danger">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <strong>Oh snap!</strong><a href="#" class="alert-link">change up a few things</a> and try again.
                        </div>
                    </div>

            </div>
        </div>

</div>

<%@include file="../../includes/footer.jsp" %>