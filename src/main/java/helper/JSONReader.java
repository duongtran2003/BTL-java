/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 *
 * @author pc
 */
public class JSONReader {
    public JSONReader() {

    }  

    public static String readJSON(HttpServletRequest request) throws IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String jsonFromRequest = "";
		String tmp = buffer.readLine();
		while (tmp != null) {
			jsonFromRequest += tmp;
			tmp = buffer.readLine();
		}
        return jsonFromRequest;
    }
}
