package mall.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import mall.ProductApplication;

@Entity
@Table(name = "Product_table")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long productId;

    private String productName;

    private Integer stock;

    public static ProductRepository repository() {
        ProductRepository productRepository = ProductApplication.applicationContext.getBean(
            ProductRepository.class
        );
        return productRepository;
    }

    public static void decreaseStock(DeliveryCompleted deliveryCompleted) {
        
        repository().findById(deliveryCompleted.getProductId()).ifPresent(product->{
            product.setStock(product.getStock() - deliveryCompleted.getQty());
            repository().save(product);
         });

    }

    public static void increaseStock(DeliveryCanceled deliveryCanceled) {
        repository().findById(deliveryCanceled.getProductId()).ifPresent(product->{
            product.setStock(product.getStock() + deliveryCanceled.getQty());
            repository().save(product);

         });
    }

}
