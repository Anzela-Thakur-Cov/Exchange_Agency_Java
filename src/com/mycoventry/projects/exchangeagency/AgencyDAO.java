package com.mycoventry.projects.exchangeagency;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.util.Base64;

import javax.servlet.annotation.MultipartConfig;


@MultipartConfig(maxFileSize = 16177215)

public class AgencyDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public AgencyDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean insertAgency(Agency agency) throws SQLException {
        String sql = "INSERT INTO userdb.agency (name, price, description, product_condition, category, location, feature, brand, username, image, filepath) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, agency.getName());
        statement.setFloat(2, agency.getPrice());
        statement.setString(3, agency.getDescription());
        statement.setString(4, agency.getProduct_condition());
        statement.setString(5, agency.getCategory());
        statement.setString(6, agency.getLocation());
        statement.setString(7, agency.getFeature());
        statement.setString(8, agency.getBrand());
        statement.setString(9, agency.getUsername());
      
        String tempFilepath = "";
        tempFilepath = "D:/uploadImages/" + agency.getFilepath();
        statement.setString(11, tempFilepath);
        
        System.out.println("file path:" +tempFilepath);
        boolean rowInserted = false;
        
     // Read the image file and set it to binary form
     		try  {
     			FileInputStream inputStream = new FileInputStream(tempFilepath);
     			statement.setBinaryStream(10, inputStream, inputStream.available());
     			rowInserted = statement.executeUpdate() > 0;
     			System.out.println("Record inserted/updated successfully");
     		}catch (IOException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}

        
        statement.close();
        disconnect();
        return rowInserted;
    }
    
    public List<Agency> listAgencyByCategory(String categoryFilter) throws SQLException {
        List<Agency> listAgency = new ArrayList<>();
        String sql = "SELECT * FROM userdb.agency WHERE category = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, categoryFilter); // Use the method parameter here
        
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            float price = resultSet.getFloat("price");
            String description = resultSet.getString("description");
            String product_condition = resultSet.getString("product_condition");
            String category = resultSet.getString("category"); // Rename this variable if necessary
            String location = resultSet.getString("location");
            String feature = resultSet.getString("feature");
            String brand = resultSet.getString("brand");
            String username = resultSet.getString("username");
            String filepath = resultSet.getString("filepath");
            Blob blob = resultSet.getBlob("image");
            
            InputStream inputStream = blob.getBinaryStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            
            try {
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            byte[] imageBytes = outputStream.toByteArray();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            
            Agency agency = new Agency(id, name, price, description, product_condition, category, location, feature, brand, username, filepath);
            agency.setBase64Image(base64Image);
            listAgency.add(agency);
        }
        
        resultSet.close();
        statement.close();
        disconnect();
        
        return listAgency;
    }

    public List<Agency> listAgencyByUsername(String username) throws SQLException {
        List<Agency> listAgency = new ArrayList<>();
        String sql = "SELECT * FROM userdb.agency WHERE username = 'anzelathakur'"; // Hardcoded username
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            float price = resultSet.getFloat("price");
            String description = resultSet.getString("description");
            String product_condition = resultSet.getString("product_condition");
            String category = resultSet.getString("category");
            String location = resultSet.getString("location");
            String feature = resultSet.getString("feature");
            String brand = resultSet.getString("brand");
            Blob blob = resultSet.getBlob("image");
            String filepath = resultSet.getString("filepath");
            
            InputStream inputStream = blob.getBinaryStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            
            try {
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            byte[] imageBytes = outputStream.toByteArray();
			String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            Agency agency = new Agency(id, name, price, description, product_condition, category, location, feature, brand, "anzelathakur", filepath);
            agency.setBase64Image(base64Image);
            listAgency.add(agency);
        }
        resultSet.close();
        statement.close();
        disconnect();
        return listAgency;
    }

    public List<Agency> listAllAgencies() throws SQLException {
        List<Agency> listAgency = new ArrayList<>();

        String sql = "SELECT * FROM userdb.agency";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            float price = resultSet.getFloat("price");
            String description = resultSet.getString("description");
            String product_condition = resultSet.getString("product_condition");
            String category = resultSet.getString("category");
            String location = resultSet.getString("location");
            String feature = resultSet.getString("feature");
            String brand = resultSet.getString("brand");
            String username = resultSet.getString("username");
            Blob blob = resultSet.getBlob("image");
            String filepath = resultSet.getString("filepath");
            
            InputStream inputStream = blob.getBinaryStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            
            try {
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			byte[] imageBytes = outputStream.toByteArray();
			String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            

            Agency agency = new Agency(id, name, price, description, product_condition, category, location, feature, brand, username, filepath);
            agency.setBase64Image(base64Image);
            listAgency.add(agency);
        }

        resultSet.close();
        statement.close();
        disconnect();

        return listAgency;
    }

    public boolean deleteAgency(Agency agency) throws SQLException {
        String sql = "DELETE FROM agency where id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, agency.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean updateAgency(Agency agency) throws SQLException {
        String sql = "UPDATE agency SET name = ?, price = ?, description = ?, product_condition = ?, category = ?, location = ?, feature = ?, brand = ?, username = ?, image = ?, filepath = ? ";
        sql += "WHERE id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, agency.getName());
        statement.setFloat(2, agency.getPrice());
        statement.setString(3, agency.getDescription());
        statement.setString(4, agency.getProduct_condition());
        statement.setString(5, agency.getCategory());
        statement.setString(6, agency.getLocation());
        statement.setString(7, agency.getFeature());
        statement.setString(8, agency.getBrand());
        statement.setString(9, agency.getUsername());
        statement.setInt(12, agency.getId());
        
        
        String tempFilepath = "";
		tempFilepath = "D:/uploadImages/" + agency.getFilepath();
		statement.setString(11, tempFilepath);
		
		
		System.out.println("file path :"+tempFilepath);
		boolean rowUpdated= false;
		
		// Read the image file and set it to binary form
		try  {
			FileInputStream inputStream = new FileInputStream(tempFilepath);
			statement.setBinaryStream(10, inputStream, inputStream.available());
			rowUpdated = statement.executeUpdate() > 0;
			System.out.println("Record inserted/updated successfully");
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

     
        statement.close();
        disconnect();
        return rowUpdated;
    }
    
    

    public Agency getAgency(int id) throws SQLException {
        Agency agency = null;
        String sql = "SELECT * FROM userdb.agency WHERE id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("name");
            float price = resultSet.getFloat("price");
            String description = resultSet.getString("description");
            String product_condition = resultSet.getString("product_condition");
            String category = resultSet.getString("category");
            String location = resultSet.getString("location");
            String feature = resultSet.getString("feature");
            String brand = resultSet.getString("brand");
            String username = resultSet.getString("username");
            String filepath = resultSet.getString("filepath");

            agency = new Agency(id, name, price, description, product_condition, category, location, feature, brand, username, filepath);
        }

        resultSet.close();
        statement.close();
      

        return agency;
    }
    

   
    

}
