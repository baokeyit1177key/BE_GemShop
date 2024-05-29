package online.gemfpt.BE.model;

import lombok.Data;

@Data
public class ProductsRequest {
    long id;
    String name;
    String descriptions;
    String category;
    double price; // giá này là giá sau khi tính toán ( nhân với giá nguyên liệu + áp giá )
    double priceRate ;  // tỉ lệ áp giá
    int stock;
    String url; // ảnh


}
