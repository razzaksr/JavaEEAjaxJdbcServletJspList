package servs;

import com.google.gson.Gson;
import oracle.jdbc.driver.OracleDriver;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ListServlet", value = "/list")
public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Driver driver=new OracleDriver();
            DriverManager.registerDriver(driver);
            Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
            String qry="select * from hai";
            PreparedStatement preparedStatement=connection.prepareStatement(qry);
            ResultSet resultSet=preparedStatement.executeQuery();
            List<Hai> haiList=new ArrayList<Hai>();
            while (resultSet.next()){
                Hai hai=new Hai();
                hai.setId(resultSet.getInt("id"));
                hai.setName(resultSet.getString("name"));
                hai.setPrice(resultSet.getInt("price"));
                haiList.add(hai);
            }
            Gson gson=new Gson();
            PrintWriter printWriter=response.getWriter();
            printWriter.write(gson.toJson(haiList));
            printWriter.flush();
            printWriter.close();
        }
        catch (SQLException sqlException){

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
