package cps3230.assignment.Product;

public class ProductDetails {
    private String title;
    private String price;
    private String img;
    private String details;

    public ProductDetails(String title, String price, String img, String details){
        this.title = title;
        this.price = price;
        this.img = img;
        this.details = details;
    }

    public ProductDetails(){

    }

    public void setProductTitle(String title) {
        this.title = title;
    }

    public String getProductTitle(){
        return title;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice(){
        return price;
    }
    public void setImg(String img) {
        this.img = img;
    }

    public String getImg(){
        return img;
    }
    public void setProdDetails(String details) {
        this.details = details;
    }

    public String getProdDetails(){
        return details;
    }
}
