<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/subnav_admin.jsp" %>


<script>
    $(document).ready(function() {

        $("#successAlert").delay(8000).fadeOut(2000);
        $("#warningAlert").delay(8000).fadeOut(2000);

    })
</script>

<div class="wrapper">

    <%--SIDEBAR--%>
        <%@ include file="../sidebar.jsp" %>


        <div id="main-wrapper" class="col-md-10">
        <div class="col-lg-8">

            <!-- Display warning if it is set in the model -->
    <c:if test="${warningAlert == 'visible'}">
        <div style="color: red;">
            ${warningMessage}
        </div>
    </c:if>

    <!-- Display success message if it is set in the model -->
    <c:if test="${successAlert == 'visible'}">
        <div style="color: green;">
            Vehicle added successfully!
        </div>
    </c:if>

            <form:form cssClass="form-horizontal" modelAttribute="vehicleMakeVO" action="/admin/vehicleMake/add" method="post">
                <fieldset>
                    <legend>Add a Vehicle</legend>

                    <%--Vehicle Make--%>
                    <div class="form-group">
                        <label for="inputVehicleMake" class="col-lg-2 control-label">Make</label>
                        <div class="col-lg-10">
                            <form:input path="newMakeName" type="text" class="form-control" id="inputVehicleMake" placeholder="Vehicle Make Name"></form:input>
                        </div>
                    </div>

                        <div class="form-group">
                            <div class="col-lg-10 col-lg-offset-2">
                                <form:button type="reset" value="cancel" class="btn btn-default">Cancel</form:button>
                                <form:button type="submit" value="save" class="btn btn-primary">Save</form:button>
                            </div>
                        </div>
                </fieldset>
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

<%@include file="../../includes/footer.jsp" %>html>