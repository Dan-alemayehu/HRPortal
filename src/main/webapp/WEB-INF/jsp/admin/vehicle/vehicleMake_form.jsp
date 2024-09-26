<%@include file="../../includes/header.jsp" %>
<%@include file="../../includes/navbar.jsp" %>
<%@include file="../../includes/subnav_admin.jsp" %>

<div class="container">
    <h2>${vehicleMake.id == null ? 'Add New Vehicle Make' : 'Edit Vehicle Make'}</h2>

    <!-- Error message display -->
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">
            ${errorMessage}
        </div>
    </c:if>

    <form:form modelAttribute="vehicleMake" method="post" class="form-horizontal">
        <form:hidden path="id" />
        <form:hidden path="version" />

        <div class="form-group">
            <label for="vehicleMakeName" class="col-sm-2 control-label">Vehicle Make Name</label>
            <div class="col-sm-8">
                <form:input path="vehicleMakeName" id="vehicleMakeName" class="form-control" />
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-8">
                <button type="submit" class="btn btn-primary">${vehicleMake.id == null ? 'Add Vehicle Make' : 'Update Vehicle Make'}</button>
                <a href="<c:url value='/admin/vehicleMake/list'/>" class="btn btn-default">Cancel</a>
            </div>
        </div>
    </form:form>
</div>

<%@include file="../../includes/footer.jsp" %>
