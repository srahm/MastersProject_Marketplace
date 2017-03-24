/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensode.jsf.namedbeans;

/**
 *
 * @author Sammy
 */
import com.ensode.jsf.util.DataConnect;
import java.sql.*;
import java.io.*;
import javax.servlet.*;

import javax.servlet.http.*;

public class DisplayImage extends HttpServlet {

    private static final long serialVersionUID = 4593558495041379082L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Statement stmt = null;
        ResultSet rs;
        InputStream sImage;
        try {

            String id = request.getParameter("Image_id");
            System.out.print("this is display image" + id);

            Connection con = DataConnect.getConnection();
            stmt = con.createStatement();
            String strSql = "select PICTURES from PICTURE_TABLE where ID=" + id;
            rs = stmt.executeQuery(strSql);
            if (rs.next()) {
                byte[] bytearray = new byte[1048576];
                int size = 0;
                sImage = rs.getBinaryStream(1);
                response.reset();
                response.setContentType("image/jpeg");
                while ((size = sImage.read(bytearray)) != -1) {
                    response.getOutputStream().
                            write(bytearray, 0, size);
                    strSql = null;
                    stmt = null;
                    sImage = null;
                    con.close();
                }
            }

        } catch (Exception e) {

        }
    }

}
