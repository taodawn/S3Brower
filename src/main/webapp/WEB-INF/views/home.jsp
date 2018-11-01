<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>elFinder 2.0</title>

    <!-- jQuery and jQuery UI (REQUIRED) -->
    <link rel="stylesheet" type="text/css" href="http://code.jquery.com/ui/1.8.23/themes/smoothness/jquery-ui.css">
    <script
            src="http://code.jquery.com/jquery-1.8.0.js"
            integrity="sha256-00Fh8tkPAe+EmVaHFpD+HovxWk7b97qwqVi7nLvjdgs="
            crossorigin="anonymous"></script>
    <script
            src="http://code.jquery.com/ui/1.8.23/jquery-ui.js"
            integrity="sha256-lFA8dPmfmR4AQorTbla7C2W0aborhztLt0IQFLAVBTQ="
            crossorigin="anonymous"></script>

    <!-- elFinder CSS (REQUIRED) -->
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/elfinder.min.css" />">
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/theme.css" />">

    <!-- elFinder JS (REQUIRED) -->
    <script type="text/javascript" src="<c:url value="/resources/js/elfinder.min.js" />"></script>

    <!-- elFinder translation (OPTIONAL) -->
    <script type="text/javascript" src="<c:url value="/resources/js/i18n/elfinder.pt_BR.js" />"></script>

    <!-- elFinder initialization (REQUIRED) -->
    <script type="text/javascript" charset="utf-8">
		// Documentation for client options:
		// https://github.com/Studio-42/elFinder/wiki/Client-configuration-options
		$(document).ready(function() {
			$('#elfinder').elfinder({
				url : 'connector',  // connector URL (REQUIRED)
				lang: 'en_US'                    // language (OPTIONAL)
			});
		});
    </script>
</head>
<body>
	<!-- Element where elFinder will be created (REQUIRED) -->
	<div id="elfinder"></div>
</body>
</html>
