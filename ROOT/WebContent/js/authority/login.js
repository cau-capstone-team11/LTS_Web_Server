function login()
{
	var form = document.frm;
	
	frm.action = "/loginProcess.do";
	frm.target = "_self";
	frm.submit();
}