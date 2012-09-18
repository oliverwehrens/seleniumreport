<#-- @ftlvariable name="report" type="com.maxheapsize.seleniumreport.report.SeleniumReport" -->
<#-- @ftlvariable name="entry" type="com.maxheapsize.seleniumreport.report.SeleniumReportEntry" -->
<html>
<head>
    <style type="text/css">
        body {
            font-family: Arial;
        }
    </style>

</head>

<body>
<h1>${report.getTestClass()} - ${report.getTestMethod()} </h1>

<a href="../index.html">Main Index</a>

<table>
<#list report.getEntries() as entry>
    <#assign color>${entry.isSuccess()?string("#adff2f", "red")}</#assign>
    <tr bgcolor="${color}">
        <td>${entry.getDate()?datetime}</td>
        <td>${entry.getText()}</td>
        <#assign imageLink>${entry.hasImage()?string("<a href='${entry.getImageFileName()}'><img width=400 height=300 src='${entry.getImageFileName()}'/></a>", "")}</#assign>
        <td>${imageLink}</td>
    </tr>
</#list>
</table>

</body>
</html>