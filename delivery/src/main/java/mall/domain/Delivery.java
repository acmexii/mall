package mall.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import mall.DeliveryApplication;
import mall.domain.DeliveryCanceled;
import mall.domain.DeliveryCompleted;

@Entity
@Table(name = "Delivery_table")
@Data
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private Long productId;

    private String productName;

    private Long customerId;

    private String adddress;

    private String status;

    private int qty;

    // @PostPersist
    // public void onPostPersist() {
    //     DeliveryCompleted deliveryCompleted = new DeliveryCompleted(this);
    //     deliveryCompleted.publishAfterCommit();
    // }

    // @PostUpdate
    // public void onPostUpdate() {
    //     DeliveryCanceled deliveryCanceled = new DeliveryCanceled(this);
    //     deliveryCanceled.publishAfterCommit();
    // }

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = DeliveryApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    public static void startDelivery(OrderPlaced orderPlaced) {
        /** Example 1:  new item */
        Delivery delivery = new Delivery();
        delivery.setOrderId(orderPlaced.getId());
        delivery.setProductId(orderPlaced.getProductId());
        delivery.setProductName(orderPlaced.getProductName());
        delivery.setQty(orderPlaced.getQty());
        delivery.setStatus("DELIVERY COMPLETED");
        repository().save(delivery);

        DeliveryCompleted deliveryCompleted = new DeliveryCompleted(delivery);
        deliveryCompleted.publishAfterCommit();
    }

    public static void cancelDelivery(OrderCanceled orderCanceled) {
        repository().findByOrderId(orderCanceled.getId()).ifPresent(delivery->{
            
            delivery.setStatus("DELIVERY CANCELLED");
            repository().save(delivery);

            DeliveryCanceled deliveryCanceled = new DeliveryCanceled(delivery);
            deliveryCanceled.publishAfterCommit();

         });

    }

}
