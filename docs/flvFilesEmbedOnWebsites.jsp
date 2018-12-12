<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Design Record Book</title>
<LINK href="../css/edrb_style.css" type=text/css rel=stylesheet>
</head>
<script language=JavaScript src="../js/Cal_So.js"></script>
<script language="JavaScript" type="text/JavaScript"></script>
<script type="text/javascript" src="http://videobookmark.net/www/Flash/Samples/flowplayer/example/flowplayer-3.1.1.min.js"></script>
<script language="JavaScript">
	flowplayer("player", "http://videobookmark.net/www/Flash/Samples/flowplayer/example/flowplayer-3.1.1.swf");
</script>



<body>
<f:view>
	<h:form id="login">
	<div align="center">	
		<h:panelGrid columns="1" width="50%" style="text-align:center">
			<h:panelGrid columns="3" width="100%" style="text-align:center">
				<h:outputLabel value="SSO ID" style="text-align:left"/>
				<h:inputText id="id" value="#{userDetailsBean.localSSOID}" style="text-align:left" required="true" requiredMessage="Please enter the ID"/>
				<h:message for="id" style="color:red " />

				<h:outputLabel value="Password" style="text-align:left"/>
				<h:inputSecret value="" style="text-align:left" id="pass" required="true" requiredMessage="Please enter the Password"/>
				<h:message for="pass" style="color:red " />

			</h:panelGrid>
			<h:panelGrid columns="2" width="100%" style="text-align:center">
				<h:panelGroup>
					<h:commandButton value="Submit" action="login" styleClass="button"/>&nbsp;
					<h:commandButton value="Cancel" action="cancellogin" styleClass="button"/>&nbsp;
				</h:panelGroup>
			</h:panelGrid>
			<h:panelGrid columns="3" width="100%" style="text-align:center">
			<h:panelGroup>
				 <a href="http://e1p1.simplecdn.net/flowplayer/flowplayer-700.flv" style="display:block;width:425px;height:300px;" id="player"> </a>
				   
			</h:panelGroup>
			</h:panelGrid>
	  </h:panelGrid>
	 
	  
	  </div>		
	</h:form>
</f:view>
</body>
</html>