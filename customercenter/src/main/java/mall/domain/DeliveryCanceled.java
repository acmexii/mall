package mall.domain;

import java.util.*;
import lombok.Data;
import mall.infra.AbstractEvent;

@Data
public class DeliveryCanceled extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Long productId;
    private String productName;
    private Long customerId;
    private String adddress;
    private String status;
}
