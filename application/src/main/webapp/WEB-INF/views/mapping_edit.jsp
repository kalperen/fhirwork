<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Fhirwork Mapping Configuration</title>
    
    <link rel="stylesheet" type="text/css" href="/resources/reset.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/general.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/mapping.css"/>
</head>
<body>
    <div class="container">
        <div class="navigation">
            <a class="link selected" href="/configuration/mapping/list">
                <div class="icon mapping"></div>
                <span>FHIR Mappings</span>
            </a>
            <a class="link" href="/configuration/network">
                <div class="icon network"></div>
                <span>Network Settings</span>
            </a>
            <a class="link" href="/configuration/cache">
                <div class="icon cache"></div>
                <span>Cache Settings</span>
            </a>
        </div>

        <div class="content">
            <h1 class="title">${mapping.code} Settings</h1>

            <c:if test="${type == 'basic'}">
            <form action="edit/basic" method="POST" id="basic_form">
                <table>
                    <tbody>
                        <tr class="property">
                            <th><label for="text" class="label">Text</label></th>
                            <td><input type="text" id="text" name="text" value="${mapping.text}" class="textbox"/></td>
                        </tr>
                        <tr class="property">
                            <th><label for="archetype" class="label">Archetype</label></th>
                            <td><input type="text" id="archetype" name="archetype" value="${mapping.archetype}" class="textbox"/></td>
                        </tr>
                        <tr class="property">
                            <th><label for="date" class="label">Date</label></th>
                            <td><input type="text" id="date" name="date" value="${mapping.date}" class="textbox"/></td>
                        </tr>
                        <tr class="property">
                            <th><label for="magnitude" class="label">Magnitude</label></th>
                            <td><input type="text" id="magnitude" name="magnitude" value="${mapping.magnitude}" class="textbox"/></td>
                        </tr>
                        <tr class="property">
                            <th><label for="unit" class="label">Unit</label></th>
                            <td><input type="text" id="unit" name="unit" value="${mapping.unit}" class="textbox"/></td>
                        </tr>
                    </tbody>
                </table>
                <input type="text" id="code" name="code" value="${mapping.code}" style="visibility:hidden;"/>
                <input type="button" onclick="confirmSubmit('basic_form')" value="Submit" class="button">
            </form>
            </c:if>

            <c:if test="${type == 'scripted'}">
            <form class="script_form" action="edit/scripted" method="POST" id="script_form">
                <label for="code" class="label">Code</label>
                <input type="text" id="code" name="code" value="${mapping.code}" class="textbox"/>

                <label for="script" class="label">Script</label>
                <textarea id="script" name="script" class="script" rows="50" cols="100">${mapping.script}</textarea>

                <input type="button" onclick="confirmSubmit('script_form')" value="Submit" class="button">
            </form>
            </c:if>

        </div>
    </div>
    
    <script>
    	function confirmSubmit(form){
 		   if(confirm("Are you sure you want to make this modification?")){
		       document.getElementById(form).submit();
		       alert("The modification has been submitted.");
		   }
    	}
    </script>
</body>
</html>
