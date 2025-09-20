package com.MyShop.Ecom_Pro.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int ID ;
    private String Name;
    private String Description;
    private String Brand;
    private BigDecimal Price;
    private String Category;

//    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "DD-mm-YYYY") // small mm fetches date
//    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "DD-MM-YYYY") // 14-01-2024
    //    private  Date Release_date; // old
//    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "DD-MMM-YYYY")  // 14-Jan-2024 , capital  DD -> gives the day from begining of year so use dd
//    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MMM-YYYY  ,  hh:mm a", timezone ="UTC") //

    //    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-YYYY ") // this part is handled in UI in ver.3
    private Date releaseDate; // these 3 names changed as per in the UI of react downloaded file
    private  boolean productAvailable;
    private  int stockQuantity;

    private  String imageName;
    private  String Image_Type;
    @Lob // large object
    private byte[] Image_Data;









    //to see in console
    @Override
    public String toString() {
        return "Product{" +
                "Available=" + productAvailable +
                ", ID=" + ID +
                ", Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", Brand='" + Brand + '\'' +
                ", Price=" + Price +
                ", Category='" + Category + '\'' +
                ", Release_date=" + releaseDate +
                ", Quantity=" + stockQuantity +
                '}';
    }
}
