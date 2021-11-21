package com.laioffer.onlineOrder;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import org.json.JSONObject; // JSON format responses in doGet
import org.apache.commons.io.IOUtils;   // Write data by POST.
import com.laioffer.onlineOrder.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;

// 用 Postman 模拟 FE 发来 .../hello-servlet 就会触发这段程序
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // response.setContentType("text/html");
        response.setContentType("application/json");    // Specify the content-type

        // Hello
//        String customer = request.getParameter("customer");
//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>Hello " + customer + "</h1>");
//        out.println("</body></html>");

        // JSON object
//        JSONObject customer = new JSONObject();
//        customer.put("email", "gliu@gmail.com");
//        customer.put("first_name", "Fox");
//        customer.put("last_name", "Liu");
//        customer.put("age", 29);
//        response.getWriter().print(customer);

        ObjectMapper mapper = new ObjectMapper();

        Customer customer = new Customer();
        customer.setEmail("gliu@gmail.com");
        customer.setPassword("123456");
        customer.setFirstName("Fox");
        customer.setLastName("Liu");
        customer.setEnabled(true);

        response.getWriter().print(mapper.writeValueAsString(customer));

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Read customer info from request body
        JSONObject jsonRequest = new JSONObject(IOUtils.toString(request.getReader()));
        String email = jsonRequest.getString("email");
        String firstName = jsonRequest.getString("first_name");
        String lastName = jsonRequest.getString("last_name");
        int age = jsonRequest.getInt("age");

        // Print customer info to IDE console
        System.out.println("Email is: " + email);
        System.out.println("First name is: " + firstName);
        System.out.println("Last name is: " + lastName);
        System.out.println("Age is: " + age);

        // Return status = ok as response body to the client
        response.setContentType("application/json");
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", "ok");
        response.getWriter().print(jsonResponse);
    }

    public void destroy() {
    }
}