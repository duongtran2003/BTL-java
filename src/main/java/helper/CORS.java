/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author pc
 */
public class CORS {
    public CORS() {

    }

    public static void disableCORS(HttpServletResponse response, String method) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", method.toUpperCase());
    }
}
