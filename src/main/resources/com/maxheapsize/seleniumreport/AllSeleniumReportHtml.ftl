<#-- @ftlvariable name="report" type="com.maxheapsize.seleniumreport.report.SeleniumReport[]" -->
<html>
<head>
    <style type="text/css">
        body {
            font-family: Arial;
        }
    </style>

</head>
<body>
<h1>Selenium Reports</h1>
<table width="100%">
<#list report as entry>
    <tr>
        <#assign color>${entry.isSuccess()?string("#adff2f", "red")}</#assign>
        <td bgcolor="${color}">
            <a href="report-${entry.getTestClass()}-${entry.getTestMethod()}/index.html">${entry.getTestClassShortName()}</a>
        </td>
        <td bgcolor="${color}">
            <a href="report-${entry.getTestClass()}-${entry.getTestMethod()}/index.html">${entry.getTestMethod()}</a>
        </td>
    </tr>
</#list>
</table>

</body>
</html>