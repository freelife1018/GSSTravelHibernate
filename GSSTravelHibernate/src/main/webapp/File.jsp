<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.net.*"%>
<%

	String tra_Name=request.getParameter("tra_Name");
    String path = "C:\\travel\\";                    //檔案主要放置目錄
    String filename = tra_Name+".xls";                   //檔案名
    filename = new String(filename.getBytes("UTF-8"),"UTF-8");
   
    File file = new File(path+filename);
    if(file.exists()){//檢驗檔案是否存在
        try{
            response.setHeader("Content-Disposition","attachment; filename=\""  + URLEncoder.encode(filename, "UTF-8") + "\"");  
            OutputStream output = response.getOutputStream();
            InputStream in = new FileInputStream(file);
            byte[] b = new byte[2048];
            int len;
           
            while((len = in.read(b))>0){
              output.write(b,0,len);
            }
            in.close();
            output.flush();
            output.close();   //關閉串流
            out.clear();
            out = pageContext.pushBody();
        }catch(Exception ex){
            out.println("Exception : "+ex.toString());
            out.println("<br/>");
        }
    }else{
        out.println(filename+" : 此檔案不存在");
        out.println("<br/>");
    }
%>