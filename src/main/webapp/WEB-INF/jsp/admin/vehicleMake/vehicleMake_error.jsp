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
                <h1>WOOPSIES</h1>
                <h2>You've hit an error page!</h2>
                <h3>There is already a vehicle make/model with the same name. Go Back or redirect through
                    the navigation menus and try again.</h3>
                <h2>Make sure you use a different name this time!</h2>

<%@include file="../../includes/footer.jsp" %>